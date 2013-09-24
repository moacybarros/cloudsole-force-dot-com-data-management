package com.example.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.service.LoginService;
import com.example.service.BulkAPIService;
import com.sforce.async.AsyncApiException;
import com.sforce.ws.ConnectionException;

@Controller
@RequestMapping("/batch")
public class BatchController {
	
	@Autowired
	LoginService loginService;
	
	private StringBuilder batchoutput = new StringBuilder();
	
	@RequestMapping(value="/job")
	public String createBulkJobView(Map<String, Object> map)
	{
		return "newbatchjob";
	}
	
	public String queryforSelectedsObject(Map<String, Object> map)
	{
		return "querybatchjob";
	}
	
	@RequestMapping(value="/run", method=RequestMethod.POST)
	public String runBulkJob(HttpServletRequest request,
			Map<String, Object> map, HttpServletResponse response) throws HttpMessageNotReadableException, IOException, AsyncApiException, ConnectionException, InterruptedException
	{
		final ServletServerHttpRequest inputMessage = new ServletServerHttpRequest(
				request);
		final Map<String, String> formData = new FormHttpMessageConverter().read(null, inputMessage).toSingleValueMap();
		String environment = formData.get("environment");
		String operations = formData.get("operations");
		String export_option = formData.get("export_option");
		String sobject = formData.get("sobject");
		String query_string = formData.get("query_string");
		//String filepath = formData.get("uni_file");
		//TODO: auto create the path depending on the date
		File tmpFile = File.createTempFile("cloudsole", ".csv");
		System.out.println(tmpFile.getAbsolutePath());
		batchoutput.append(new BulkAPIService().run(environment, operations, export_option, sobject, query_string, tmpFile.getAbsolutePath()));
	
		response.setContentType("application/csv"); 
	    response.setContentLength(new Long(tmpFile.length()).intValue());
	    response.setHeader("Content-Disposition","attachment; filename=CloudSole-Batch-" + sobject + ".csv");
	 
	     try {
	    	 FileCopyUtils.copy(new FileInputStream(tmpFile), response.getOutputStream());
	    	 map.put("success", "Your file was downloaded successfully");
	     } catch (IOException e) {
	         e.printStackTrace();
	         map.put("error", e.getMessage());
	     }
	     finally
	     {
	    	 tmpFile.deleteOnExit();
	     }
		
		return "newbatchjob";
	}
	
	@RequestMapping("/result")
	public String batchJobResult(Map<String, Object> map)
	{
		//TODO: show the path where the file is stored.
		map.put("batchresults", batchoutput.toString());
		return "batchjobresult";
	}

}
