package com.example.service;

import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.force.api.ApiConfig;
import com.force.api.ApiSession;
import com.force.api.ForceApi;
import com.force.api.Identity;
import com.force.sdk.oauth.context.ForceSecurityContextHolder;
import com.force.sdk.oauth.context.SecurityContext;

@Service
public class LoginServiceImp implements LoginService {
 	
	@Override
	public ForceApi LoginToSalesforce() 
	{
		SecurityContext sc = ForceSecurityContextHolder.get();
        ApiSession apiSession = new ApiSession();
        apiSession.setAccessToken(sc.getSessionId());
        apiSession.setApiEndpoint(sc.getEndPointHost());

        return new ForceApi(apiSession);
	}
	
	
	@Override
	public Identity getUserLoggedIn() {
		// TODO Auto-generated method stub
		return LoginToSalesforce().getIdentity();
	}
	
	
	
}
