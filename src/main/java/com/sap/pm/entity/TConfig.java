package com.sap.pm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "T_CONFIG", schema = "PM_USER")
@SequenceGenerator(name="seq", initialValue=1, allocationSize=100000)
public class TConfig {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
	private long id;
	
	@Column(name = "METRIC_TYPE", columnDefinition = "varchar")
	private String metrictype;
	
	@Column(name = "METRIC_THRESHOLD", columnDefinition = "decimal(16,6)")
	private double metric_threshold;
	
	@Column(name = "CAPACITY", columnDefinition = "decimal(16,6)")
	private double capacity;
	
	@Column(name = "UNIT", columnDefinition = "varchar")
	private String unit;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMetrictype() {
		return metrictype;
	}

	public void setMetrictype(String metrictype) {
		this.metrictype = metrictype;
	}

	public double getMetric_threshold() {
		return metric_threshold;
	}

	public void setMetric_threshold(double metric_threshold) {
		this.metric_threshold = metric_threshold;
	}

	public double getCapacity() {
		return capacity;
	}

	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	
}
