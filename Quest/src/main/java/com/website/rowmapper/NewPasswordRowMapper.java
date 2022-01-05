package com.website.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.website.dto.CreateAccountTokenDTO;
import com.website.dto.ForgottenPasswordTokenDTO;

public class NewPasswordRowMapper implements RowMapper<ForgottenPasswordTokenDTO> {


	@Override
	public ForgottenPasswordTokenDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		ForgottenPasswordTokenDTO user = new ForgottenPasswordTokenDTO();
		 		
		user.setId(rs.getInt("id"));
		user.setEmail(rs.getString("email"));
		user.setToken(rs.getString("token"));
		user.setExpiryDate(rs.getDate("expiryDate"));
		
		return user;
	}
}
