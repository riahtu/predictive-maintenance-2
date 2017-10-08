package com.sap.pm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sap.pm.service.ForecastService;

@RestController
public class JobsController {
	
	@Autowired
	private ForecastService forecastService;
	
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
}
