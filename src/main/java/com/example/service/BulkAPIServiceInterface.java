package com.example.service;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.sforce.async.*;
import com.sforce.ws.ConnectionException;

interface BulkAPIServiceInterface {
	
	//public static final String mySqlUrl = "jdbc:mysql://localhost/RLSFDCArchive?user=" + mysqlusername +"&password=" + mysqlpassword;
	
	public JobInfo createJob(String sobjectType, String operation, BulkConnection connection) throws AsyncApiException;
	public JobInfo createJob(BulkConnection connection) throws AsyncApiException;
	
	public void createBatch(FileOutputStream tmpOut, File tmpFile, List<BatchInfo> batchInfos, BulkConnection connection, JobInfo jobInfo) throws IOException, AsyncApiException;
	
	public List<BatchInfo> createBatchesFromCSVFile(BulkConnection connection, JobInfo jobInfo, String csvFileName) throws IOException, AsyncApiException;
	public List<BatchInfo> createBatchesFromCSVFile(BulkConnection connection, JobInfo jobInfo) throws IOException, AsyncApiException;
	public List<BatchInfo> createBatchesFromStream(BulkConnection connection, JobInfo jobInfo, String SOQLQuery) throws IOException, AsyncApiException;
	
	public void closeJob(BulkConnection connection, String jobId) throws AsyncApiException;
	public void checkResults(BulkConnection connection, JobInfo job, List<BatchInfo> batchInfoList, String fromToMedia, String fileName) throws AsyncApiException, IOException, InterruptedException;
	
	public void awaitCompletion(BulkConnection connection, JobInfo job,List<BatchInfo> batchInfoList) throws AsyncApiException;
	
	public void runJob(String environment, String fromToMedia, String sobjectType, String operation, String SOQLQuery, String sampleFileName) throws AsyncApiException, ConnectionException, IOException, InterruptedException;
	
	public boolean createCSVFromMySQL(String host, String port, String databaseName, String mysqlusername, String mysqlpassword, String mySqlQuery, String csvFileName);
	
	public StringBuilder run(String environment, String operation, String fromToMedia, String SObject, String SOQLquery, String csvFileName) throws AsyncApiException, ConnectionException, IOException, InterruptedException;
	

}
