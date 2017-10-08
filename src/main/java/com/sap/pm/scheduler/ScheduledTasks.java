package com.sap.pm.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import ch.qos.logback.classic.Logger;

@Component
public class ScheduledTasks {

    private static final Logger log = (Logger) LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        log.error("The time is now 5 {}", dateFormat.format(new Date()));
    }
    
    @Scheduled(fixedRate = 1000)
    public void reportCurrentTime1() {
        log.error("The time is now 1 {}", dateFormat.format(new Date()));
    }
    
    @Scheduled(fixedRate = 2000)
    public void reportCurrentTime2() {
        log.error("The time is now  2 {}", dateFormat.format(new Date()));
    }
}