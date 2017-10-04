package com.sap.pm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "CPU_METRIC_INFO", schema = "PS_USER")
public class MetricInfo {
	
	@Id
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Date")
	private Date date;
	
	@Column(name = "CPU_USAGE", columnDefinition = "decimal(16,6)")
	private double cpuUsage;
	
	@Column(name = "CPU_USAGE_FORECAST", columnDefinition = "decimal(16,6)")
	private double cpuUsageForecast;
	
	@Column(name = "RAM_USAGE", columnDefinition = "decimal(16,6)")
	private double ramUsage;
	
	@Column(name = "RAM_USAGE_FORECAST", columnDefinition = "INT(5)")
	private double ramUsageFC;
	
	@Column(name = "BUSY_THREADS", columnDefinition = "INT(5)")
	private Integer busyThreads;
	
	@Column(name = "BUSY_THREADS_FORECAST")
	private Integer busyThreadsFC;

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

	public double getRamUsage() {
		return ramUsage;
	}

	public void setRamUsage(double ramUsage) {
		this.ramUsage = ramUsage;
	}

	public double getRamUsageFC() {
		return ramUsageFC;
	}

	public void setRamUsageFC(double ramUsageFC) {
		this.ramUsageFC = ramUsageFC;
	}

	public Integer getBusyThreads() {
		return busyThreads;
	}

	public void setBusyThreads(Integer busyThreads) {
		this.busyThreads = busyThreads;
	}

	public Integer getBusyThreadsFC() {
		return busyThreadsFC;
	}

	public void setBusyThreadsFC(Integer busyThreadsFC) {
		this.busyThreadsFC = busyThreadsFC;
	}

	@Override
	public String toString() {
		return "MetricInfo [date=" + date + ", cpuUsage=" + cpuUsage + ", cpuUsageForecast=" + cpuUsageForecast
				+ ", ramUsage=" + ramUsage + ", ramUsageFC=" + ramUsageFC + ", busyThreads=" + busyThreads
				+ ", busyThreadsFC=" + busyThreadsFC + "]";
	}
	
}
