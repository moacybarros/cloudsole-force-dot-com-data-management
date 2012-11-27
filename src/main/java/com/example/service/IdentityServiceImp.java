package com.example.service;

import com.force.api.Identity;

public class IdentityServiceImp implements IdentityService {
	
	//inject the bean with constructor-args
	LoginServiceImp loginService = new LoginServiceImp();
	
	@Override
	public Identity getLoggedInUser() 
	{
		return loginService.LoginToSalesforce().getIdentity();
	}


}
