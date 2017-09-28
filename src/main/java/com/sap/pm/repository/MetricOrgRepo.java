package com.sap.pm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sap.pm.entity.MetricOriginal;


public interface MetricOrgRepo  extends JpaRepository<MetricOriginal, Long>  {

}
