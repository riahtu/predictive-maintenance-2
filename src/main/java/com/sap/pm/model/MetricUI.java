package com.sap.pm.model;

import java.util.Date;

public class MetricUI {

	private Date date;
	
	private Double actual;
	
	private Double predicted;
	
	private Double capacity;
	
	private Double threshold;

	public Double getCapacity() {
		return capacity;
	}

	public void setCapacity(Double capacity) {
		this.capacity = capacity;
	}

	public Double getThreshold() {
		return threshold;
	}

	public void setThreshold(Double threshold) {
		this.threshold = threshold;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getActual() {
		return actual;
	}

	public void setActual(Double actual) {
		this.actual = actual;
	}

	public Double getPredicted() {
		return predicted;
	}

	public void setPredicted(Double predicted) {
		this.predicted = predicted;
	}
	
	
	
}
