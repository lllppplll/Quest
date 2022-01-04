package com.website.dao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.website.dto.CreateAccountDTO;
import com.website.dto.CreateAccountTokenDTO;
import com.website.rowmapper.EmailVerificationRowMapper;

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

	@Override
	public void saveToken(String email, String token, Date calculateExpiryToken) {
		
		Object[] sqlParameter = {email, token, calculateExpiryToken};		
		
		String sql = "INSERT INTO VerificationTokens(email, token, expiryDate) VALUES ( ?,?,? )";
		
	    jdbcTemplate.update(sql, sqlParameter);
		
	}
	
	@Override
	public CreateAccountTokenDTO getToken(String token) {
				
        String sql = "SELECT * FROM VerificationTokens WHERE token = ? ";	
		
        CreateAccountTokenDTO getToken = jdbcTemplate.queryForObject(sql, new EmailVerificationRowMapper(), token);

		return getToken;
	}

	@Override
	public void enableAccount(String email, boolean enable) {
		
			Object[] sqlParameter = { enable, email};
							
			String sql = "UPDATE Users Set enabled = ? WHERE email = ?";
					
		    jdbcTemplate.update(sql, sqlParameter);
		 
		}
	
}
