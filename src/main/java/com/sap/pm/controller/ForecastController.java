package com.sap.pm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sap.pm.pojo.ForecastResponse;
import com.sap.pm.service.ForecastService;

@RestController
public class ForecastController {

	@Autowired
	private ForecastService forecastService;
	
	@RequestMapping("/forecastMetric1Min")
	public ForecastResponse forecastMetric1Min(
			@RequestParam("metricName")String metricName,
			@RequestParam("granularity")String granularity){
		ForecastResponse result;
		result = forecastService.forecastMetric1Min(metricName, granularity);
		return result;
	}
}
