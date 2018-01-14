package com.sap.pm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sap.pm.entity.OnboardingData;
import com.sap.pm.repository.OnboardingDataRepository;

@Service
public class OnboardingService {

	@Autowired
	private OnboardingDataRepository onboardingDataRepository;
	
	public ResponseEntity saveOnboaringAppData(OnboardingData onboardingData){
		
		onboardingDataRepository.save(onboardingData);
		
		return new ResponseEntity("Success", HttpStatus.OK);
	}
}
