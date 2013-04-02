package com.example.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.service.LoginService;

@Controller
@RequestMapping("/about")
public class AboutController {
    
	@Autowired
    private LoginService loginService;
	
	@RequestMapping("")
    public String AboutMe(Map<String, Object> map) 
	{	
		 //map.put("loggedinUser", loginService.LoginToSalesforce().getIdentity());
		 return "about";
    }

}
