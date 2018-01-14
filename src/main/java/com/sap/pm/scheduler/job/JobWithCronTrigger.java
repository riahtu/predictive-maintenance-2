/**
 * 
 */
package com.sap.pm.scheduler.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.stereotype.Component;

import com.sap.pm.scheduler.quartz.ConfigureQuartz;
import com.sap.pm.service.ForecastService;
import com.sap.pm.util.AppLogger;

/**
 */
@Component
@DisallowConcurrentExecution
public class JobWithCronTrigger implements Job {

	private final static Logger logger = LoggerFactory.getLogger(JobWithCronTrigger.class);

	@Value("0/7 * * * * ?")
	private String frequency;
	
	@Autowired
	private ForecastService forecastService;

	@Override
	public void execute(JobExecutionContext jobExecutionContext) {
		logger.error("Running JobWithCronTrigger | frequency {}", frequency);
		forecastService.forecastMetric1Min("CPU_USAGE", "1min");
	}

	@Bean(name = "jobWithCronTriggerBean")
	public JobDetailFactoryBean sampleJob() {
		return ConfigureQuartz.createJobDetail(this.getClass());
	}

	@Bean(name = "jobWithCronTriggerBeanTrigger")
	public CronTriggerFactoryBean sampleJobTrigger(@Qualifier("jobWithCronTriggerBean") JobDetail jobDetail) {
		return ConfigureQuartz.createCronTrigger(jobDetail, frequency);
	}
}
