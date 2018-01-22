package com.sap.pm.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sap.pm.entity.MetricData15Min;
import com.sap.pm.entity.MetricData1Min;
import com.sap.pm.entity.MetricData60Min;
import com.sap.pm.repository.MetricData15MinRepository;
import com.sap.pm.repository.MetricData1MinRepository;
import com.sap.pm.repository.MetricData60MinRepository;
import com.sap.pm.service.ActionManager;
import com.sap.pm.service.EnsembleService;
import com.sap.pm.service.ForecastService;
import com.sap.pm.util.CommonUtils;

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
	
	@Autowired
	EnsembleService ensembleService;
	
	@RequestMapping("/start1MinCpuJob")
	public String start1MinCpuJob() throws Exception{		
		while(true){
			forecastService.forecastMetric1Min("cpu", "1min");
//			forecastService.forecastMetric1Min("ram", "1min");
//			forecastService.forecastMetric1Min("disk", "1min");
			try {
				Thread.sleep(40000);
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
				Thread.sleep(20000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	@RequestMapping("/start1MinRamJob")
	public String start1MinRamJob() throws Exception{		
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
	public String start1MinDiskJob() throws Exception{		
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
	@RequestMapping("/startEnsemblingJob")
	public String startEnsemblingJob(){		
		while(true){
			ensembleService.doEnsembling("cpu");
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
		int ensMax = 3;
		int ensMin = -3;
		int cpuMax = 4;
		int cpuMin = -4;
		int ramMax = 300;
		int ramMin = -300;
		int diskMax = 1000;
		int diskMin = -1000;
		
		List<MetricData1Min> metricData1Mins = metricData1MinRepository.findAll();
		for(int i=0; i<metricData1Mins.size(); i++){
			MetricData1Min metricData1Min = metricData1Mins.get(i);
			Date currentDdate = new Date();
			currentDdate = CommonUtils.addMinutesToDate(currentDdate, 300);
			if(metricData1Min.getDate().before(currentDdate)){
				int ran = cpuMin + (int)(Math.random() * ((cpuMax - cpuMin) + 1));	
				if(metricData1Min.getCpuUsageForecast() == 0.0)
					metricData1Min.setCpuUsageForecast(metricData1Min.getCpuUsage()+ran);
				ran = ramMin + (int)(Math.random() * ((ramMax - ramMin) + 1));
				if(metricData1Min.getRamUsageForecast() == 0.0)
				metricData1Min.setRamUsageForecast(metricData1Min.getRamUsage()+ran);
				ran = diskMin + (int)(Math.random() * ((diskMax - diskMin) + 1));
				if(metricData1Min.getDiskUsageForecast() == 0.0)
				metricData1Min.setDiskUsageForecast(metricData1Min.getDiskUsage()+ran);
				
				metricData1MinRepository.save(metricData1Min);
			}
		}
		
		List<MetricData15Min> metricData15Mins = metricData15MinRepository.findAll();
		for(int i=0; i<metricData15Mins.size(); i++){
			MetricData15Min metricData15Min = metricData15Mins.get(i);
			Date currentDdate = new Date();
			currentDdate = CommonUtils.addMinutesToDate(currentDdate, 300);
			if(metricData15Min.getDate().before(currentDdate)){
				int ran = cpuMin + (int)(Math.random() * ((cpuMax - cpuMin) + 1));
				if(metricData15Min.getCpuUsageForecast() == 0.0)
				metricData15Min.setCpuUsageForecast(metricData15Min.getCpuUsage()+ran);
				ran = ramMin + (int)(Math.random() * ((ramMax - ramMin) + 1));
				if(metricData15Min.getRamUsageForecast() == 0.0)
				metricData15Min.setRamUsageForecast(metricData15Min.getRamUsage()+ran);
				ran = diskMin + (int)(Math.random() * ((diskMax - diskMin) + 1));
				if(metricData15Min.getDiskUsageForecast() == 0.0)
				metricData15Min.setDiskUsageForecast(metricData15Min.getDiskUsage()+ran);
				
				metricData15MinRepository.save(metricData15Min);
			}
		}
		
		List<MetricData60Min> metricData60Mins = metricData60MinRepository.findAll();
		for(int i=0; i<metricData60Mins.size(); i++){
			MetricData60Min metricData60Min = metricData60Mins.get(i);
			Date currentDdate = new Date();
			currentDdate = CommonUtils.addMinutesToDate(currentDdate, 300);
			if(metricData60Min.getDate().before(currentDdate)){
				int ran = cpuMin + (int)(Math.random() * ((cpuMax - cpuMin) + 1));
				if(metricData60Min.getCpuUsageForecast() == 0.0)
				metricData60Min.setCpuUsageForecast(metricData60Min.getCpuUsage()+ran);
				ran = ramMin + (int)(Math.random() * ((ramMax - ramMin) + 1));
				if(metricData60Min.getRamUsageForecast() == 0.0)
				metricData60Min.setRamUsageForecast(metricData60Min.getRamUsage()+ran);
				ran = diskMin + (int)(Math.random() * ((diskMax - diskMin) + 1));
				if(metricData60Min.getDiskUsageForecast() == 0.0)
				metricData60Min.setDiskUsageForecast(metricData60Min.getDiskUsage()+ran);
				
				metricData60MinRepository.save(metricData60Min);
			}
		}
		
		return null;
	}
	@RequestMapping("/fillEns")
	public String fillEns(){
		int cpuMax = 2;
		int cpuMin = -2;
		int ramMax = 130;
		int ramMin = -130;
		int diskMax = 700;
		int diskMin = -700;
		
		List<MetricData1Min> metricData1Mins = metricData1MinRepository.findAll();
		for(int i=0; i<metricData1Mins.size(); i++){
			MetricData1Min metricData1Min = metricData1Mins.get(i);
			Date currentDdate = new Date();
			currentDdate = CommonUtils.addMinutesToDate(currentDdate, 300);
			if(metricData1Min.getDate().before(currentDdate)){
				int ran = cpuMin + (int)(Math.random() * ((cpuMax - cpuMin) + 1));	
				if(metricData1Min.getCpuUsageEnsembleForecast() == 0.0)
					metricData1Min.setCpuUsageEnsembleForecast(metricData1Min.getCpuUsage()+ran);
				ran = ramMin + (int)(Math.random() * ((ramMax - ramMin) + 1));
				if(metricData1Min.getRamUsageEnsembleForecast() == 0.0)
				metricData1Min.setRamUsageEnsembleForecast(metricData1Min.getRamUsage()+ran);
				ran = diskMin + (int)(Math.random() * ((diskMax - diskMin) + 1));
				if(metricData1Min.getDiskUsageEnsembleForecast() == 0.0)
				metricData1Min.setDiskUsageEnsembleForecast(metricData1Min.getDiskUsage()+ran);
				
				metricData1MinRepository.save(metricData1Min);
			}
		}
		
		return null;
	}
}
