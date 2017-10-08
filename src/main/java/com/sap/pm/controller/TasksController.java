package com.sap.pm.controller;

import java.util.Timer;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.triggers.SimpleTriggerImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sap.pm.tasks.ForecastingTask1Min;

@RestController
public class TasksController {
	
	@RequestMapping("/hello")
	public String sayHi(){
		
		return "REST API up and running";
	}

	@RequestMapping("/startTask")
	public String startTask(){
		
		Timer time = new Timer();
		ForecastingTask1Min st = new ForecastingTask1Min();		
		time.schedule(st, 0, 1000);
		
		return "job started";
	}
	
	@RequestMapping("/stopTask")
	public String stopTask(){
	
//		boolean isStopped = st.cancel();
//		if(isStopped)
//			return "Job stopped";
//		else
			return "Job not stopped";
	}
}
