package com.sap.pm.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

public class OnboardingDataPK implements Serializable{

	private static final long serialVersionUID = 1L;

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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountName == null) ? 0 : accountName.hashCode());
		result = prime * result + ((applicationName == null) ? 0 : applicationName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OnboardingDataPK other = (OnboardingDataPK) obj;
		if (accountName == null) {
			if (other.accountName != null)
				return false;
		} else if (!accountName.equals(other.accountName))
			return false;
		if (applicationName == null) {
			if (other.applicationName != null)
				return false;
		} else if (!applicationName.equals(other.applicationName))
			return false;
		return true;
	}

	
}
