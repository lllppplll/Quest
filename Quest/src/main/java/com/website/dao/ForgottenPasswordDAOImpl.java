package com.website.dao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.website.dto.ForgottenPasswordTokenDTO;
import com.website.rowmapper.NewPasswordRowMapper;

@Repository
public class ForgottenPasswordDAOImpl implements ForgottenPasswordDAOI{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//check email
	@Override
	public int checkEmail(String email) {
		
		String sql = "SELECT CASE WHEN EXISTS (SELECT * FROM Users WHERE email = ? )THEN CAST(1 AS BIT)ELSE CAST(0 AS BIT) END";

        int isEmail = jdbcTemplate.queryForObject(sql, Integer.class, email);
	    
		return isEmail;
	}

	@Override
	public void saveToken(String email, String token, Date calculateExpiryToken) {
		
		Object[] sqlParameter = {email, token, calculateExpiryToken};		
		
		String sql = "INSERT INTO PasswordTokens(email, token, expiryDate) VALUES ( ?,?,? )";
		
	    jdbcTemplate.update(sql, sqlParameter);
	    
	}

	@Override
	public ForgottenPasswordTokenDTO getToken(String token) {
		
        String sql = "SELECT * FROM PasswordTokens WHERE token = ? ";
        //can return a null value (used instead of queryforobject)
        return DataAccessUtils.singleResult(jdbcTemplate.query(sql, new NewPasswordRowMapper(), token));

	}

	@Override
	public void saveNewPassword(String email, String password) {
		
		Object[] sqlParameter = {password, email};		
		
		String sql = "UPDATE Users Set password = ? WHERE email = ?";
		
	    jdbcTemplate.update(sql, sqlParameter);
		
	}
}
