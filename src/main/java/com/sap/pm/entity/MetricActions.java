package com.sap.pm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "METRIC_ACTIONS", schema = "PM_USER")
public class MetricActions {
	
	@Id
	@Column(name = "ACTION_ID")
	private int actionId;
	
	@Column(name = "METRIC_ID", columnDefinition = "INT")
	private double metricId;
	
	@Column(name = "MAPE_LOWER", columnDefinition = "decimal(16,6)")
	private double mapeLower;
	
	@Column(name = "MAPE_UPPER", columnDefinition = "decimal(16,6)")
	private double mapeUpper;
	
	@Column(name = "ACTION_NAME", columnDefinition = "varchar")
	private String actionName;

	public int getActionId() {
		return actionId;
	}

	public void setActionId(int actionId) {
		this.actionId = actionId;
	}

	public double getMetricId() {
		return metricId;
	}

	public void setMetricId(double metricId) {
		this.metricId = metricId;
	}

	public double getMapeLower() {
		return mapeLower;
	}

	public void setMapeLower(double mapeLower) {
		this.mapeLower = mapeLower;
	}

	public double getMapeUpper() {
		return mapeUpper;
	}

	public void setMapeUpper(double mapeUpper) {
		this.mapeUpper = mapeUpper;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	
}
