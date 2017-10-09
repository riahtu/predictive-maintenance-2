package com.sap.pm.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sap.pm.entity.MetricData15Min;
import com.sap.pm.entity.MetricData1Min;
import com.sap.pm.entity.MetricData60Min;

@Repository
public interface MetricData15MinRepository  extends JpaRepository<MetricData15Min, Date>  {
	
	@Query("select metricDdate from MetricData15Min metricDdate where metricDdate.date < :date1")
	public List<MetricData15Min> getDateGreaterthanCurrentDate(@Param("date1")Date date1);
	
	@Query("select metricDdate from MetricData15Min metricDdate where metricDdate.date > :date1 and metricDdate.date <= :date2")
	public List<MetricData15Min> retrieveData(@Param("date1")Date date1,@Param("date2")Date date2);
}
