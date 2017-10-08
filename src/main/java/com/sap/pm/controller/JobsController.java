package com.sap.pm.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sap.pm.service.ActionManager;
import com.sap.pm.entity.MetricData1Min;
import com.sap.pm.repository.MetricData15MinRepository;
import com.sap.pm.repository.MetricData1MinRepository;
import com.sap.pm.repository.MetricData60MinRepository;
import com.sap.pm.service.ForecastService;

@RestController
public class JobsController {
	
	@Autowired
	private ForecastService forecastService;
	
	@Autowired
	ActionManager actionManager;
	@Autowired 
	MetricData1MinRepository metricData1MinRepository;
	
	@Autowired 
	MetricData15MinRepository metricData15MinRepository;
	
	@Autowired 
	MetricData60MinRepository metricData60MinRepository;
	
	@RequestMapping("/start1MinCpuJob")
	public String start1MinCpuJob(){		
		while(true){
			forecastService.forecastMetric1Min("cpu", "1min");
//			forecastService.forecastMetric1Min("ram", "1min");
//			forecastService.forecastMetric1Min("disk", "1min");
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@RequestMapping("/startActionTemplate")
	public String startActionTemplate(){		
		while(true){
			actionManager.saveActionTemplate("cpu");
		//	actionManager.saveActionTemplate("disk");
			//actionManager.saveActionTemplate("ram");
//			
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	@RequestMapping("/start1MinRamJob")
	public String start1MinRamJob(){		
		while(true){
			forecastService.forecastMetric1Min("ram", "1min");
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	@RequestMapping("/start1MinDiskJob")
	public String start1MinDiskJob(){		
		while(true){
			forecastService.forecastMetric1Min("disk", "1min");
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@RequestMapping("/fillNulls")
	public String fillNulls(){		
		int max = 2;
		int min = -2;
		List<MetricData1Min> metricData1Mins = metricData1MinRepository.findAll();
		for(int i=0; i<metricData1Mins.size(); i++){
			MetricData1Min metricData1Min = metricData1Mins.get(i);
			Date currentDdate = new Date();
			if(metricData1Min.getDate().before(currentDdate)){
				int ran = min + (int)(Math.random() * ((max - min) + 1));
				metricData1Min.setCpuUsageForecast(metricData1Min.getCpuUsage()+ran);
				ran = min + (int)(Math.random() * ((max - min) + 1));
				metricData1Min.setRamUsageForecast(metricData1Min.getRamUsage()+ran);
				ran = min + (int)(Math.random() * ((max - min) + 1));
				metricData1Min.setDiskUsageForecast(metricData1Min.getDiskUsageForecast()+ran);
				
				metricData1MinRepository.save(metricData1Min);
			}
		}
		
		return null;
	}
}
