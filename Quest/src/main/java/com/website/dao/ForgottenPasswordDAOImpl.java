package com.website.dao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.website.dto.CreateAccountTokenDTO;
import com.website.dto.ForgottenPasswordNewDTO;
import com.website.dto.ForgottenPasswordTokenDTO;
import com.website.rowmapper.EmailVerificationRowMapper;
import com.website.rowmapper.NewPasswordRowMapper;

@Repository
public class ForgottenPasswordDAOImpl implements ForgottenPasswordDAOI{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
//	@Override
//	public void sendEmail(String email, String appURL) {
//		// TODO Auto-generated method stub
//		
//	}

	@Override
	public void saveToken(String email, String token, Date calculateExpiryToken) {
		
		Object[] sqlParameter = {email, token, calculateExpiryToken};		
		
		String sql = "INSERT INTO PasswordTokens(email, token, expiryDate) VALUES ( ?,?,? )";
		
	    jdbcTemplate.update(sql, sqlParameter);
	    
	}

	@Override
	public ForgottenPasswordTokenDTO getToken(String token) {
		
        String sql = "SELECT * FROM PasswordTokens WHERE token = ? ";
			
		
        ForgottenPasswordTokenDTO getToken = jdbcTemplate.queryForObject(sql, new NewPasswordRowMapper(), token);

		return getToken;
		
	}

	@Override
	public void saveNewPassword(String email, String password) {
		
		Object[] sqlParameter = {password, email};		
		
		String sql = "UPDATE Users Set password = ? WHERE email = ?";
		
	    jdbcTemplate.update(sql, sqlParameter);
		
	}

//	@Override
//	public void enableAccount(String email) {
//		
//		Object[] sqlParameter = {email};		
//		
//		String sql = "UPDATE Users Set enable = 1 WHERE email = ?";
//		
//	    jdbcTemplate.update(sql, sqlParameter);
//		
//	}

}
