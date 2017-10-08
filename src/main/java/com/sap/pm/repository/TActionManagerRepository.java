package com.sap.pm.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sap.pm.entity.TActionManager;

@Repository
public interface TActionManagerRepository extends JpaRepository<TActionManager, Serializable>{

	TActionManager findByMetrictype(String name);

}
