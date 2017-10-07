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
import com.sap.pm.entity.MetricData1Min;
import com.sap.pm.model.ForecastBody;
import com.sap.pm.model.Location;
import com.sap.pm.model.MetricUI;
import com.sap.pm.model.Metrics;
import com.sap.pm.model.RegDatasourceBody;
import com.sap.pm.repository.MetricDataRepository;
import com.sap.pm.util.DBUtils;
import com.sap.pm.util.DestinationUtil;

@Service
public class MainService {

	private static final Logger log = LoggerFactory.getLogger(MainService.class);
	
	private static final String REG_URL = "https://aac4paservicesp1942956795trial.hanatrial.ondemand.com/com.sap.aa.c4pa.services/api/analytics/dataset/sync";
	
	private static final String FORECAST_URL = "https://aac4paservicesp1942956795trial.hanatrial.ondemand.com/com.sap.aa.c4pa.services/api/analytics/forecast/sync";
	
	@Autowired 
	MetricDataRepository metricDataRepository;
	
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
			
			AuthenticationHeader appToAppSSOHeader = DestinationUtil.getAuthenticationHeader(REG_URL);
			if (null == appToAppSSOHeader) {
				log.info("appToAppSSOHeader : NULL");
			}
			
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add(appToAppSSOHeader.getName(), appToAppSSOHeader.getValue());
			HttpEntity<String> entity = new HttpEntity<String>(jsonObject, headers);

			response = restTemplate.exchange(REG_URL, HttpMethod.POST, entity, String.class);

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
	
	public String forecastMetric(){
		log.info("forecastmetric ---- ");
		
		ResponseEntity<String> response = null;
		String responseBody = null;

		ForecastBody body = new ForecastBody();
		body.setDatasetID(6);
		body.setTargetColumn("CPU_USAGE");
		body.setDateColumn("DATE");
		body.setNumberOfForecasts(1);
		body.setReferenceDate("2017-10-06 19:40:28");
		
		Gson gson = new Gson();
		String jsonObject = gson.toJson(body);
		log.info("Request body - " + jsonObject);
		
		try {
			
			AuthenticationHeader appToAppSSOHeader = DestinationUtil.getAuthenticationHeader(FORECAST_URL);
			if (null == appToAppSSOHeader) {
				log.info("appToAppSSOHeader : NULL");
			}
			
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add(appToAppSSOHeader.getName(), appToAppSSOHeader.getValue());
			HttpEntity<String> entity = new HttpEntity<String>(jsonObject, headers);

			response = restTemplate.exchange(FORECAST_URL, HttpMethod.POST, entity, String.class);

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
			}			log.debug("response payload : " + response);

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
	public List<MetricData1Min> getData(String metricName) {
		List<MetricUI> metricUIs = new ArrayList<MetricUI>();
		MetricUI metricUI = new MetricUI();
		
		List<MetricData1Min> metricData = metricDataRepository.findAll();
//		metricUI.setDate(metricData.getDate());
//		metricUI.setActual(metricData.getCpuUsage());
//		metricUI.setPredicted(metricData.getCpuUsageForecast());
//		metricUIs.add(metricUI);
		
		return metricData;
	}
}
