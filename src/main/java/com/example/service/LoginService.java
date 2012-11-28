package com.example.service;
import com.force.api.ForceApi;
import com.force.api.Identity;

public interface LoginService 
{
	public ForceApi LoginToSalesforce();
	public Identity getUserLoggedIn();
}
