package com.sap.pm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sap.pm.entity.ActionTemplate;
import com.sap.pm.repository.ActionTemplateRepository;
import com.sap.pm.repository.MetricData1MinRepository;


@Service
public class ActionManagerService {
	@Autowired
	ActionTemplateRepository actionTemplateRepository;
	
	public List<ActionTemplate> getActionNotification(Long id){
		if(null == id){
			List<ActionTemplate> actionList = actionTemplateRepository.findAll();
			return actionList;
		}else{
			List<ActionTemplate> actionList = new ArrayList<ActionTemplate>();
			ActionTemplate action =  actionTemplateRepository.findOne(id);
			if(null != action){
				actionList.add(action);
			}
			return actionList;
		}
	}

}
