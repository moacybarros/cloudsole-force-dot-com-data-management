package com.example.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.model.Person;

@Controller
public class AboutController {
	
	@RequestMapping("/about")
    public String AboutMe(Map<String, String> map) 
	{	
		map.put("Author", "Thys Michels");
		map.put("Email", "thysmichels@gmail.com");
		map.put("Website", "http://thysmichels.com");
        return "about";
    }

}
