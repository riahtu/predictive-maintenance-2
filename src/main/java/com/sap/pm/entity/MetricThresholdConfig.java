//package com.sap.pm.entity;
//
//import java.util.Date;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.IdClass;
//import javax.persistence.Table;
//
//
//@Entity
//@Table(name = "METRIC_THRESHOLD_CONFIG", schema = "PM_USER")
//@IdClass(MetricThresholdConfigID.class)
//public class MetricThresholdConfig {
//	
//	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY) 
//	@Column(name = "ID")
//	private int Id;
//	
//	@Id
//	@Column(name = "METRIC_NAME")
//	private String metricName;
//	
//	@Id
//	@Column(name = "APPLICATION_ID")
//	private String applicationId;
//
//
//	@Column(name = "DESCRIPTION")
//	private String description;
//	
//
//	@Column(name = "THRESHOLD")
//	private String threshold;
//	
//
//	@Column(name = "METRC_RUN_NEXT_TIME")
//	private Date metricNextRunTime;
//	
//	@Column(name = "RUN_INTERVAL")
//	private int runInterval;
//	
//	@Column(name = "IS_ALERT_SUPPRESED")
//	private boolean isAlertSuppresed;
//	
//	@Column(name = "IS_ACTIVE")
//	private boolean isActive;
//
//	public int getId() {
//		return Id;
//	}
//
//	public void setId(int id) {
//		Id = id;
//	}
//
//	public String getMetricName() {
//		return metricName;
//	}
//
//	public void setMetricName(String metricName) {
//		this.metricName = metricName;
//	}
//
//	public String getApplicationId() {
//		return applicationId;
//	}
//
//	public void setApplicationId(String applicationId) {
//		this.applicationId = applicationId;
//	}
//
//	public String getDescription() {
//		return description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}
//
//	public String getThreshold() {
//		return threshold;
//	}
//
//	public void setThreshold(String threshold) {
//		this.threshold = threshold;
//	}
//
//	public Date getMetricNextRunTime() {
//		return metricNextRunTime;
//	}
//
//	public void setMetricNextRunTime(Date metricNextRunTime) {
//		this.metricNextRunTime = metricNextRunTime;
//	}
//
//	public int getRunInterval() {
//		return runInterval;
//	}
//
//	public void setRunInterval(int runInterval) {
//		this.runInterval = runInterval;
//	}
//
//	public boolean isAlertSuppresed() {
//		return isAlertSuppresed;
//	}
//
//	public void setAlertSuppresed(boolean isAlertSuppresed) {
//		this.isAlertSuppresed = isAlertSuppresed;
//	}
//
//	public boolean isActive() {
//		return isActive;
//	}
//
//	public void setActive(boolean isActive) {
//		this.isActive = isActive;
//	}
//	
//
//}
