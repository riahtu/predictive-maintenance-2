package com.sap.pm.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sap.pm.entity.MetricInfo;

@Repository
public interface MetricInfoRepository  extends JpaRepository<MetricInfo, Date> {

}
