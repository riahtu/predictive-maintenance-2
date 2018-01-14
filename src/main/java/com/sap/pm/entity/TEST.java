package com.sap.pm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
//@Table(name = "\"tables_cds::TEST\"", schema = "PM_USER")
@Table(name = "TEST2")
public class TEST {
	
	@Id
	@Column(name = "ID", columnDefinition = "varchar(15)")
	private String id;
	
	@Column(name = "NAME", columnDefinition = "varchar(15)")
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
