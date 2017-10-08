package com.sap.pm.tasks;

import java.util.Date;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;

import com.sap.pm.entity.MetricData15Min;
import com.sap.pm.entity.MetricData15Min2;
import com.sap.pm.repository.MetricData15Min2Repository;
import com.sap.pm.repository.MetricData15MinRepository;


public class ForecastingTask1Min extends TimerTask {

	@Autowired 
	MetricData15Min2Repository metricData15Min2Repository;
	Date now; // to display current time

	// Add your task here
	public void run() {
		now = new Date(); // initialize date
		System.out.println("Time is :" + now); // Display current time
		MetricData15Min2 metricData15Min2 = new MetricData15Min2();
		metricData15Min2.setDate(new Date());
		metricData15Min2.setCpuUsage(10.0);
		metricData15Min2.setCpuUsageEnsembleForecast(11.0);
		metricData15Min2.setCpuUsageMape(0.23);
		metricData15Min2.setIsWeekDay(1);
		
		metricData15Min2Repository.save(metricData15Min2);
	}
}