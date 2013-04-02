package com.example.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/contact")
public class ContactController 
{
	@Autowired
	private MailSender mailSender;
	
	@RequestMapping("")
    public String sendContactPage(Map<String, Object> map)
	{
		return "contact";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "", method = RequestMethod.POST)
    public String sendContactEmail(HttpServletRequest request, Map<String, Object> map, HttpServletResponse response) throws HttpMessageNotReadableException, IOException {
		
		final ServletServerHttpRequest inputMessage = new ServletServerHttpRequest(request);
		final Map<String, String> formData = new FormHttpMessageConverter().read(null, inputMessage).toSingleValueMap();
		final String email = formData.get("email");
		final String subject = formData.get("subject");
		final String body = formData.get("body");
		
		SimpleMailMessage message = new SimpleMailMessage();
		 
		message.setFrom(email);
		message.setTo("thysmichels@gmail.com");
		message.setSubject(subject);
		message.setText(body);
		
		mailSender.send(message);
        return "redirect:/login/";
    }
}
