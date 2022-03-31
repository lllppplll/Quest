package com.website.dao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.website.dto.CreateAccountDTO;
import com.website.dto.CreateAccountTokenDTO;
import com.website.rowmapper.EmailValidRowMapper;
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
	public CreateAccountTokenDTO getToken(String token) {
				
        String sql = "SELECT * FROM VerificationTokens WHERE token = ?";	
        //queryforobject does not except 0 rows or more than 1.
        //if user types token value in url no columns are found resulting in an exception.
        //dataAccessUtils will return null if 0 rows.  
        return DataAccessUtils.singleResult(jdbcTemplate.query(sql, new EmailVerificationRowMapper(), token));
		
	}

	@Override
	public void enableAccount(String email, boolean enable) {
		
			Object[] sqlParameter = { enable, email};					
			String sql = "UPDATE Users Set enabled = ? WHERE email = ?";			
		    jdbcTemplate.update(sql, sqlParameter);
		 
		}

	@Override
	public CreateAccountDTO isEmail(String email) {
		
		//String sql = "SELECT CASE WHEN EXISTS (SELECT email FROM Users WHERE email = ? )THEN CAST(1 AS BIT)ELSE CAST(0 AS BIT) END";
		//String sql = "SELECT CASE WHEN  EXISTS (SELECT email FROM Users WHERE email = ?) THEN 1 ELSE 0 END FROM Users";
		//String sql = "SELECT CASE WHEN email = ? THEN 1 ELSE 0 END FROM Users";
		String sql = "SELECT email FROM Users WHERE email = ?";
		
        //int user = jdbcTemplate.queryForObject(sql, Integer.class, email);
        CreateAccountDTO user = DataAccessUtils.singleResult(jdbcTemplate.query(sql, new EmailValidRowMapper(), email));
		
		return user;
		
	}
	
	
	@Override
	@Transactional
	public void createAccountWithToken(String email, String password, String token, Date calculateExpiryToken) {
        
		//Connecting and saving data in database
		Object[] sqlParameters = {email, password, "ROLE_USER", 0};
		Object[] sqlParameters1 = {email};
		Object[] sqlParameter2 = {email, token, calculateExpiryToken};
		
		//Prepared SQL statement
		String sql = "INSERT INTO Users(email, password, roles, enabled) VALUES ( ?,?,?,? )";
		String sql1 = "INSERT INTO UsersDetails(email) VALUES ( ? )";
		String sql2 = "INSERT INTO VerificationTokens(email, token, expiryDate) VALUES ( ?,?,? )";
		
		//use JDBC template
		jdbcTemplate.update(sql, sqlParameters);
		jdbcTemplate.update(sql1, sqlParameters1);
	    jdbcTemplate.update(sql2, sqlParameter2);
		
	}
	
}
