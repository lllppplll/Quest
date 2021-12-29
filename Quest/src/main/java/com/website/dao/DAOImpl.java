package com.website.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.website.dto.CreateAccountDTO;

@Repository
@Transactional
public class DAOImpl implements DAOI {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void SaveCreateAccount(CreateAccountDTO userData) {
		
//      Connecting and saving data in database
		Object[] sqlParameters1 = {userData.getEmail(), userData.getPassword(), "ROLE_USER", 0};
//		Prepared SQL statement
		String sql1 = "INSERT INTO Users(email, password, role, enabled) VALUES ( ?,?,?,? )";
	    jdbcTemplate.update(sql1, sqlParameters1);

	}
}
