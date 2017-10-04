package com.sap.pm.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sap.pm.entity.MetricForecast;

@Repository
public interface MetricForecastRepo  extends JpaRepository<MetricForecast, Date>  {

}
