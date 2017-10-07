package com.sap.pm.service;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class SampleJobService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Async
    public void executeSampleJob() {

        logger.info("The sample job has begun...");
        try {
        	TimeUnit.MINUTES.sleep(10);
        } catch (InterruptedException e) {
            logger.error("Error while executing sample job", e);
        } finally {
            logger.error("Sample job has finished...");
        }
    }
}
