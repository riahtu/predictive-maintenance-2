package com.sap.pm.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sap.pm.entity.MetricData15Min2;
import com.sap.pm.entity.MetricData1Min;

@Repository
public interface MetricData15Min2Repository  extends JpaRepository<MetricData15Min2, Date>  {

}
