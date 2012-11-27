package com.example.service;

import com.force.api.ApiSession;
import com.force.api.ForceApi;
import com.force.sdk.oauth.context.ForceSecurityContextHolder;
import com.force.sdk.oauth.context.SecurityContext;

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

}
