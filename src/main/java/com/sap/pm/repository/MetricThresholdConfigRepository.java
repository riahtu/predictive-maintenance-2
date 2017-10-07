package com.sap.pm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sap.pm.entity.MetricThresholdConfig;
import com.sap.pm.entity.MetricThresholdConfigID;

@Repository
public interface MetricThresholdConfigRepository  extends JpaRepository<MetricThresholdConfig, MetricThresholdConfigID> {

}
