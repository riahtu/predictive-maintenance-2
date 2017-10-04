package com.sap.pm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sap.pm.entity.MetricInfo;

public interface MetricInfoRepository  extends JpaRepository<MetricInfo, Long> {

}
