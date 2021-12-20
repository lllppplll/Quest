package com.website.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DAOImpl implements DAOI {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

}
