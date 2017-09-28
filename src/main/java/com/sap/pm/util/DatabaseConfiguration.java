package com.sap.pm.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.jta.JtaTransactionManager;


@Configuration
public class DatabaseConfiguration extends JpaBaseConfiguration {

	final static Logger LOGGER = LoggerFactory.getLogger(DatabaseConfiguration.class);

	protected DatabaseConfiguration(DataSource dataSource, JpaProperties properties,
			ObjectProvider<JtaTransactionManager> jtaTransactionManagerProvider) {
		super(dataSource, properties, jtaTransactionManagerProvider);
	}

	@Override
	protected AbstractJpaVendorAdapter createJpaVendorAdapter() {
		AbstractJpaVendorAdapter adapter = new EclipseLinkJpaVendorAdapter();
		adapter.setShowSql(true);
		return adapter;
	}

	@Override
	protected Map<String, Object> getVendorProperties() {
		HashMap<String, Object> hMap = new HashMap<String, Object>();
		//hMap.put("eclipselink.ddl-generation", "none");
		hMap.put("eclipselink.weaving", "false");
		hMap.put("eclipselink.logging.level", "FINE");
		hMap.put("eclipselink.logging.level.sql", "FINE");
		hMap.put("eclipselink.logging.parameters", "true");
		hMap.put("eclipselink.cache.shared.default", "false");
		//hMap.put("packagesToScan", "com.sap.srfaas.entity");
		hMap.put("persistence-unit", "persistence-with-jpa");
		return hMap;
	}
	

}
