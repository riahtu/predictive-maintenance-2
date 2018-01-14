package com.sap.pm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "ONBOARDING_DATA", schema = "PM_USER")
@IdClass(OnboardingDataPK.class)
public class OnboardingData {

	@Id
	@Column(name = "APPLICATION_NAME", columnDefinition = "varchar")
	private String applicationName;
	
	@Id
	@Column(name = "ACCOUNT_NAME", columnDefinition = "varchar")
	private String accountName;
	public String getApplicationName() {
		return applicationName;
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	
}
