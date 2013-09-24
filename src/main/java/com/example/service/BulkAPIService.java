package com.example.service;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ServiceLoader;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import au.com.bytecode.opencsv.CSVWriter;

import com.sforce.async.*;
import com.sforce.bulk.CsvWriter;
import com.sforce.soap.partner.Connector;
import com.sforce.soap.partner.PartnerConnection;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;

public class BulkAPIService implements BulkAPIServiceInterface{

	private LoginServiceImp loginService;
	
	private StringBuilder batchoutput = new StringBuilder();
	
	@Override
	public JobInfo createJob(String sobjectType, String operation, BulkConnection connection)
			throws AsyncApiException {
		
		JobInfo job = new JobInfo();
	    job.setObject(sobjectType);
	    job.setOperation(OperationEnum.valueOf(operation));
	    job.setConcurrencyMode(ConcurrencyMode.Parallel);
	    job.setContentType(ContentType.CSV);
	    job = connection.createJob(job);
	    assert job.getId() != null;
	    job = connection.getJobStatus(job.getId());
	    batchoutput.append(job);
	    return job;
	}

	
	@Override
	public void createBatch(FileOutputStream tmpOut, File tmpFile,
			List<BatchInfo> batchInfos, BulkConnection connection,
			JobInfo jobInfo) throws IOException, AsyncApiException {
		
		    tmpOut.flush();
		    tmpOut.close();
		    FileInputStream tmpInputStream = new FileInputStream(tmpFile);
		    try {
		      BatchInfo batchInfo = connection.createBatchFromStream(jobInfo,
		          tmpInputStream);
		      batchoutput.append(batchInfo);
		      batchInfos.add(batchInfo);

		    } finally {
		      tmpInputStream.close();
		    }
	}

	@Override
	public List<BatchInfo> createBatchesFromCSVFile(BulkConnection connection,
			JobInfo jobInfo, String csvFileName) throws IOException,
			AsyncApiException {
	    
		List<BatchInfo> batchInfos = new ArrayList<BatchInfo>();
	    BufferedReader rdr = new BufferedReader(new InputStreamReader(
	        new FileInputStream(csvFileName)));
	    // read the CSV header row
	    byte[] headerBytes = (rdr.readLine() + "\n").getBytes("UTF-8");
	    int headerBytesLength = headerBytes.length;
	    File tmpFile = File.createTempFile("bulkAPIInsert", ".csv");

	    // Split the CSV file into multiple batches
	    try {
	      FileOutputStream tmpOut = new FileOutputStream(tmpFile);
	      int maxBytesPerBatch = 10000000; // 10 million bytes per batch
	      int maxRowsPerBatch = 10000; // 10 thousand rows per batch
	      int currentBytes = 0;
	      int currentLines = 0;
	      String nextLine;
	      while ((nextLine = rdr.readLine()) != null) {
	        byte[] bytes = (nextLine + "\n").getBytes("UTF-8");
	        // Create a new batch when our batch size limit is reached
	        if (currentBytes + bytes.length > maxBytesPerBatch
	            || currentLines > maxRowsPerBatch) {
	          createBatch(tmpOut, tmpFile, batchInfos, connection, jobInfo);
	          currentBytes = 0;
	          currentLines = 0;
	        }
	        if (currentBytes == 0) {
	          tmpOut = new FileOutputStream(tmpFile);
	          tmpOut.write(headerBytes);
	          currentBytes = headerBytesLength;
	          currentLines = 1;
	        }
	        tmpOut.write(bytes);
	        currentBytes += bytes.length;
	        currentLines++;
	      }
	      // Finished processing all rows
	      // Create a final batch for any remaining data
	      if (currentLines > 1) {
	        createBatch(tmpOut, tmpFile, batchInfos, connection, jobInfo);
	      }
	    } finally {
	      tmpFile.delete();
	    }
	    return batchInfos;
	}

	@Override
	public void closeJob(BulkConnection connection, String jobId)
			throws AsyncApiException {
		JobInfo job = new JobInfo();
	    job.setId(jobId);
	    job.setState(JobStateEnum.Closed);
	    connection.updateJob(job);
	}

	@Override
	public void checkResults(BulkConnection connection, JobInfo job,
			List<BatchInfo> batchInfoList, String fromToMedia, String fileName) throws AsyncApiException,
			IOException 
			{
	
		String[] queryResults = null;
		if (batchInfoList != null)
		{
			for (BatchInfo b : batchInfoList) 
			{
				QueryResultList list = connection.getQueryResultList(job.getId(), b.getId());
				queryResults = list.getResult();
				CSVReader rdr = null;
				if (queryResults != null) 
				for (String resultId : queryResults) 
						rdr = new CSVReader(connection.getQueryResultStream(job.getId(), b.getId(), resultId));
				if (fromToMedia.equalsIgnoreCase("csv"))
					writeToCSVFile(rdr, fileName);
				else if (fromToMedia.equalsIgnoreCase("mysql"))
					writeToMySQL(rdr);
					
			}
		}
	}
	
	public void writeToMySQL(CSVReader rdr) throws IOException
	{
		

	}
	
	
	public void writeToCSVFile(CSVReader rdr, String fileName) throws IOException
	{
		List<String> resultHeader = rdr.nextRecord();
		String[] resultHeaderForCSV = new String[resultHeader.size()];
    
		for (int k = 0; k < resultHeader.size(); k++)
		{
			resultHeaderForCSV[k] = resultHeader.get(k);
		}
		List<String> row;
		Writer writer = new FileWriter(fileName);
		CsvWriter csvWriter = new CsvWriter(resultHeaderForCSV, writer);

		while ((row = rdr.nextRecord()) != null)
		{
			String[] resultRecord = new String[resultHeader.size()];
			for (int i = 0; i < resultHeader.size(); i++) {
        	//resultInfo.put(resultHeader.get(i), row.get(i));
				resultRecord[i] = row.get(i);
			}
			csvWriter.writeRecord(resultRecord);
		}
		csvWriter.endDocument();

	}
	
	@Override
	public void awaitCompletion(BulkConnection connection, JobInfo job,
			List<BatchInfo> batchInfoList) throws AsyncApiException {
		long sleepTime = 0L;
	    Set<String> incomplete = new HashSet<String>();
	    for (BatchInfo bi : batchInfoList) {
	      incomplete.add(bi.getId());
	    }
	    while (!incomplete.isEmpty()) {
	      try {
	        Thread.sleep(sleepTime);
	      } catch (InterruptedException e) {
	      }
	      batchoutput.append("Awaiting results..." + incomplete.size());
	      sleepTime = 10000L;
	      BatchInfo[] statusList = connection.getBatchInfoList(job.getId())
	          .getBatchInfo();
	      for (BatchInfo b : statusList) {
	        if (b.getState() == BatchStateEnum.Completed
	            || b.getState() == BatchStateEnum.Failed) {
	          if (incomplete.remove(b.getId())) {
	        	  batchoutput.append("BATCH STATUS:\n" + b);
	          }
	        }
	      }
	    }
	}

	@Autowired
	@Override
	public void runJob(String environment, String fromToMedia, String operation, String sobjectType, String SOQLQuery,
			String sampleFileName) throws AsyncApiException,
			ConnectionException, IOException, InterruptedException {
	
		BulkConnection connection = null;
		ConnectorConfig connectorConfig = null;
		
		if (environment.equalsIgnoreCase("sandbox"))
		{
			
			ConnectorConfig partnerConfig = new ConnectorConfig();
			partnerConfig.setAuthEndpoint("https://test.salesforce.com/services/Soap/u/27.0");
			partnerConfig.setServiceEndpoint(loginService.getEndpointURL() + "/services/Soap/u/");
			partnerConfig.setSessionId(loginService.getSessionId());
			// Creating the connection automatically handles login and stores
			// the session in partnerConfig
			new PartnerConnection(partnerConfig);
			// When PartnerConnection is instantiated, a login is implicitly
			// executed and, if successful,
			// a valid session is stored in the ConnectorConfig instance.
			// Use this key to initialize a BulkConnection:
			ConnectorConfig config = new ConnectorConfig();
			config.setSessionId(partnerConfig.getSessionId());
			// The endpoint for the Bulk API service is the same as for the normal
			// SOAP uri until the /Soap/ part. From here it's '/async/versionNumber'
			String soapEndpoint = partnerConfig.getServiceEndpoint();
			String apiVersion = "27.0";
			String restEndpoint = soapEndpoint.substring(0, soapEndpoint.indexOf("Soap/"))
			+ "async/" + apiVersion;
			config.setRestEndpoint(restEndpoint);
			// This should only be false when doing debugging.
			config.setCompression(true);
			// Set this to true to see HTTP requests and responses on stdout
			config.setTraceMessage(false);
			connection = new BulkConnection(config);
			batchoutput.append("Authentication Succcessfull");
		}
		if (environment.equalsIgnoreCase("production"))
		{
			ConnectorConfig partnerConfig = new ConnectorConfig();
			partnerConfig.setAuthEndpoint("https://login.salesforce.com/services/Soap/u/27.0");
			partnerConfig.setServiceEndpoint(loginService.getEndpointURL() + "/services/Soap/u/");
			partnerConfig.setSessionId(loginService.getSessionId());
			new PartnerConnection(partnerConfig);
			
			ConnectorConfig config = new ConnectorConfig();
			config.setSessionId(partnerConfig.getSessionId());
			// The endpoint for the Bulk API service is the same as for the normal
			// SOAP uri until the /Soap/ part. From here it's '/async/versionNumber'
			String soapEndpoint = partnerConfig.getServiceEndpoint();
			String apiVersion = "27.0";
			String restEndpoint = soapEndpoint.substring(0, soapEndpoint.indexOf("Soap/"))
			+ "async/" + apiVersion;
			config.setRestEndpoint(restEndpoint);
			// This should only be false when doing debugging.
			config.setCompression(true);
			// Set this to true to see HTTP requests and responses on stdout
			config.setTraceMessage(false);
			connection = new BulkConnection(config);
			batchoutput.append("Authentication Succcessfull");
		}
	    JobInfo job = createJob(sobjectType,operation, connection);
	    List<BatchInfo> batchInfoList = null;
	    
	    if (operation.equalsIgnoreCase("query"))
	    {
	    	batchoutput.append("Start Query");
	    	batchInfoList = createBatchesFromStream(connection, job, SOQLQuery);
	    }			
	    if (operation.equalsIgnoreCase("insert"))
	    {
	    	batchoutput.append("Start Insert");
	    	batchInfoList = createBatchesFromCSVFile(connection, job, sampleFileName);
	    }
	    if (operation.equalsIgnoreCase("update"))
	    {
	    	batchoutput.append("Start Update");
	    	batchInfoList = createBatchesFromCSVFile(connection, job, sampleFileName);
	    }
	    if (operation.equalsIgnoreCase("delete"))
	    {
	    	batchoutput.append("Start Delete");
	    	batchInfoList = createBatchesFromCSVFile(connection, job, sampleFileName);
	    }	
	    closeJob(connection, job.getId());
		awaitCompletion(connection, job, batchInfoList);
		if (operation.equalsIgnoreCase("query"))
			checkResults(connection, job, batchInfoList, fromToMedia, sampleFileName);

	}

	@Override
	public boolean createCSVFromMySQL(String host, String port, String databaseName, String mysqlusername, String mysqlpassword, String mySqlQuery, String csvFileName) {
		
	    Connection conn = null;
	    ResultSet rs = null;
	    boolean success = false;
	    
	    try {
	      Class.forName("com.mysql.jdbc.Driver").newInstance();
	      conn = DriverManager.getConnection("jdbc:mysql://"+ host + ":" + port+"/"+databaseName + "?user="+mysqlusername +"&password="+mysqlpassword);
	      
	      Statement s = conn.createStatement();
	      s.executeQuery(mySqlQuery);
	      rs = s.getResultSet();
	      
	      // dump the contents to the console
	      //System.out.println(rs.getMetaData().getColumnName(1));
	      
	     /* while (rs.next ()){
	        String LnVal = rs.getString ("LASTNAME");
	        String CompanyVal = rs.getString ("COMPANY");
	        System.out.println ("LastName = "+LnVal+", Company = "+CompanyVal);           
	      }   */
	      
	      
	      // write the result set to the CSV file
	      if (rs != null) {
	        CSVWriter writer = new CSVWriter(new FileWriter(csvFileName), ',');
	        writer.writeAll(rs, true);
	        writer.close();
	        batchoutput.append("Successfully fetched records from MySQL");
	        success = true;
	      }
	      
	    } catch (Exception e) {
	      System.err.println("Cannot connect to database server");
	      success = false;
	    } finally {
	      if (rs != null) {
	        try {
	          rs.close();
	          batchoutput.append("Resultset terminated");
	        } catch (Exception e1) { /* ignore close errors */
	        }
	      }
	      if (conn != null) {
	        try {
	          conn.close();
	          batchoutput.append("Database connection terminated");
	        } catch (Exception e2) { /* ignore close errors */
	        }
	      }

	    }
	    return success;
	}


	@Override
	public StringBuilder run(String environment, String operation, String fromToMedia, String SObject, String SOQLquery, String csvFileName) throws AsyncApiException, ConnectionException, IOException, InterruptedException 
	{
		runJob(environment, fromToMedia, operation, SObject, SOQLquery, csvFileName);
		return batchoutput;
	}


	@Override
	public JobInfo createJob(BulkConnection connection)
			throws AsyncApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BatchInfo> createBatchesFromCSVFile(BulkConnection connection,
			JobInfo jobInfo) throws IOException, AsyncApiException {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public List<BatchInfo> createBatchesFromStream(BulkConnection connection,
			JobInfo jobInfo, String SOQLQuery) throws IOException,
			AsyncApiException {
		      List<BatchInfo> batchInfos = new ArrayList<BatchInfo>();
		      
		      BatchInfo info = null;
		      ByteArrayInputStream bout = new ByteArrayInputStream(SOQLQuery.getBytes());
		      info = connection.createBatchFromStream(jobInfo, bout);
		      batchInfos.add(info);
		      
		      return batchInfos;
	}
}
