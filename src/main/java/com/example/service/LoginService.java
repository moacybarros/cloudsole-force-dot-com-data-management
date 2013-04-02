package com.example.service;
import java.util.List;

import com.force.api.DescribeSObjectBasic;
import com.force.api.ForceApi;
import com.force.api.Identity;

public interface LoginService 
{
	public ForceApi LoginToSalesforce();
	public List<String> showSObjects();
}
