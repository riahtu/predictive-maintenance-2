package com.sap.pm.scheduler;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sap.pm.service.MainService;
import com.sap.pm.service.SampleJobService;

@Component
public class SampleJob implements Job {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SampleJobService jobService;
    
    @Autowired
    private MainService mainService;

    public void execute(JobExecutionContext context) throws JobExecutionException {

        logger.info("Job ** {} ** fired @ {}", context.getJobDetail().getKey().getName(), context.getFireTime());

        //jobService.executeSampleJob();
        mainService.forecastMetric();

        logger.info("Next job scheduled @ {}", context.getNextFireTime());
    }
}
