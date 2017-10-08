package com.sap.pm.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sap.pm.entity.ActionTemplate;
@Repository
public interface ActionTemplateRepository extends JpaRepository<ActionTemplate, Serializable>{

}
