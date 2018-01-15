package com.sap.pm.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.sap.core.connectivity.api.authentication.AuthenticationHeader;
import com.sap.core.connectivity.api.configuration.DestinationConfiguration;
import com.sap.pm.entity.MetricData15Min;
import com.sap.pm.entity.MetricData1Min;
import com.sap.pm.entity.MetricData60Min;
import com.sap.pm.entity.MetricInfo;
import com.sap.pm.model.ForecastBody;
import com.sap.pm.pojo.ForecastResponse;
import com.sap.pm.repository.MetricData15MinRepository;
import com.sap.pm.repository.MetricData1MinRepository;
import com.sap.pm.repository.MetricData60MinRepository;
import com.sap.pm.repository.MetricInfoRepository;
import com.sap.pm.util.CommonUtils;
import com.sap.pm.util.DestinationUtil;

@Service
public class ForecastService {
	
	
	@Autowired 
	MetricInfoRepository metricInfoRepo;
	
	@Autowired 
	MetricData1MinRepository metricData1MinRepository;
	
	@Autowired 
	MetricData15MinRepository metricData15MinRepository;
	
	@Autowired 
	MetricData60MinRepository metricData60MinRepository;
	
	private static final Logger log = LoggerFactory.getLogger(ForecastService.class);
	
	private static final String FORECAST_URL = "https://aac4paservicesp1942956795trial.hanatrial.ondemand.com/com.sap.aa.c4pa.services/api/analytics/forecast/sync";
	
	void insertInfluencerData(){
		
		List<MetricInfo> list = metricInfoRepo.findAll();
		
		
		for(MetricInfo metricInfo : list){
			Date date = metricInfo.getDate();
			
			
		}
		
	}
	
	public ForecastResponse forecastMetric1Min(String metricName, String granularity){
		log.error("forecastmetric ---- ");
		
		ResponseEntity<ForecastResponse> response = null;
		ForecastResponse responseBody = null;
		
		int datasetId = 0;
		String targetColumn = "";
		
		if("cpu".equals(metricName)){
			targetColumn = "CPU_USAGE";
		}else if("ram".equals(metricName)){
			targetColumn = "RAM_USAGE";
		}else if("disk".equals(metricName)){
			targetColumn = "DISK_USAGE";
		}	
		
		if("1min".equals(granularity)){
			datasetId = 1;
		}else if("15min".equals(granularity)){
			datasetId = 24;
		}else if("60min".equals(granularity)){
			datasetId = 25;
		}

		ForecastBody body = new ForecastBody();
		body.setDatasetID(datasetId);
		body.setTargetColumn(targetColumn);
		body.setDateColumn("DATE");
		body.setNumberOfForecasts(10);
		body.setReferenceDate(CommonUtils.convertToString(new Date()));
		
		Gson gson = new Gson();
		String jsonObject = gson.toJson(body);
		log.info("Request body - " + jsonObject);
		
		try {
			DestinationConfiguration destConfig = DestinationUtil.getDestConfig("ps");			
			String url = destConfig.getProperty("URL")+"/forecast/sync";
			
			AuthenticationHeader appToAppSSOHeader = DestinationUtil.getAuthenticationHeader(url);
			if (null == appToAppSSOHeader) {
				log.info("appToAppSSOHeader : NULL");
			}
			
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add(appToAppSSOHeader.getName(), appToAppSSOHeader.getValue());
			HttpEntity<String> entity = new HttpEntity<String>(jsonObject, headers);

			response = restTemplate.exchange(url, HttpMethod.POST, entity, ForecastResponse.class);

			if (response != null) {
				responseBody = response.getBody();
				persistData(responseBody, metricName, granularity);
			}			
			log.debug("response payload : " + response);

		} catch (HttpClientErrorException | HttpServerErrorException e) {
			log.error("Exception ");
			//responseBody = e.getResponseBodyAsString();
			log.debug("response payload " + responseBody);
		}
		
		return responseBody;
	}
	
	private void persistData(ForecastResponse response, String metricName, String granularity){
		if(null != response){
			for(int i=0; i<response.getForecasts().size(); i++){
				if("1min".equals(granularity)){
					MetricData1Min metricData1Min = metricData1MinRepository.findOne(response.getForecasts().get(i).getDate());
					
					if("cpu".equals(metricName)){
						metricData1Min.setCpuUsageForecast(response.getForecasts().get(i).getForecastValue());
						metricData1Min.setCpuUsageMape(response.getModelPerformance().getMape());
					}else if("ram".equals(metricName)){
						metricData1Min.setRamUsageForecast(response.getForecasts().get(i).getForecastValue());
						metricData1Min.setRamUsageMape(response.getModelPerformance().getMape());
					}else if("disk".equals(metricName)){
						metricData1Min.setDiskUsageForecast(response.getForecasts().get(i).getForecastValue());
						metricData1Min.setDiskUsageMape(response.getModelPerformance().getMape());
					}
					
					
					metricData1MinRepository.save(metricData1Min);
				}else if("15min".equals(granularity)){
					MetricData15Min metricData15Min = metricData15MinRepository.findOne(response.getForecasts().get(i).getDate());
					
					if("cpu".equals(metricName)){
						metricData15Min.setCpuUsageForecast(response.getForecasts().get(i).getForecastValue());
						metricData15Min.setCpuUsageMape(response.getModelPerformance().getMape());
					}else if("ram".equals(metricName)){
						metricData15Min.setRamUsageForecast(response.getForecasts().get(i).getForecastValue());
						metricData15Min.setRamUsageMape(response.getModelPerformance().getMape());
					}else if("disk".equals(metricName)){
						metricData15Min.setDiskUsageForecast(response.getForecasts().get(i).getForecastValue());
						metricData15Min.setDiskUsageMape(response.getModelPerformance().getMape());
					}
					
					
					metricData15MinRepository.save(metricData15Min);
				}else if("60min".equals(granularity)){
					MetricData60Min metricData60Min = metricData60MinRepository.findOne(response.getForecasts().get(i).getDate());
					
					if("cpu".equals(metricName)){
						metricData60Min.setCpuUsageForecast(response.getForecasts().get(i).getForecastValue());
						metricData60Min.setCpuUsageMape(response.getModelPerformance().getMape());
					}else if("ram".equals(metricName)){
						metricData60Min.setRamUsageForecast(response.getForecasts().get(i).getForecastValue());
						metricData60Min.setRamUsageMape(response.getModelPerformance().getMape());
					}else if("disk".equals(metricName)){
						metricData60Min.setDiskUsageForecast(response.getForecasts().get(i).getForecastValue());
						metricData60Min.setDiskUsageMape(response.getModelPerformance().getMape());
					}
					
					
					metricData60MinRepository.save(metricData60Min);
				}
			}
		}
	}

}
