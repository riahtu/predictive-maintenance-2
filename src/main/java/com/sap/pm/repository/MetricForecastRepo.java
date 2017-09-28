package com.sap.pm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sap.pm.entity.MetricForecast;


public interface MetricForecastRepo  extends JpaRepository<MetricForecast, Long>  {

}
