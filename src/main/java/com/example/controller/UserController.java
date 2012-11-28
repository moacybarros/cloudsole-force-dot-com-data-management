package com.example.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.model.User;
import com.example.service.IdentityService;

@Controller
public class UserController 
{
	@Autowired
    private IdentityService identityService;
    
	@RequestMapping("/identity")
    public String listPeople(Map<String, Object> map) {
		map.put("user", new User());
        map.put("userList", identityService.getAllUsers());
        return "identity";
    }
	
	@RequestMapping("/identity/delete/{userId}")
    public String deleteUser(@PathVariable("userId") String userId) {

		identityService.deleteUser(userId);

        return "redirect:/login";
    }
}
