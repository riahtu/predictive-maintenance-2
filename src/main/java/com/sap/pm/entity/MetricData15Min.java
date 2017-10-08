package com.sap.pm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "METRIC_DATA_15MIN", schema = "PM_USER")
public class MetricData15Min {
	
	@Id
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE")
	private Date date;
	
	@Column(name = "IS_WEEKDAY", columnDefinition = "INT")
	private int isWeekDay;
	
	@Column(name = "CPU_USAGE", columnDefinition = "decimal(16,6)")
	private double cpuUsage;
	
	@Column(name = "CPU_USAGE_FORECAST", columnDefinition = "decimal(16,6)")
	private double cpuUsageForecast;
	
	@Column(name = "CPU_USAGE_ENSEMBLE_FORECAST", columnDefinition = "decimal(16,6)")
	private double cpuUsageEnsembleForecast;
	
	@Column(name = "CPU_USAGE_MODEL_MAPE", columnDefinition = "decimal(16,6)")
	private double cpuUsageMape;
	
	@Column(name = "RAM_USAGE", columnDefinition = "decimal(16,6)")
	private double ramUsage;
	
	@Column(name = "RAM_USAGE_FORECAST", columnDefinition = "decimal(16,6)")
	private double ramUsageForecast;
	
	@Column(name = "RAM_USAGE_ENSEMBLE_FORECAST", columnDefinition = "decimal(16,6)")
	private double ramUsageEnsembleForecast;
	
	@Column(name = "RAM_USAGE_MODEL_MAPE", columnDefinition = "decimal(16,6)")
	private double ramUsageMape;
	
	@Column(name = "DISK_USAGE", columnDefinition = "decimal(16,6)")
	private double diskUsage;
	
	@Column(name = "DISK_USAGE_FORECAST", columnDefinition = "decimal(16,6)")
	private double diskUsageForecast;
	
	@Column(name = "DISK_USAGE_ENSEMBLE_FORECAST", columnDefinition = "decimal(16,6)")
	private double diskUsageEnsembleForecast;
	
	@Column(name = "DISK_USAGE_MODEL_MAPE", columnDefinition = "decimal(16,6)")
	private double diskUsageMape;

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

	public double getRamUsageForecast() {
		return ramUsageForecast;
	}

	public void setRamUsageForecast(double ramUsageForecast) {
		this.ramUsageForecast = ramUsageForecast;
	}

	public double getRamUsageEnsembleForecast() {
		return ramUsageEnsembleForecast;
	}

	public void setRamUsageEnsembleForecast(double ramUsageEnsembleForecast) {
		this.ramUsageEnsembleForecast = ramUsageEnsembleForecast;
	}

	public double getRamUsageMape() {
		return ramUsageMape;
	}

	public void setRamUsageMape(double ramUsageMape) {
		this.ramUsageMape = ramUsageMape;
	}

	public double getDiskUsage() {
		return diskUsage;
	}

	public void setDiskUsage(double diskUsage) {
		this.diskUsage = diskUsage;
	}

	public double getDiskUsageForecast() {
		return diskUsageForecast;
	}

	public void setDiskUsageForecast(double diskUsageForecast) {
		this.diskUsageForecast = diskUsageForecast;
	}

	public double getDiskUsageEnsembleForecast() {
		return diskUsageEnsembleForecast;
	}

	public void setDiskUsageEnsembleForecast(double diskUsageEnsembleForecast) {
		this.diskUsageEnsembleForecast = diskUsageEnsembleForecast;
	}

	public double getDiskUsageMape() {
		return diskUsageMape;
	}

	public void setDiskUsageMape(double diskUsageMape) {
		this.diskUsageMape = diskUsageMape;
	}
	
}
