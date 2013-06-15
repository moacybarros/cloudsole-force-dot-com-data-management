package com.example.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.service.LoginService;
import com.example.util.SObjectUtil;
import com.force.api.DescribeSObject.Field;
import com.force.api.QueryResult;

@Controller
@RequestMapping("/sobject")
public class SObjectController 
{
	private static final Logger logger = LoggerFactory.getLogger(SObjectController.class);
	final static Integer PAGESIZE = 100;
	private static final String SELECT = "SELECT ";
	private static final String FROM = "FROM ";
	private static List<String> sObjectFieldNames = null;
	private static QueryResult<Map> res = null;	
	private static Map<String, String> paginationPages =null;
	boolean soqlcheck = true;

	@Autowired
    private LoginService loginService;
	
	public List<String> queryRecipies()
	{
		List<String> queries = new ArrayList<String>();
		queries.add("SELECT Contact.FirstName, Contact.Account.Name from Contact");
		queries.add("SELECT Account.Name, (SELECT Contact.FirstName, Contact.LastName FROM Account.Contacts) FROM Account");
		queries.add("SELECT Id, Name, Account.Name FROM Contact WHERE Account.Industry = 'media'");
		queries.add("SELECT Name, (SELECT LastName FROM Contacts) FROM Account");
		queries.add("SELECT Name,( SELECT CreatedBy.Name FROM Notes) FROM Account");
		queries.add("SELECT Amount, Id, Name,( SELECT Quantity, ListPrice, PricebookEntry.UnitPrice, PricebookEntry.Name FROM OpportunityLineItems) FROM Opportunity");
		queries.add("SELECT Amount, Id, Name, (SELECT Quantity, ListPrice, PriceBookEntry.UnitPrice, PricebookEntry.Name PricebookEntry.product2.Family FROM OpportunityLineItems) FROM Opportunity");
		return queries;
	}
	
	@RequestMapping(value="/query/{sobjectName}/soqlbuilder", method=RequestMethod.GET)
	public String buildDynamicSOQLfromCheckBoxes(@PathVariable("sobjectName") String sObjectName, HttpServletRequest request, Map<String, Object> map) throws HttpMessageNotReadableException, IOException
	{
		final ServletServerHttpRequest inputMessage = new ServletServerHttpRequest(request);
		final Map<String, String> formData = new FormHttpMessageConverter().read(null, inputMessage).toSingleValueMap();
		
		//List<String> listOfSelectedColumns = new ArrayList<String>();
		
		/*for (Field listOfField : loginService.LoginToSalesforce().describeSObject(sObjectName).getFields())
		{
			System.out.println(formData.get(listOfField.getName().toString()));
			if (formData.get(listOfField.getName().toString()) != null)
			{
				listOfSelectedColumns.add(listOfField.getName().toString());
			}
			
		}*/
		
		//final String id = formData.get("Id");
		//listOfSelectedColumns.add("laas");
		
		//map.put("sobjectQuery", listOfSelectedColumns);
		
		return "sobject";
	}
	
	
	@RequestMapping(value="/query/download/{sobjectName}", method=RequestMethod.GET)
	public String downloadSObjectToCSV(@PathVariable("sobjectName") String sObjectName, Map<String, Object> map, HttpServletResponse response) throws Exception
	{
		 File file = SObjectUtil.createTempCSVFile(res);
		 
	     response.setContentType("application/csv"); 
	     response.setContentLength(new Long(file.length()).intValue());
	     response.setHeader("Content-Disposition","attachment; filename=CloudSole-" + sObjectName + ".csv");
	 
	     try {
	    	 FileCopyUtils.copy(new FileInputStream(file), response.getOutputStream());
	    	 map.put("success", "Your file was downloaded successfully");
	     } catch (IOException e) {
	         e.printStackTrace();
	         map.put("error", e.getMessage());
	     }
	     finally
	     {
	    	 file.deleteOnExit();
	     }
		
		return "sobject";
	}

	@RequestMapping(value="/view/{sobjectName}", method = RequestMethod.GET)
	 public String viewSObjects(@PathVariable("sobjectName") String sObjectName, Map<String, Object> map){
		try
		{
			for (Field listOfField : loginService.LoginToSalesforce().describeSObject(sObjectName).getFields())
			{
				sObjectFieldNames.add(listOfField.getName().toString());
			}
		
			map.put("sobjectFieldNames", sObjectFieldNames);
			return "sobject";
			
		} catch (Exception e) {
			map.put("error", e.getMessage());
			return "sobject";
		}
	  }
	
	@RequestMapping(value="/query/build", method= RequestMethod.GET)
	public String buildQuery(Map<String, Object> map)
	{
		return "sobject";
	}
	
	@RequestMapping(value="/query/{sobjectName}", method = RequestMethod.GET)
	public String querySObjects(@PathVariable("sobjectName") String sObjectName, Map<String, Object> map) throws HttpMessageNotReadableException, IOException
	{
		try
		{
			sObjectFieldNames = new ArrayList<String>();
			StringBuilder buildSoqlQuery = new StringBuilder();
			buildSoqlQuery.append(SELECT);
		
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession(false);
			session.setAttribute("currentSObject", sObjectName);
		
			sObjectFieldNames.clear();
			for (Field listOfField : loginService.LoginToSalesforce().describeSObject(sObjectName).getFields())
			{	
				buildSoqlQuery.append(listOfField.getName().concat(", "));
				sObjectFieldNames.add(listOfField.getName().toString());
			}
	
			buildSoqlQuery.deleteCharAt(buildSoqlQuery.length()-2);
		
			buildSoqlQuery.append(FROM);
			buildSoqlQuery.append(sObjectName);
		
			map.put("sobjectFieldNamesSOQL", sObjectFieldNames);
			map.put("sobjectQuery", buildSoqlQuery.toString());
			return "sobject";
		} 	catch (Exception e) {
			map.put("error", e.getMessage());
			return "sobject";
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/query/{sobjectName}/{pageNumber}")
	public String querySObjectsReturnViewPaging(@PathVariable("sobjectName") String sObjectName, @PathVariable("pageNumber") Integer pageNumber, HttpServletRequest request, Map<String, Object> map, HttpServletResponse response) throws HttpMessageNotReadableException, IOException
	{
		try
		{
			map.put("sobjectRecords", res.getRecords().subList(100*pageNumber, ((pageNumber+1)*100 > res.getTotalSize()) ? res.getTotalSize() : (pageNumber+1)*100));
			map.put("sobjectFieldNames", res.getRecords().get(0).keySet());
			map.put("pagination", paginationPages);
		}
		catch (Exception e)
		{
			map.put("error", e.getMessage());
		}
		return "sobject";
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/query/{sobjectName}", method = RequestMethod.POST)
	public String querySObjectsReturnView(@PathVariable("sobjectName") String sObjectName, HttpServletRequest request, Map<String, Object> map, HttpServletResponse response) throws HttpMessageNotReadableException, IOException
	{
		try 
		{
			final ServletServerHttpRequest inputMessage = new ServletServerHttpRequest(request);
			final Map<String, String> formData = new FormHttpMessageConverter().read(null, inputMessage).toSingleValueMap();
			final String soqlquery = formData.get("soqlquery");
			Map<String, String> unsortedpaginationPages = new HashMap<String, String>();
				
			if (soqlquery.isEmpty())
			{
				res = new QueryResult<Map>();
				res.setTotalSize(2000);
				res = loginService.LoginToSalesforce().query(soqlquery);
			}

			for (Integer k = 100, pagecounter=Math.abs(res.getTotalSize()/100); k<res.getTotalSize(); k+=PAGESIZE, pagecounter--)
			{
				unsortedpaginationPages.put(pagecounter.toString(), "/login/sobject/query/"+ sObjectName +"/" + pagecounter.toString());
			}
			
			paginationPages = new TreeMap<String, String>(unsortedpaginationPages);
			
			map.put("sobjectRecords",res.getRecords().subList(0, (100 > res.getTotalSize()) ? res.getTotalSize() : 100));
			map.put("sobjectFieldNames", res.getRecords().get(0).keySet());
			map.put("pagination", paginationPages);

			return "sobject";
		
		} catch (Exception e) {
			map.put("error", e.getMessage());
			return "sobject";
		}
	}
	
	@RequestMapping(value="/delete/{sobject}/{sobjectId}")
	public String deleteSObjects(@PathVariable("sobject") String SObject, @PathVariable("sobjectId") String sobjectId, HttpServletRequest request, Map<String, Object> map, HttpServletResponse response)
	{
		try
		{
			loginService.LoginToSalesforce().deleteSObject(SObject, sobjectId);
			map.put("success", "Your record was deleted successfully");
			return "sobject";
		
		} catch (Exception e) {
			map.put("error", e.getMessage());
			return "sobject";
		}
	}
	
	@RequestMapping(value="/create/{sobject}")
	public String createNewObjectView(@PathVariable("sobject") String SObject,  Map<String, Object> map)
	{
		try
		{
			Map<String, String> requiredFieldNames = new HashMap<String, String>();
			Map<String, String> optionalFieldNames = new HashMap<String, String>();
		
			for (Field sobjectRequiredFields : loginService.LoginToSalesforce().describeSObject(SObject).getRequiredFieldsForCreateUpdate())
			{
				requiredFieldNames.put(sobjectRequiredFields.getName().toString(), "");
			}
			for (Field sobjectOptionalFields : loginService.LoginToSalesforce().describeSObject(SObject).getOptionalFieldsForCreateUpdate())
			{
				optionalFieldNames.put(sobjectOptionalFields.getName().toString(), "");
			}
		
			map.put("requiredSobjectFieldNames", requiredFieldNames);
			map.put("optionalSobjectFieldNames", optionalFieldNames);
			return "sobject";
		
		} catch (Exception e) {
				map.put("error", e.getMessage());
				return "sobject";
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/create/{sobject}", method = RequestMethod.POST)
	public String createNewSObject(@PathVariable("sobject") String SObject, HttpServletRequest request, Map<String, Object> map, HttpServletResponse response) throws HttpMessageNotReadableException, IOException
	{
		try
		{
			final ServletServerHttpRequest inputMessage = new ServletServerHttpRequest(request);
			final Map<String, String> formData = new FormHttpMessageConverter().read(null, inputMessage).toSingleValueMap();
			Map<String, String> formFieldMap = new HashMap<String, String>();
			
			for (Field sobjectRequiredFields : loginService.LoginToSalesforce().describeSObject(SObject).getRequiredFieldsForCreateUpdate())
			{
				formFieldMap.put(sobjectRequiredFields.getName().toString(), formData.get(sobjectRequiredFields.getName().toString()));
			}
	
			JSONObject json = new JSONObject();
			json.putAll(formFieldMap);
		 
			ObjectMapper mapper = new ObjectMapper();
			mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
			Object newsobject = (Object) mapper.readValue(json.toJSONString(), Object.class);
			String id = loginService.LoginToSalesforce().createSObject(SObject, newsobject);
			map.put("success", "Your record has been created. Id:" + id);
		 
			return "sobject";
		} catch (Exception e) {
			map.put("error", e.getMessage());
			return "sobject";
		}
	}
	
	@RequestMapping(value="/edit/{sobject}", method=RequestMethod.GET)
	public String editSObjectError(Map<String, Object> map) throws JsonParseException, JsonMappingException, IOException
	{
		map.put("error", "No record selected for edit, please run a query and click the edit button");
		return "sobject";
	}
	
	@RequestMapping(value="/edit/{sobject}/{sobjectId}", method=RequestMethod.POST)
	public String editSObjectView(@PathVariable("sobject") String SObject, @PathVariable("sobjectId") String sobjectId, HttpServletRequest request, Map<String, Object> map, HttpServletResponse response) throws JsonParseException, JsonMappingException, IOException
	{
		try
		{
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession(false);
			session.setAttribute("sobjectRecord", sobjectId);
			
			@SuppressWarnings("unchecked")
			Map<Object, Object> res = (Map<Object, Object>) loginService.LoginToSalesforce().getSObject(SObject, sobjectId).asMap();
			res.remove(String.valueOf("attributes"));
		
			map.put("requiredEditSobjectFieldNames", res);
			
			return "sobject";
			
		} catch (Exception e) {
			map.put("error", e.getMessage());
			return "sobject";
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/save/{sobject}/{sobjectId}", method = RequestMethod.POST)
	public String editSObject(@PathVariable("sobject") String SObject, @PathVariable("sobjectId") String sobjectId, HttpServletRequest request, Map<String, Object> map, HttpServletResponse response) throws JsonParseException, JsonMappingException, IOException
	{
		try
		{
			final ServletServerHttpRequest inputMessage = new ServletServerHttpRequest(request);
			final Map<String, String> formData = new FormHttpMessageConverter().read(null, inputMessage).toSingleValueMap();
			Map<String, String> formFieldMap = new HashMap<String, String>();
		
			for (Field sobjectRequiredFields : loginService.LoginToSalesforce().describeSObject(SObject).getOptionalFieldsForCreateUpdate())
			{
			
					if (!sobjectRequiredFields.getName().contains("Date"))
					{
						if (sobjectRequiredFields.isUpdateable())
						{
							formFieldMap.put(sobjectRequiredFields.getName().toString(), formData.get(sobjectRequiredFields.getName().toString()));
					
						}
					}
			}
					
			JSONObject json = new JSONObject();
			json.putAll(formFieldMap);
			
			ObjectMapper mapper = new ObjectMapper();
			mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_EMPTY);
			mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
			
			Object updatedSObject = (Object) mapper.readValue(json.toJSONString(), Object.class);
			
			loginService.LoginToSalesforce().updateSObject(SObject, sobjectId, updatedSObject);
			map.put("success", "Your record was updated successfully. Id:" + sobjectId);
			
			return "sobject";
		} catch (Exception e) {
			map.put("error", e.getMessage());
			return "sobject";
		}
	}
	
		
}
