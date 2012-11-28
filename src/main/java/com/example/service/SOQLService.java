package com.example.service;

import java.util.List;

public interface SOQLService 
{
	public List<String> runSOQLQuery(String soqlQuery);
	public List<String> runSOQLQuery(String sObject, String fields, String limit, String group);
}
