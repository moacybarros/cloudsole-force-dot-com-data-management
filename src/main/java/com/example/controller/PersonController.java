package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.service.PersonService;

import java.util.Map;

@Controller
public class PersonController {

    @Autowired
    private PersonService personService;

    @RequestMapping("/")
    public String listPeople(Map<String, Object> map) {

       // map.put("person", new Person());
       // map.put("peopleList", personService.listPeople());
        map.put("loggedinUser", personService.getUserLoggedIn());
        
        return "login";
    }
}
