package com.sap.pm.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sap.pm.entity.MetricData15Min;

@Repository
public interface MetricData15MinRepository  extends JpaRepository<MetricData15Min, Date>  {

}
