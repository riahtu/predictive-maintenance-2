package com.sap.pm.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sap.pm.entity.ActionTemplate;
import com.sap.pm.entity.TActionManager;
import com.sap.pm.entity.TConfig;
import com.sap.pm.pojo.AccountMetric;
import com.sap.pm.pojo.Metric;
import com.sap.pm.repository.ActionTemplateRepository;
import com.sap.pm.repository.TActionManagerRepository;
import com.sap.pm.repository.TConfigRepository;

@Component
public class ActionManager {
	
	@Autowired
	TConfigRepository configRepository;
	
	@Autowired
	TActionManagerRepository actionManagerRepository;
	
	@Autowired
	ActionTemplateRepository actionTemplateRepository;
	
	public void saveActionTemplate(AccountMetric accountMetric) {

		for (com.sap.pm.pojo.Process process : accountMetric.getProcesses()) {
			for (Metric metric : process.getMetrics()) {
				TConfig config = configRepository.findByMetrictype(metric.getName());
				TActionManager actionManager = actionManagerRepository.findByMetricType(metric.getName());
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
		}
	}

}
