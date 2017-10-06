package com.sap.pm.controller;

import java.util.List;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.triggers.SimpleTriggerImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sap.core.connectivity.api.configuration.DestinationConfiguration;
import com.sap.pm.entity.MetricOriginal;
import com.sap.pm.model.Metrics;
import com.sap.pm.service.MainService;
import com.sap.pm.util.DestinationUtil;

@RestController
public class MainController implements ApplicationContextAware {

	private static ApplicationContext applicationContext;
	
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	@Autowired
	private MainService mainService;
	
	
	@RequestMapping("/startscheduler")
	public String sayHi(){
		Scheduler scheduler = getSchedulerInstance();
		try {
			scheduler.start();
			
			SimpleTriggerImpl trigger =  getTriggerInstance();
			JobDetailImpl jdd = getJobDetailFactoryBean();
			
			scheduler.scheduleJob( jdd, trigger);
			
			
			
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return "Scheduler is started";
	}
	
	
	@RequestMapping("/stop")
	public String sayStop(){
		Scheduler scheduler = getSchedulerInstance();
		try {
			scheduler.standby();
			
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Scheduler is stopped";
	}
	
	@RequestMapping("/testDB")
	public String testDB(){
		String result = "success";
		result = mainService.readDB();
		return result;
	}
	
	@RequestMapping("/registerData")
	public String registerData(){
		String result = "success";
		result = mainService.registerData();
		return result;
	}
	
	@RequestMapping("/metrics")
	public List<Metrics> metrics(){
		List<Metrics> result = null;
		result = mainService.getMetrics();
		return result;
	}
	
	
	
	

	@Override
	public void setApplicationContext(ApplicationContext ac) throws BeansException {
		applicationContext = ac;
	}
	
	private Scheduler getSchedulerInstance() {
		Scheduler scheduler = null;
		try {
			scheduler = (Scheduler) getApplicationContext().getBean("scheduler");
		} catch (BeansException ex) {
			//logger.error("Scheduler Bean", ex.getMessage());
		}
		return scheduler;
	}
	
	private SimpleTriggerImpl getTriggerInstance(){
		SimpleTriggerImpl scheduler = null;
		try {
			scheduler =  (SimpleTriggerImpl) getApplicationContext().getBean("trigger");
		} catch (BeansException ex) {
			//logger.error("Scheduler Bean", ex.getMessage());
		}
		return scheduler;
	}
	
	private JobDetailImpl getJobDetailFactoryBean(){
		JobDetailImpl jd = null;
		try {
			jd =  (JobDetailImpl) getApplicationContext().getBean("jobDetail");
		} catch (BeansException ex) {
			//logger.error("Scheduler Bean", ex.getMessage());
		}
		return jd;
		
	}	
	@RequestMapping("/registerDataSource")
	public String registerDataSource(
			@RequestParam("schemaName")String schemaName,
			@RequestParam("tableName")String tableName){
		String result = "success";
		result = mainService.registerDataSource(schemaName, tableName);
		return result;
	}
	
	@RequestMapping("/forecastMetric")
	public String forecastMetric(){
		String result = "success";
		result = mainService.forecastMetric();
		return result;
	}
	@RequestMapping("/getMetricData")
	public List<MetricOriginal> getMetricData(@RequestParam("metricName")String metricName){
		List<MetricOriginal> result;
		result = mainService.getMetricData(metricName);
		return result;
	}
	
	@RequestMapping("/getDestinationURL")
	public String getDestinationURL(){
		DestinationConfiguration destConfig = DestinationUtil.getDestConfig("ps");
		return destConfig.getProperty("URL");
	}
	
	@RequestMapping("/getMetrics2")
	public String getMetrics2(){		
		return mainService.getMetrics2();
	}
	
}
