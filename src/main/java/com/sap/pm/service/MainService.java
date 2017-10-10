package com.sap.pm.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
import com.sap.pm.entity.MetricData1Min2;
import com.sap.pm.entity.MetricData60Min;
import com.sap.pm.entity.TConfig;
import com.sap.pm.model.Location;
import com.sap.pm.model.MetricUI;
import com.sap.pm.model.Metrics;
import com.sap.pm.model.RegDatasourceBody;
import com.sap.pm.repository.MetricData15MinRepository;
import com.sap.pm.repository.MetricData1Min2Repository;
import com.sap.pm.repository.MetricData1MinRepository;
import com.sap.pm.repository.MetricData60MinRepository;
import com.sap.pm.repository.TConfigRepository;
import com.sap.pm.util.CommonUtils;
import com.sap.pm.util.DBUtils;
import com.sap.pm.util.DestinationUtil;

@Service
public class MainService {

	private static final Logger log = LoggerFactory.getLogger(MainService.class);
	
	private static final String REG_URL = "https://aac4paservicesi322364trial.hanatrial.ondemand.com/com.sap.aa.c4pa.services/api/analytics/dataset/sync";
	
	@Autowired 
	MetricData1MinRepository metricData1MinRepository;
	
	@Autowired 
	MetricData15MinRepository metricData15MinRepository;
	
	@Autowired 
	MetricData60MinRepository metricData60MinRepository;
	
	@Autowired 
	MetricData1Min2Repository metricData1Min2Repository;
	
	@Autowired
	TConfigRepository configRepository;
	
	public String readDB(){
		String result = "success";
		Connection connection = null;
		try{
		connection = DBUtils.getDefaultDBConnection();
		
		Statement stmt=connection.createStatement();
		
		ResultSet rs=stmt.executeQuery("select * from \"PS_DATA\".\"SALES\"");
		
		while(rs.next()){
			result += rs.getString(1)+"  "+rs.getString(2)+"\n";
		}
		
		connection.close();
		}catch(SQLException e){
			
		}finally{
			if(connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		}
		
		return result;
	}
	public String registerData(){
		ResponseEntity<String> response = null;
		String responseBody = null;
		
		RegDatasourceBody body = new RegDatasourceBody();
		Location location = new Location();
		location.setSchema("PS_DATA");
		location.setTable("SALES");
		body.setLocation(location);
		
		Gson gson = new Gson();
		String jsonObject = gson.toJson(body);
		log.info("Request body - " + jsonObject);
		
		try {
			DestinationConfiguration destConfig = DestinationUtil.getDestConfig("ps");			
			String url = destConfig.getProperty("URL")+"/dataset/sync";
			
			AuthenticationHeader appToAppSSOHeader = DestinationUtil.getAuthenticationHeader(url);
			if (null == appToAppSSOHeader) {
				log.info("appToAppSSOHeader : NULL");
			}
			
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add(appToAppSSOHeader.getName(), appToAppSSOHeader.getValue());
			HttpEntity<String> entity = new HttpEntity<String>(jsonObject, headers);

			response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

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
	
	public String registerDataSource(String schemaName, String tableName){
		ResponseEntity<String> response = null;
		String responseBody = null;
		
		RegDatasourceBody body = new RegDatasourceBody();
		Location location = new Location();
		location.setSchema(schemaName);
		location.setTable(tableName);
		body.setLocation(location);
		
		Gson gson = new Gson();
		String jsonObject = gson.toJson(body);
		log.info("Request body - " + jsonObject);
		
		try {
			DestinationConfiguration destConfig = DestinationUtil.getDestConfig("ps");			
			String url = destConfig.getProperty("URL")+"/dataset/sync";
			
			AuthenticationHeader appToAppSSOHeader = DestinationUtil.getAuthenticationHeader(url);
			if (null == appToAppSSOHeader) {
				log.info("appToAppSSOHeader : NULL");
			}
			
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add(appToAppSSOHeader.getName(), appToAppSSOHeader.getValue());
			HttpEntity<String> entity = new HttpEntity<String>(jsonObject, headers);

			response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

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
	
//	public String forecastMetric(){
//		log.info("forecastmetric ---- ");
//		
//		ResponseEntity<String> response = null;
//		String responseBody = null;
//
//		ForecastBody body = new ForecastBody();
//		body.setDatasetID(8);
//		body.setTargetColumn("CPU_USAGE");
//		body.setDateColumn("DATE");
//		body.setNumberOfForecasts(10);
//		body.setReferenceDate("2017-10-13 19:01:00");
//		
//		Gson gson = new Gson();
//		String jsonObject = gson.toJson(body);
//		log.info("Request body - " + jsonObject);
//		
//		try {
//			
//			AuthenticationHeader appToAppSSOHeader = DestinationUtil.getAuthenticationHeader(FORECAST_URL);
//			if (null == appToAppSSOHeader) {
//				log.info("appToAppSSOHeader : NULL");
//			}
//			
//			RestTemplate restTemplate = new RestTemplate();
//			HttpHeaders headers = new HttpHeaders();
//			headers.setContentType(MediaType.APPLICATION_JSON);
//			headers.add(appToAppSSOHeader.getName(), appToAppSSOHeader.getValue());
//			HttpEntity<String> entity = new HttpEntity<String>(jsonObject, headers);
//
//			response = restTemplate.exchange(FORECAST_URL, HttpMethod.POST, entity, String.class);
//
//			if (response != null) {
//				responseBody = response.getBody();
//			}			log.debug("response payload : " + response);
//
//		} catch (HttpClientErrorException | HttpServerErrorException e) {
//			log.error("Exception ");
//			responseBody = e.getResponseBodyAsString();
//			log.debug("response payload " + responseBody);
//		}
//		
//		return responseBody;
//	}
//	
	public String getMetrics2(){
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
				
			}			
			log.debug("response payload : " + response);

		} catch (HttpClientErrorException | HttpServerErrorException e) {
			log.error("Exception ");
			responseBody = e.getResponseBodyAsString();
			log.debug("response payload " + responseBody);
		}
		
		return responseBody;
	}
	
	public List<Metrics> getMetrics(){
		ResponseEntity<List> response;
		
		List<Metrics> metricList = new ArrayList<>();
		
		Metrics metrics1 = new Metrics();
		metrics1.setMax(1000);
		metricList.add(metrics1);
		
		Metrics metrics2 = new Metrics();
		metrics1.setMax(1800);
		metricList.add(metrics2);
		
		Metrics metrics3 = new Metrics();
		metrics1.setMax(1090);
		metricList.add(metrics3);
		return metricList;
		
	}
	public List<MetricUI> getMetricData(String metricName,String date,String granularity) {
		List<MetricUI> metricUIs = new ArrayList<MetricUI>();
		
		List<MetricData1Min2> metricData1Mins = metricData1Min2Repository.findAll();
		if(metricData1Mins != null){
			for(int i=0; i< metricData1Mins.size(); i++){
				MetricUI metricUI = new MetricUI();

				metricUI.setDate(metricData1Mins.get(i).getDate());
				metricUI.setActual(metricData1Mins.get(i).getCpuUsage());
				metricUI.setPredicted(metricData1Mins.get(i).getCpuUsageEnsembleForecast());
				metricUIs.add(metricUI);
			}
		}
		
		return metricUIs;
	}
	
	public List<MetricUI> getMetricData2(String metricName,String date,String granularity) {
		List<MetricUI> metricUIs = new ArrayList<MetricUI>();
		Date fromTime = null;
		Date toTime = null;
		
		if(null == date || date.isEmpty()){
			Date currentDate = new Date();
			if("1min".equals(granularity)){
				fromTime = CommonUtils.addMinutesToDate(currentDate, -60);
				toTime = CommonUtils.addMinutesToDate(currentDate, -50);
			}else if("15mins".equals(granularity)){
				fromTime = CommonUtils.addMinutesToDate(currentDate, -60*15);
				toTime = CommonUtils.addMinutesToDate(currentDate, -50*15);
			}else if("60mins".equals(granularity)){
				fromTime = CommonUtils.addMinutesToDate(currentDate, -60*60);
				toTime = CommonUtils.addMinutesToDate(currentDate, -50*60);
			}
			
		}else{
			//fromTime = CommonUtils.convertToDate(date);
			Date dateTemp = new Date();
			dateTemp.setTime(Long.parseLong(date));
			fromTime = dateTemp;
			
			if("1min".equals(granularity)){
				toTime = CommonUtils.addMinutesToDate(fromTime, 10);
			}else if("15mins".equals(granularity)){
				toTime = CommonUtils.addMinutesToDate(fromTime, 10*15);
			}else if("60mins".equals(granularity)){
				toTime = CommonUtils.addMinutesToDate(fromTime, 10*60);
			}
		}
		
		if("1min".equals(granularity)){
			List<MetricData1Min> metricData1Mins = metricData1MinRepository.retrieveData( fromTime, toTime);
			if(metricData1Mins != null){
				for(int i=0; i< metricData1Mins.size(); i++){
					MetricUI metricUI = new MetricUI();
					
					metricUI.setDate(metricData1Mins.get(i).getDate());
					
					if("cpu".equals(metricName)){
						if(fromTime.before(new Date()))
							metricUI.setActual(metricData1Mins.get(i).getCpuUsage());
						metricUI.setPredicted(metricData1Mins.get(i).getCpuUsageEnsembleForecast());
						TConfig config = configRepository.findByMetrictype("cpu");
						metricUI.setCapacity(config.getCapacity());
						metricUI.setThreshold(config.getMetric_threshold());
					}else if("ram".equals(metricName)){
						if(fromTime.before(new Date()))
							metricUI.setActual(metricData1Mins.get(i).getRamUsage());
						metricUI.setPredicted(metricData1Mins.get(i).getRamUsageEnsembleForecast());
						TConfig config = configRepository.findByMetrictype("ram");
						metricUI.setCapacity(config.getCapacity());
						metricUI.setThreshold(config.getMetric_threshold());
					}else if("disk".equals(metricName)){
						if(fromTime.before(new Date()))
							metricUI.setActual(metricData1Mins.get(i).getDiskUsage());
						metricUI.setPredicted(metricData1Mins.get(i).getDiskUsageEnsembleForecast());
						TConfig config = configRepository.findByMetrictype("disk");
						metricUI.setCapacity(config.getCapacity());
						metricUI.setThreshold(config.getMetric_threshold());
					}else{
						
					}
					metricUIs.add(metricUI);
				}
			}
		}else if("15mins".equals(granularity)){
			List<MetricData15Min> metricData15Mins = metricData15MinRepository.retrieveData( fromTime, toTime);
			if(metricData15Mins != null){
				for(int i=0; i< metricData15Mins.size(); i++){
					MetricUI metricUI = new MetricUI();
					
					metricUI.setDate(metricData15Mins.get(i).getDate());
					
					if("cpu".equals(metricName)){
						if(fromTime.before(new Date()))
							metricUI.setActual(metricData15Mins.get(i).getCpuUsage());
						metricUI.setPredicted(metricData15Mins.get(i).getCpuUsageEnsembleForecast());
						TConfig config = configRepository.findByMetrictype("cpu");
						metricUI.setCapacity(config.getCapacity());
						metricUI.setThreshold(config.getMetric_threshold());
					}else if("ram".equals(metricName)){
						if(fromTime.before(new Date()))
							metricUI.setActual(metricData15Mins.get(i).getRamUsage());
						metricUI.setPredicted(metricData15Mins.get(i).getRamUsageEnsembleForecast());
						TConfig config = configRepository.findByMetrictype("ram");
						metricUI.setCapacity(config.getCapacity());
						metricUI.setThreshold(config.getMetric_threshold());
					}else if("disk".equals(metricName)){
						if(fromTime.before(new Date()))
							metricUI.setActual(metricData15Mins.get(i).getDiskUsage());
						metricUI.setPredicted(metricData15Mins.get(i).getDiskUsageEnsembleForecast());
						TConfig config = configRepository.findByMetrictype("disk");
						metricUI.setCapacity(config.getCapacity());
						metricUI.setThreshold(config.getMetric_threshold());
					}else{
						
					}
					metricUIs.add(metricUI);
				}
			}
		}else if("60mins".equals(granularity)){
			List<MetricData60Min> metricData60Mins = metricData60MinRepository.retrieveData( fromTime, toTime);
			if(metricData60Mins != null){
				for(int i=0; i< metricData60Mins.size(); i++){
					MetricUI metricUI = new MetricUI();
					
					metricUI.setDate(metricData60Mins.get(i).getDate());
					
					if("cpu".equals(metricName)){
						if(fromTime.before(new Date()))
							metricUI.setActual(metricData60Mins.get(i).getCpuUsage());
						metricUI.setPredicted(metricData60Mins.get(i).getCpuUsageEnsembleForecast());
						TConfig config = configRepository.findByMetrictype("cpu");
						metricUI.setCapacity(config.getCapacity());
						metricUI.setThreshold(config.getMetric_threshold());
					}else if("ram".equals(metricName)){
						if(fromTime.before(new Date()))
							metricUI.setActual(metricData60Mins.get(i).getRamUsage());
						metricUI.setPredicted(metricData60Mins.get(i).getRamUsageEnsembleForecast());
						TConfig config = configRepository.findByMetrictype("ram");
						metricUI.setCapacity(config.getCapacity());
						metricUI.setThreshold(config.getMetric_threshold());
					}else if("disk".equals(metricName)){
						if(fromTime.before(new Date()))
							metricUI.setActual(metricData60Mins.get(i).getDiskUsage());
						metricUI.setPredicted(metricData60Mins.get(i).getDiskUsageEnsembleForecast());
						TConfig config = configRepository.findByMetrictype("disk");
						metricUI.setCapacity(config.getCapacity());
						metricUI.setThreshold(config.getMetric_threshold());
					}else{
						
					}
					metricUIs.add(metricUI);
				}
			}
		}
		
		return metricUIs;
	}
}
