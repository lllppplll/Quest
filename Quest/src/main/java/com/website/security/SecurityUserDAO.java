package com.website.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.website.dto.CreateAccountDTO;

@Repository
public class SecurityUserDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public CreateAccountDTO getUserByEmail(String email) {
		
				Object[] sqlParameters = { email };
				
				String sql = "SELECT * FROM Users WHERE email = ?";
			
				CreateAccountDTO user = jdbcTemplate.queryForObject(sql, sqlParameters, new BeanPropertyRowMapper<CreateAccountDTO>(CreateAccountDTO.class));
				
				return user;
	}
	
}
