/**
 * 
 */
package com.sap.pm.scheduler.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.stereotype.Component;

import com.sap.pm.scheduler.quartz.ConfigureQuartz;
import com.sap.pm.util.AppLogger;

/**
 */
@Component
@DisallowConcurrentExecution
public class JobWithSimpleTrigger implements Job {

	private final static AppLogger logger = AppLogger.getInstance();
	
	@Value("2000")
    private long frequency;

	@Override
	public void execute(JobExecutionContext jobExecutionContext) {
		logger.info("Running JobWithSimpleTrigger | frequency {}", frequency);
	}
	
	@Bean(name = "jobWithSimpleTriggerBean")
    public JobDetailFactoryBean sampleJob() {
        return ConfigureQuartz.createJobDetail(this.getClass());
    }

    @Bean(name = "jobWithSimpleTriggerBeanTrigger")
    public SimpleTriggerFactoryBean sampleJobTrigger(@Qualifier("jobWithSimpleTriggerBean") JobDetail jobDetail) {
    	return ConfigureQuartz.createTrigger(jobDetail,frequency);
    }
}
