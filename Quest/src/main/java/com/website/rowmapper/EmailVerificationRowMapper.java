package com.website.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.website.dto.CreateAccountTokenDTO;

public class EmailVerificationRowMapper implements RowMapper<CreateAccountTokenDTO> {


	@Override
	public CreateAccountTokenDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		CreateAccountTokenDTO user = new CreateAccountTokenDTO();
		 		
		user.setId(rs.getInt("id"));
		user.setEmail(rs.getString("email"));
		user.setToken(rs.getString("token"));
		user.setExpiryDate(rs.getDate("expiryDate"));
		
		return user;
	}
}
