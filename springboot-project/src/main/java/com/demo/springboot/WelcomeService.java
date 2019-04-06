package com.demo.springboot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WelcomeService {
	
	@Value("${welcome.message}")
	private String welcomeMessage;
	
	public String showWelcomeMessage(){
		
		//return "Welcome to first Spring boot application";
		return welcomeMessage;
	}

}
