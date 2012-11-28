package com.example.service;

import java.util.List;

public interface UserService 
{
	public List<String> getSalesforceUsers();
	public List<String> updateSalesforceUser(String userId);
	public List<String> deleteSalesforceUser(String userId);
	public void addSalesforceUser(String firstName, String lastName, String emailAddress);
}
