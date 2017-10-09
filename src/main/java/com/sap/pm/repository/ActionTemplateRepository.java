package com.sap.pm.repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sap.pm.entity.ActionTemplate;
import com.sap.pm.entity.MetricData1Min;

@Repository
public interface ActionTemplateRepository extends JpaRepository<ActionTemplate, Serializable>{
	@Query("select actionTemplate from ActionTemplate actionTemplate where actionTemplate.id > :id")
	public List<ActionTemplate> retrieveNextNotificaations(@Param("id")Long id);
}
