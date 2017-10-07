package com.sap.pm.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sap.pm.entity.MetricData1Min2;

@Repository
public interface MetricData1Min2Repository  extends JpaRepository<MetricData1Min2, Date>  {

}
