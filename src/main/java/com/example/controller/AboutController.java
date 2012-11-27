package com.example.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;

import com.example.model.Person;

public class AboutController {
	
	@RequestMapping("/About")
    public String AboutMe() {
		String me = "Author: Thys Michels";
      
      
        
        return me;
    }

}
