package com.sap.pm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sap.pm.entity.OnboardingData;
import com.sap.pm.service.OnboardingService;

@RestController
public class OnboardingController {

	@Autowired
	private OnboardingService onboardingService;
	
	@RequestMapping("/saveOnboaringAppData")
	public ResponseEntity saveOnboaringAppData(
			@RequestBody OnboardingData onboardingData){
		
		return onboardingService.saveOnboaringAppData(onboardingData);
	}
}
