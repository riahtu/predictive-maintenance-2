package com.sap.pm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "METRIC_DATA", schema = "PM_USER")
public class MetricData {
	
	@Id
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Date")
	private Date date;
	
	@Column(name = "CPU_USAGE", columnDefinition = "decimal(16,6)")
	private double cpuUsage;
	
	@Column(name = "CPU_USAGE_FORECAST", columnDefinition = "decimal(16,6)")
	private double cpuUsageForecast;
	
	@Column(name = "IS_WEEKDAY", columnDefinition = "INT")
	private int isWeekDay;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getCpuUsage() {
		return cpuUsage;
	}

	public void setCpuUsage(double cpuUsage) {
		this.cpuUsage = cpuUsage;
	}

	public double getCpuUsageForecast() {
		return cpuUsageForecast;
	}

	public void setCpuUsageForecast(double cpuUsageForecast) {
		this.cpuUsageForecast = cpuUsageForecast;
	}

	public int getIsWeekDay() {
		return isWeekDay;
	}

	public void setIsWeekDay(int isWeekDay) {
		this.isWeekDay = isWeekDay;
	}
	
}
