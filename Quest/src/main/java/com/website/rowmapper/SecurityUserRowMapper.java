package com.website.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.website.dto.CreateAccountDTO;

public class SecurityUserRowMapper implements RowMapper<CreateAccountDTO>{
	
	@Override
	public CreateAccountDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		CreateAccountDTO user = new CreateAccountDTO();
				
		user.setId(rs.getInt("id"));		
		user.setEmail(rs.getString("email"));	
		user.setPassword(rs.getString("password"));
		user.setRoles(rs.getString("roles"));
		user.setEnabled(rs.getBoolean("enabled"));
		
		return user;
	}
}
