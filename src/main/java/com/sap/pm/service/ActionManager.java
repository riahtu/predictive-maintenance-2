package com.sap.pm.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sap.pm.entity.ActionTemplate;
import com.sap.pm.entity.MetricData1Min;
import com.sap.pm.entity.TActionManager;
import com.sap.pm.entity.TConfig;
import com.sap.pm.pojo.Metric;
import com.sap.pm.repository.ActionTemplateRepository;
import com.sap.pm.repository.MetricData1MinRepository;
import com.sap.pm.repository.TActionManagerRepository;
import com.sap.pm.repository.TConfigRepository;
import com.sap.pm.util.CommonUtils;

@Component
public class ActionManager {
	
	@Autowired
	TConfigRepository configRepository;
	
	@Autowired
	TActionManagerRepository actionManagerRepository;
	
	@Autowired
	ActionTemplateRepository actionTemplateRepository;
	
	@Autowired
	MetricData1MinRepository metricData1MinRepository;
	
	public void saveActionTemplate(String metricType) {

		Date fromDate = new Date();
		Date toDate = CommonUtils.addMinutesToDate(fromDate,10);
		
		List<MetricData1Min> metricList = metricData1MinRepository.retrieveData(fromDate, toDate);
		
		if(metricType.equals("cpu")){
			double avg = 0;
			for(MetricData1Min metric : metricList){
				avg = (avg+metric.getCpuUsageEnsembleForecast())/2;
			}
			
			TConfig config = configRepository.findByMetrictype("cpu");
			//TActionManager actionManager = actionManagerRepository.findByMetrictype("cpu");
			if (avg > config.getMetric_threshold()) {
				ActionTemplate actionTemplate = new ActionTemplate();
				actionTemplate.setCriticality(1);
				actionTemplate.setName("cpu");
				actionTemplate.setTime(toDate);
				actionTemplate.setType("ADDED");
				String message = "Server metric " + "cpu " +"has seen a recording of " + avg + " and an "
						+ "action of "+ "ADDED" +" is triggered.Additional comments : ";					
				actionTemplate.setDescription(message);
				actionTemplateRepository.save(actionTemplate);
			}
		}
		
		if(metricType.equals("disk")){
			double avg = 0;
			for(MetricData1Min metric : metricList){
				avg = (avg+metric.getDiskUsageEnsembleForecast())/2;
			}
			
			TConfig config = configRepository.findByMetrictype("disk");
			TActionManager actionManager = actionManagerRepository.findByMetrictype("disk");
			if (avg > config.getMetric_threshold()) {
				ActionTemplate actionTemplate = new ActionTemplate();
				actionTemplate.setCriticality(1);
				actionTemplate.setName("disk");
				actionTemplate.setTime(toDate);
				actionTemplate.setType("ADDED");
				String message = "Server metric " + "disk " +"has seen a recording of " + avg + " and an "
						+ "action of "+ actionManager.getActionType() +" is triggered.Additional comments : ";					
				actionTemplate.setDescription(message);
				actionTemplateRepository.save(actionTemplate);
			}
		}
		
		if(metricType.equals("ram")){
			double avg = 0;
			for(MetricData1Min metric : metricList){
				avg = (avg+metric.getRamUsageEnsembleForecast())/2;
			}
			
			TConfig config = configRepository.findByMetrictype("ram");
			TActionManager actionManager = actionManagerRepository.findByMetrictype("ram");
			if (avg > config.getMetric_threshold()) {
				ActionTemplate actionTemplate = new ActionTemplate();
				actionTemplate.setCriticality(1);
				actionTemplate.setName("ram");
				actionTemplate.setTime(toDate);
				actionTemplate.setType("ADDED");
				String message = "Server metric " + " ram " +"has seen a recording of " + avg + " and an "
						+ "action of "+ actionManager.getActionType() +" is triggered.Additional comments : ";					
				actionTemplate.setDescription(message);
				actionTemplateRepository.save(actionTemplate);
			}
		}
		
		
		
		/*for (com.sap.pm.pojo.Process process : accountMetric.getProcesses()) {
			for (Metric metric : process.getMetrics()) {
				TConfig config = configRepository.findByMetrictype(metric.getName());
				TActionManager actionManager = actionManagerRepository.findByMetrictype(metric.getName());
				if (metric.getValue() > config.getMetric_threshold()) {
					ActionTemplate actionTemplate = new ActionTemplate();
					actionTemplate.setCriticality(actionManager.getCriticality());
					actionTemplate.setName(actionManager.getMetrictype());
					actionTemplate.setTime(new Date());
					actionTemplate.setType(actionManager.getActionType());
					String message = "Server metric" + metric.getMetricType() +"has seen a recording of" +metric.getValue() + "and an "
							+ "action of "+ actionManager.getActionType() +" is triggered.Additional comments : ";					
					actionTemplate.setDescription(message);
					actionTemplateRepository.save(actionTemplate);
				}
			}
		}*/
	}

}
