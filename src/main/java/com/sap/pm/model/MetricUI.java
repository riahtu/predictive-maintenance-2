package com.sap.pm.model;

import java.util.Date;

public class MetricUI {

	private Date date;
	
	private Double actual;
	
	private Double predicted;

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
