package com.website.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.website.dto.CreateAccountDTO;

@Repository
public class CreateAccountDAOImpl implements CreateAccountDAOI {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//Constructor Empty
	public CreateAccountDAOImpl() {
	}

	//Constructor
	public CreateAccountDAOImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void SaveCreateAccount(CreateAccountDTO userData) {
		
        //Connecting and saving data in database
		Object[] sqlParameters1 = {userData.getEmail(), userData.getPassword(), userData.getRoles(), userData.getEnabled()};
		//Prepared SQL statement
		String sql1 = "INSERT INTO Users(email, password, roles, enabled) VALUES ( ?,?,?,? )";
	    jdbcTemplate.update(sql1, sqlParameters1);

	}	
}
