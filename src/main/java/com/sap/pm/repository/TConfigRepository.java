package com.sap.pm.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sap.pm.entity.TConfig;

@Repository
public interface TConfigRepository extends JpaRepository<TConfig, Serializable>{

	TConfig findByMetrictype(String name);

}
