package com.sap.pm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sap.pm.entity.OnboardingData;
import com.sap.pm.entity.OnboardingDataPK;

@Repository
public interface OnboardingDataRepository  extends JpaRepository<OnboardingData, OnboardingDataPK>  {
	
}
