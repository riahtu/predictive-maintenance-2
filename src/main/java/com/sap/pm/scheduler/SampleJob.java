package com.sap.pm.scheduler;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sap.pm.service.MainService;
import com.sap.pm.service.MetricsDataService;
import com.sap.pm.service.SampleJobService;

@Component
@Qualifier("job1")
public class SampleJob implements Job {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SampleJobService jobService;
    
    @Autowired
    private MainService mainService;
    
    @Autowired
    private MetricsDataService metricDataService;

    public void execute(JobExecutionContext context) throws JobExecutionException {

        logger.error("Job ** {} ** fired @ {}", context.getJobDetail().getKey().getName(), context.getFireTime());

        //jobService.executeSampleJob();
        //mainService.forecastMetric();
        //mainService.getMetrics2();
        metricDataService.storeMetricsData();
        jobService.executeSampleJob();
        logger.error("Next job scheduled @ {}", context.getNextFireTime());
    }
}
