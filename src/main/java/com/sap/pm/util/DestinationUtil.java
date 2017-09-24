//package com.sap.pm.util;
//
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.util.ArrayList;
//import java.util.Base64;
//
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpHeaders;
//import org.apache.http.HttpResponse;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.HttpClientBuilder;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.util.EntityUtils;
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.sap.core.connectivity.api.authentication.AuthenticationHeader;
//import com.sap.core.connectivity.api.authentication.AuthenticationHeaderProvider;
//import com.sap.core.connectivity.api.configuration.ConnectivityConfiguration;
//import com.sap.core.connectivity.api.configuration.DestinationConfiguration;
//
//public class DestinationUtil {
//
//	private static final Logger LOGGER = LoggerFactory.getLogger(DestinationUtil.class);
//
//	private static HttpClient httpClient = null;
//
//	private static ConnectivityConfiguration configuration;
//
//	private static AuthenticationHeaderProvider authHeaderProvider;
//
//	private DestinationUtil() {
//		throw new IllegalAccessError("Utility class DestinationAccessUtil should not be instantiated");
//	}
//
//	static {
//		try {
//			Context ctx = new InitialContext();
//			configuration = (ConnectivityConfiguration) ctx.lookup("java:comp/env/connectivityConfiguration");
//
//		} catch (NamingException e) {
//			LOGGER.debug(" NamingException Could not connect to DestinationConfiguration sevice : JNDI Lookup error");
//		}
//	}
//
//	static {
//		try {
//			Context ctx = new InitialContext();
//			authHeaderProvider = (AuthenticationHeaderProvider) ctx.lookup("java:comp/env/myAuthHeaderProvider");
//
//		} catch (NamingException e) {
//			LOGGER.error(" NamingException Could not connect to AuthenticationHeaderProvider for AppToAppSSO");
//		}
//	}
//
//	public static HttpClient getHttpClient() {
//		if (httpClient == null)
//			httpClient = HttpClientBuilder.create().build();
//
//		return httpClient;
//	}
//
//	/**
//	 * Generates access token for OAuth client
//	 * 
//	 * @param tokenUrl
//	 * @param userName
//	 * @param pwd
//	 * @param scope
//	 * @return
//	 * @throws BaseException
//	 */
//	public static String getAccessToken(String tokenUrl, String userName, String pwd, String scope){
//		
//		LOGGER.info("Entry : getAccessToken");
//		
//		String result = null;
//		String accessToken = null;
//		HttpResponse httpResponse;
//
//		String userPass = userName + ":" + pwd;
//		String encodedUserPass = null;
//		try {
//			encodedUserPass = Base64.getEncoder().encodeToString(userPass.getBytes("utf-8"));
//		} catch (UnsupportedEncodingException e1) {
//			LOGGER.error("UnsupportedEncodingException occurred ");
//		}
//
//		String basicAuth = "Basic " + encodedUserPass;
//		LOGGER.info("basicAuth : " + basicAuth);
//
//		HttpClient httpclient = getHttpClient();
//		HttpPost httpPost = new HttpPost(tokenUrl);
//		httpPost.setHeader(HttpHeaders.AUTHORIZATION, basicAuth);
//		httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");
//
//		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
//		params.add(new BasicNameValuePair("grant_type", "client_credentials"));
//
//		if (null != scope) {
//			params.add(new BasicNameValuePair("scope", scope));
//		}
//
//		LOGGER.info("params : " + params.get(0) + "\t" + scope);
//
//		try {
//			httpPost.setEntity(new UrlEncodedFormEntity(params));
//			httpResponse = httpclient.execute(httpPost);
//
//			if (httpResponse == null) {
//				LOGGER.info("HttpResponse : NULL");
//			}
//
//			LOGGER.info("HttpResponse : " + httpResponse.getEntity().getContent());
//
//			HttpEntity entity = httpResponse.getEntity();
//			if (entity == null) {
//				LOGGER.info("HttpEntity : NULL");
//			}
//
//			result = EntityUtils.toString(entity);
//
//		} catch (IOException e) {
//			LOGGER.error("Exception : " + e);
//		}
//
//		LOGGER.info("Result : " + result);
//		if (result == null || result.isEmpty()) {
//		}
//
//		JSONObject obj = null;
//		try {
//			obj = new JSONObject(result);
//		} catch (JSONException e) {
//			LOGGER.error("JSONException : " + e);
//		}
//
//		if (null != obj && obj.has("access_token"))
//			accessToken = obj.getString("access_token");
//
//		accessToken = "Bearer "+accessToken;
//		LOGGER.info("Access token for URL - " + tokenUrl + " and User - " + userName + " : " + accessToken);
//		
//		LOGGER.info("Exit : getAccessToken");
//		return accessToken;
//	}
//
//	/**
//	 * Generates Access token for OAuth client
//	 * 
//	 * @param destination
//	 * @return
//	 * @throws BaseException
//	 */
//	public static String getAccessToken(String destination){
//		String accessToken = null;
//		LOGGER.info("Entry : getAccessToken");
//		
//		DestinationConfiguration destConfiguration = getDestConfig(destination);
//		if (destConfiguration == null) {
//			LOGGER.error("DestinationConfiguration : NULL");
//		}
//
//		String url = destConfiguration.getProperty("URL");
//		if(url == null || url.isEmpty()){
//
//		}
//		
//		String userName = destConfiguration.getProperty("User");
//		String pwd = destConfiguration.getProperty("Password");
//		if(userName == null || userName.isEmpty() || pwd == null || pwd.isEmpty()){
//
//		}
//		
//		accessToken = getAccessToken(url, userName, pwd, null);
//		LOGGER.info("Access token for destination "+destination+" : " + accessToken);
//		
//		LOGGER.info("Exit : getAccessToken");
//		return accessToken;
//	}
//
//	/**
//	 * Returns the configuration for given destination
//	 * 
//	 * @param destination
//	 * @return
//	 * @throws BaseException
//	 */
//	public static DestinationConfiguration getDestConfig(String destination){
//
//		LOGGER.info("Entry : getDestConfig");
//		
//		DestinationConfiguration destConfiguration = configuration.getConfiguration(destination);
//		
//		LOGGER.info("Exit : getDestConfig");
//		return destConfiguration;
//	}
//
//	/**
//	 * Returns AuthenticationHeader for AppToAppSSO
//	 * 
//	 * @param url
//	 * @return
//	 * @throws BaseException
//	 */
//	public static AuthenticationHeader getAuthenticationHeader(String url) {
//		AuthenticationHeader appToAppSSOHeader = null;
//		
//		LOGGER.info("Entry : getAuthenticationHeader");
//		
//		try {
//			appToAppSSOHeader = authHeaderProvider.getAppToAppSSOHeader(url);
//		} catch (IllegalStateException e) {
//			LOGGER.error("IllegalStateException in getAuthenticationHeader() - " + e);
//		}
//		
//		/*if(null == appToAppSSOHeader){
//			LOGGER.info("appToAppSSOHeader : NULL");
//			throw new BaseException("destination.appToappsso.error", null, new String[] { url });
//		}*/
//		
//		LOGGER.info("AppToAppSSOHeader for URL - "+url+" : "+appToAppSSOHeader);
//		
//		LOGGER.info("Exit : getAuthenticationHeader");
//		return appToAppSSOHeader;
//	}
//
//}
