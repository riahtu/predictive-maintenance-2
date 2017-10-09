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
	private Long id;
	
	@Column(name = "METRIC_TYPE", columnDefinition = "varchar(4)")
	private String metrictype;
	
	@Column(name = "METRIC_THRESHOLD", columnDefinition = "decimal(16,6)")
	private double metric_threshold;
	
	@Column(name = "CAPACITY", columnDefinition = "decimal(16,6)")
	private double capacity;
	
	@Column(name = "UNIT", columnDefinition = "varchar(6)")
	private String unit;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
