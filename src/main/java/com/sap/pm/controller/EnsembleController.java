package com.sap.pm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sap.pm.service.EnsembleService;

@RestController
public class EnsembleController {

	@Autowired
	EnsembleService ensembleService;
	
	@RequestMapping("/startEnsembling")
	public String startEnsembling(
			@RequestParam("metricName")String metricName){
		String result;
		result = ensembleService.doEnsembling(metricName);
		return result;
	}
}
