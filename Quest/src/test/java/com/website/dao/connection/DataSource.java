package com.website.dao.connection;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DataSource {

	public JdbcTemplate jdbcDatasource() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl("jdbc:hsqldb:mem:db");
		dataSource.setDriverClassName("org.hsqldb.jdbc.JDBCDriver");

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		return jdbcTemplate;

	}
}
