package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.model.Person;
import com.example.service.LoginService;
import com.example.service.PersonService;

import java.util.Map;

@Controller
public class PersonController {

    @Autowired
    private LoginService loginService;
        
    @Autowired
    private PersonService personService;
    
    @RequestMapping("/")
    public String RocketForce(Map<String, Object> map) 
    {
        map.put("loggedinUser", loginService.getUserLoggedIn());
        //map.put("salesforceUsers", userService.listPeople(map));
        map.put("person", new Person());
        map.put("peopleList", personService.listPeople());
        return "login";
    }
}
