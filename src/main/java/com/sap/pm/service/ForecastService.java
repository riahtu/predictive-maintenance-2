package com.sap.pm.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sap.pm.entity.MetricInfo;
import com.sap.pm.repository.MetricInfoRepository;

@Service
public class ForecastService {
	
	
	@Autowired 
	MetricInfoRepository metricInfoRepo;
	
	void insertInfluencerData(){
		
		List<MetricInfo> list = metricInfoRepo.findAll();
		
		
		for(MetricInfo metricInfo : list){
			Date date = metricInfo.getDate();
			
			
		}
		
	}

}
