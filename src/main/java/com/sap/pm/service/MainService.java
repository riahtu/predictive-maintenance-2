package com.sap.pm.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.sap.pm.model.BodySave;
import com.sap.pm.util.DBUtils;
import com.sap.pm.util.DestinationUtil;

@Service
public class MainService {

	private static final Logger log = LoggerFactory.getLogger(MainService.class);
	
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
		
		BodySave body = new BodySave();
		body.setHanaURL("PS_DATA/SALES");
		
		Gson gson = new Gson();
		String jsonObject = gson.toJson(body);
		log.info("Request body - " + jsonObject);
		
		try {
			String url = "https://aac4paservicesi322364trial.hanatrial.ondemand.com/com.sap.aa.c4pa.services/api/analytics/dataset/sync";
			
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
}
