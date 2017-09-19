package com.sap.pm.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

	//@Autowired
	//private DigioService digioService;
	
	@RequestMapping("/hello")
	public String sayHi(){
		
		return "Rest service is up and running";
	}
	
}
