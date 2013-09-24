package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.force.api.ApiConfig;
import com.force.api.ApiSession;
import com.force.api.DescribeGlobal;
import com.force.api.DescribeSObjectBasic;
import com.force.api.ForceApi;
import com.force.api.Identity;
import com.force.sdk.oauth.context.ForceSecurityContextHolder;
import com.force.sdk.oauth.context.SecurityContext;

@Service
public class LoginServiceImp implements LoginService {
 	
	private static List<String> sObjectNames = new ArrayList<String>();
	private static String loggedInUserName;
	
	//private static String sessionId = ForceSecurityContextHolder.get().getSessionId();
	//private static String endPoint = ForceSecurityContextHolder.get().getEndPointHost();
	
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
	public List<String> showSObjects() {
		if (sObjectNames.isEmpty())
		{
			for (DescribeSObjectBasic describeObject : LoginToSalesforce().describeGlobal().getSObjects())
			{
				if (describeObject.isQueryable())
					sObjectNames.add(describeObject.getName());
			}
			return sObjectNames;
		}
		else
		{
			return sObjectNames;
		}
	
	}
	
	public static String getSessionId() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession(false); //create a new session
		return session.getAttribute("sessionId").toString();
	}

	public static String getEndpointURL() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession(false); //create a new session
		return session.getAttribute("endpoint").toString();
	}
	
	
	
}
