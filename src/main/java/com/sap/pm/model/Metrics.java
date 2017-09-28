package com.sap.pm.model;

public class Metrics {
	
	 private String name;
	 private String  state;
	 private String value;
	 private String unit;
	 private int warningThreshold;
	 private int errorThreshold;
     private String output;
	 private String metricType;
     private int min;
     private int max;
     
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public int getWarningThreshold() {
		return warningThreshold;
	}
	public void setWarningThreshold(int warningThreshold) {
		this.warningThreshold = warningThreshold;
	}
	public int getErrorThreshold() {
		return errorThreshold;
	}
	public void setErrorThreshold(int errorThreshold) {
		this.errorThreshold = errorThreshold;
	}
	public String getOutput() {
		return output;
	}
	public void setOutput(String output) {
		this.output = output;
	}
	public String getMetricType() {
		return metricType;
	}
	public void setMetricType(String metricType) {
		this.metricType = metricType;
	}
	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
     
     

}
