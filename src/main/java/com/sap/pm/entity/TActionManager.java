package com.sap.pm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "T_ACTION_MANAGER", schema = "PM_USER")
@SequenceGenerator(name="seq", initialValue=1, allocationSize=100000)
public class TActionManager {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
	private Long id;
	
	@Column(name = "METRIC_TYPE", columnDefinition = "varchar(8)")
	private String metrictype;
	
	@Column(name = "ACTION_TYPE",columnDefinition = "varchar(4)")
	private String actionType;
	
	@Column(name = "CRITICALITY", columnDefinition = "INT")
	private int criticality;
	
		
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

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public int getCriticality() {
		return criticality;
	}

	public void setCriticality(int criticality) {
		this.criticality = criticality;
	}

	
	
	
}
