package com.example.service;

import java.util.List;
import com.force.api.QueryResult;
import org.springframework.stereotype.Service;

import com.force.api.Identity;
import com.example.model.User;

@Service
public class IdentityServiceImp implements IdentityService {
	
	private LoginServiceImp loginService;
	
	public IdentityServiceImp(){}
	
	public IdentityServiceImp(LoginServiceImp loginService)
	{
		this.loginService = loginService;
	}
	
	public LoginServiceImp getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginServiceImp loginService) {
		this.loginService = loginService;
	}

	@Override
	public Identity getLoggedInUser() 
	{
		return getLoginService().LoginToSalesforce().getIdentity();
	}

	@Override
	public List<User> getAllUsers() 
	{
		QueryResult<User> users  = getLoginService().LoginToSalesforce().query("Select Name from User", User.class);
		return users.getRecords();
	}

	@Override
	public void deleteUser(String id) {
		// TODO Auto-generated method stub
		getLoginService().LoginToSalesforce().deleteSObject("user", id);
	}


}
