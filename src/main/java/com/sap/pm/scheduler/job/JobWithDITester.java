package com.sap.pm.scheduler.job;

import javax.inject.Inject;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.stereotype.Component;

import com.sap.pm.scheduler.quartz.ConfigureQuartz;
import com.sap.pm.util.AppLogger;

@Component
@DisallowConcurrentExecution
public class JobWithDITester implements Job  {
	
	private final static AppLogger logger = AppLogger.getInstance();

	@Value("0/11 * * * * ?")
	private String frequency;

	@Inject
	private ArbitraryDependency fieldInjectDependency;
	

	@Override
	public void execute(JobExecutionContext jobExecutionContext) {
		logger.error("Running JobWithDITester | frequency {}", frequency);
		if (null != fieldInjectDependency) {
			logger.error("ArbitraryDependency = " + fieldInjectDependency.toString());
		}
	}
	
	@Bean(name = "jobWithDITesterBean")
	public JobDetailFactoryBean sampleJob() {
		return ConfigureQuartz.createJobDetail(this.getClass());
	}
	
	@Bean(name = "jobWithDITesterBeanTrigger")
	public CronTriggerFactoryBean sampleJobTrigger(@Qualifier("jobWithDITesterBean") JobDetailFactoryBean jdfb ) {
		return ConfigureQuartz.createCronTrigger(jdfb.getObject(), frequency);
	}

}
