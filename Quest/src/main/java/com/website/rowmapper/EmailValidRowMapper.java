package com.website.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.website.dto.CreateAccountDTO;

public class EmailValidRowMapper implements RowMapper<CreateAccountDTO>{
	
	@Override
	public CreateAccountDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		CreateAccountDTO user = new CreateAccountDTO();
		 		
		user.setEmail(rs.getString("email"));
		
		return user;
	}
	
}
