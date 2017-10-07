package com.sap.pm.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sap.pm.entity.MetricData1Min2;

@Repository
public interface MetricData1Min2Repository  extends JpaRepository<MetricData1Min2, Date>  {
	@Query("select metricDdate from MetricData1Min2 metricDdate where metricDdate.date > :date1 and metricDdate.date < :date2")
	public List<MetricData1Min2> retrieveData(@Param("date1")Date date1,@Param("date2")Date date2);
}
