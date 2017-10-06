package com.sap.pm.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sap.pm.entity.MetricData;

@Repository
public interface MetricDataRepository  extends JpaRepository<MetricData, Date>  {

}
