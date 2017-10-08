package com.sap.pm.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sap.pm.entity.MetricData15Min;
import com.sap.pm.entity.MetricData1Min;
import com.sap.pm.entity.MetricData60Min;
import com.sap.pm.repository.MetricData15MinRepository;
import com.sap.pm.repository.MetricData1MinRepository;
import com.sap.pm.repository.MetricData60MinRepository;
import com.sap.pm.util.CommonUtils;

@Service
public class EnsembleService {
	
	@Autowired 
	MetricData1MinRepository metricData1MinRepository;
	
	@Autowired 
	MetricData15MinRepository metricData15MinRepository;
	
	@Autowired 
	MetricData60MinRepository metricData60MinRepository;
	
	public String doEnsembling(String metricName){
		Date fromDate = new Date();
		Date toDate = CommonUtils.addMinutesToDate(fromDate, 10);
		
		if("cpu".equals(metricName)){
			List<MetricData1Min> metricData1Mins = metricData1MinRepository.retrieveData(fromDate, toDate);
			for(int i=0; i<metricData1Mins.size(); i++ ){
				MetricData1Min metricData1Min = metricData1Mins.get(i);
				
				Date currentDate = metricData1Min.getDate();
				
				double mape1 = metricData1Min.getCpuUsageMape();
				double mape2 = 1.0;
				double mape3 = 1.0;
				
				double value1 = metricData1Min.getDiskUsageForecast();
				double value2 = 0.0;
				double value3 = 0.0;
				List<MetricData15Min> metricData15Mins = metricData15MinRepository.getDateGreaterthanCurrentDate(currentDate);
				if(metricData15Mins.size() >= 1){
					MetricData15Min metricData15Min = metricData15Mins.get(metricData15Mins.size()-1);
					value2 = metricData15Min.getCpuUsageForecast();
					mape2 = metricData15Min.getCpuUsageMape();
					if(mape2 > 1.0)
						mape2 = 1.0;
				}
				
				List<MetricData60Min> metricData60Mins = metricData60MinRepository.getDateGreaterthanCurrentDate(currentDate);
				if(metricData60Mins.size() >= 1){
					MetricData60Min metricData60Min = metricData60Mins.get(metricData60Mins.size()-1);
					value3 = metricData60Min.getCpuUsageForecast();
					mape3 = metricData60Min.getCpuUsageMape();
					if(mape3 > 1.0)
						mape3 = 1.0;
				}
				
				double mape1N = (1-mape1);
				double mape2N = (1-mape2);
				double mape3N = (1-mape3);
						
				double denominator = mape1N + mape2N + mape3N;
				
				double numartor = mape1N*value1 + mape2N*value2 + mape3N*value3;
				
				double finalForecast = numartor/denominator;
				metricData1Min.setCpuUsageEnsembleForecast(finalForecast);
				
				metricData1MinRepository.save(metricData1Min);
			}
			
		}else if("ram".equals(metricName)){
			
		}else if("disk".equals(metricName)){
			
		} 
		
		return "success";
	}
}
