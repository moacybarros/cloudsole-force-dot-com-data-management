package com.example.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.force.api.QueryResult;

public class SObjectUtil {
	
	@SuppressWarnings("unchecked")
	public static File createTempCSVFile(QueryResult<Map> res) throws IOException
	{
		File tmpFile = File.createTempFile("cloudsole", ".csv");
		BufferedWriter writer = new BufferedWriter(new FileWriter(tmpFile));
		//List<Map> records = new ArrayList<Map>();
		Map<String, String> iteratorMap = new HashMap<String, String>();
		if (res != null)
		{
			Set<Object> columnNames = res.getRecords().get(0).keySet();
			for (Object col : columnNames)
			{
				if (!String.valueOf(col).equalsIgnoreCase("attributes") && !String.valueOf(col).equalsIgnoreCase("type") && !String.valueOf(col).equalsIgnoreCase("url"))
				{
					writer.append(String.valueOf(col));
					writer.append(',');
				}
			}
			writer.append('\n');
		
		
		for (int rowcounter=0; rowcounter < res.getRecords().size() ;rowcounter++)
		{
			int colcounter = 0;
			for (Object record : res.getRecords().get(rowcounter).values())
			{
				if (colcounter > 0)
				{
					if (record == null)
					{
						//writer.write("");
						writer.append(',');
					}
					else
					{
						writer.append(String.valueOf(record));
						writer.append(',');
					}
				}
				colcounter++;
			}
				
			writer.append('\n');
		}
		}
			
		writer.flush();
		writer.close();
		return tmpFile;
		
	}
	
}
