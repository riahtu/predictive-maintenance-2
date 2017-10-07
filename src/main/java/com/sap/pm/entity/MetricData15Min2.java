package com.sap.pm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "METRIC_DATA_15MIN2", schema = "PM_USER")
public class MetricData15Min2 {
	
	@Id
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE")
	private Date date;
	
	@Column(name = "IS_WEEKDAY", columnDefinition = "INT")
	private int isWeekDay;
	
	@Column(name = "CPU_USAGE", columnDefinition = "decimal(16,6)")
	private double cpuUsage;
	
	@Column(name = "CPU_USAGE_ENSEMBLE_FORECAST", columnDefinition = "decimal(16,6)")
	private double cpuUsageEnsembleForecast;
	
	@Column(name = "CPU_USAGE_MODEL_MAPE", columnDefinition = "decimal(16,6)")
	private double cpuUsageMape;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getIsWeekDay() {
		return isWeekDay;
	}

	public void setIsWeekDay(int isWeekDay) {
		this.isWeekDay = isWeekDay;
	}

	public double getCpuUsage() {
		return cpuUsage;
	}

	public void setCpuUsage(double cpuUsage) {
		this.cpuUsage = cpuUsage;
	}

	public double getCpuUsageEnsembleForecast() {
		return cpuUsageEnsembleForecast;
	}

	public void setCpuUsageEnsembleForecast(double cpuUsageEnsembleForecast) {
		this.cpuUsageEnsembleForecast = cpuUsageEnsembleForecast;
	}

	public double getCpuUsageMape() {
		return cpuUsageMape;
	}

	public void setCpuUsageMape(double cpuUsageMape) {
		this.cpuUsageMape = cpuUsageMape;
	}

	
}
