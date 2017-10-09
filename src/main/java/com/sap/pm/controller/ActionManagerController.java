package com.sap.pm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sap.pm.entity.ActionTemplate;
import com.sap.pm.repository.MetricData1MinRepository;
import com.sap.pm.service.ActionManagerService;

@RestController
public class ActionManagerController {

	@Autowired 
	ActionManagerService actionManagerService;
	
	@RequestMapping("/getActionNotification")
	public List<ActionTemplate> getActionNotification(
			@RequestParam(value="id", required=false) Long id){		

		return actionManagerService.getActionNotification(id);
	}
}
