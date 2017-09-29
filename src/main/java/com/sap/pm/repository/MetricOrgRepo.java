package com.sap.pm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sap.pm.entity.MetricOriginal;

@Repository
public interface MetricOrgRepo  extends JpaRepository<MetricOriginal, Long>  {

}
