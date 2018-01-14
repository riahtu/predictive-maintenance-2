/**
 * 
 */
package com.sap.pm.scheduler.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.sap.pm.util.AppLogger;

/**
 *
 */
@DisallowConcurrentExecution
public class DynamicJob  implements Job {
	
	private final static AppLogger logger = AppLogger.getInstance();
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		logger.info("----- Running Dynamic Job With Simple Trigger ------");
	}

}
