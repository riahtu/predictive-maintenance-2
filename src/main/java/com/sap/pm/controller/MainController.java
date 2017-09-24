package com.sap.pm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sap.pm.service.MainService;

@RestController
public class MainController {

	@Autowired
	private MainService mainService;
	
	@RequestMapping("/hello")
	public String sayHi(){
		
		return "Rest service is up and running";
	}
	
	@RequestMapping("/testDB")
	public String testDB(){
		String result = "success";
		result = mainService.readDB();
		return result;
	}
	
	@RequestMapping("/testDestination")
	public String testDestination(){
		String result = "success";
		result = mainService.readDB();
		return result;
	}
}
