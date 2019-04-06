package com.demo.springboot;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.springboot.configuration.BasicConfiguration;

@RestController
public class WelcomeController {
	
	@Autowired
	WelcomeService welcomeService;
	
	@Autowired
	private BasicConfiguration configuration;
	
	@RequestMapping("/welcome")
	public String showWelcomeMessage(){
		
		return welcomeService.showWelcomeMessage();
	}
	
	@RequestMapping("/dynamic-configuration")
	public Map dynamicConfiguration() {
		Map map = new HashMap();
		map.put("message", configuration.getMessage());
		map.put("number", configuration.getNumber());
		map.put("value", configuration.isValue());

		return map;
	}


}
