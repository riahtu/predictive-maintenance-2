package com.sap.pm.service;

import java.io.IOException;
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

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.pm.entity.MetricData1Min;
import com.sap.pm.pojo.AccountMetric;
import com.sap.pm.pojo.Metric;
import com.sap.pm.repository.MetricDataRepository;

@Service
public class MetricsDataService {

	private static final Logger log = LoggerFactory.getLogger(MainService.class);

	private static final String METRICS_DATA_URL = "https://api.int.sap.hana.ondemand.com/monitoring/v1/accounts/wae679e83/apps/acrscore1/metrics";

	@Autowired
	MetricDataRepository metricDataRepository;
	
	public String getMetricDataFromApi() {
		log.info("getMetrics2 ---- ");
		
		ResponseEntity<String> response = null;
		String responseBody = null;
		
		try {
			String url = "https://api.hana.ondemand.com/monitoring/v1/accounts/a9ef421ca/apps/gstrapp/metrics";
			
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("Authorization", "Basic cDE5NDI5NTY3OTU6V2VsY29tZUAxMjM=");
			HttpEntity<String> entity = new HttpEntity<String>(headers);
			response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

			if (response != null) {
				responseBody = response.getBody();
			}			log.debug("response payload : " + response);

		} catch (HttpClientErrorException | HttpServerErrorException e) {
			log.error("Exception ");
			responseBody = e.getResponseBodyAsString();
			log.debug("response payload " + responseBody);
		}
		
		return responseBody;
	}
	
	
	public void storeMetricsData() {

		String response = getMetricDataFromApi();

		ObjectMapper mapper = new ObjectMapper();
		List<AccountMetric> obj = null;
		try {
			obj = mapper.readValue(response, new TypeReference<List<AccountMetric>>(){});
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (null != obj ) {
			for(AccountMetric acMetr : obj){
			for (com.sap.pm.pojo.Process process :  acMetr.getProcesses()) {
				for (Metric metric : process.getMetrics()) {
					if (metric.getName().equals("CPU Load")) {
						MetricData1Min metricdata = new MetricData1Min();
						java.util.Date date = new java.util.Date();
						int day = date.getDay();
						metricdata.setDate(new java.util.Date());
						metricdata.setCpuUsage(metric.getValue());
						if (day == 0 || day == 1)
							metricdata.setIsWeekDay(0);
						else
							metricdata.setIsWeekDay(1);
						metricDataRepository.save(metricdata);
					}
				}
			}
		}
		}

	}

}
