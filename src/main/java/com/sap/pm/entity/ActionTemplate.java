package com.sap.pm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ACTION_TEMPLATE", schema = "PM_USER")
@SequenceGenerator(name="seq", initialValue=1, allocationSize=100000)
public class ActionTemplate {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
	private Long id;
	
	@Column(name = "NAME", columnDefinition = "varchar")
	private String name;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TIME")
	private Date time;
	
	@Column(name = "TYPE", columnDefinition = "varchar")
	private String type;
	
	@Column(name = "CRITICALITY", columnDefinition = "INT")
	private int criticality;
	
	@Column(name = "DESCRIPTION", columnDefinition = "varchar")
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getCriticality() {
		return criticality;
	}

	public void setCriticality(int criticality) {
		this.criticality = criticality;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
