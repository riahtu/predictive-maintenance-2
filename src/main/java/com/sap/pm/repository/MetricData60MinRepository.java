package com.sap.pm.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sap.pm.entity.MetricData60Min;

@Repository
public interface MetricData60MinRepository  extends JpaRepository<MetricData60Min, Date>  {

}
