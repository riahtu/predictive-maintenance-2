package com.sap.pm.scheduler.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sap.pm.service.ForecastService;

public class SampleJob implements Job {
    @Autowired
    private SampleService service;
    
    @Autowired
    private ForecastService forecastservice;
    
    private static final Logger log = LoggerFactory.getLogger(SampleJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
       // service.hello();
    	log.error("In job execute");
        try {
			forecastservice.forecastMetric1Min("cpu", "1min");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
