package com.sap.pm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;





@RestController
public class SchedulerController {

//	@Autowired
	private ScheduledTasks scheduledTasks;
	
	@RequestMapping("/start")
	public String sayHi(@PathVariable String input){
		
		if(input.equals("start")){
			scheduledTasks.reportCurrentTime();
		}
		
		return "Rest service is up and running";
	}
	
}
