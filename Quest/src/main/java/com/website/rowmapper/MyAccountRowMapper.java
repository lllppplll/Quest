package com.website.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.website.dto.CreateAccountTokenDTO;
import com.website.dto.MyAccountDTO;

public class MyAccountRowMapper implements RowMapper<MyAccountDTO>{

	@Override
	public MyAccountDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		MyAccountDTO userInformation = new MyAccountDTO();
 		
		userInformation.setId(rs.getInt("id"));
		userInformation.setFirstname(rs.getString("firstname"));	
		userInformation.setSurname(rs.getString("surname"));
		userInformation.setHousename(rs.getString("housename"));
		userInformation.setAddress1(rs.getString("address1"));
		userInformation.setAddress2(rs.getString("address2"));
		userInformation.setTown(rs.getString("town"));
		userInformation.setPostcode(rs.getString("postcode"));
		userInformation.setPhonenumber(rs.getString("phonenumber"));
		userInformation.setDob(rs.getString("dob"));
		
		return userInformation;
	}

}
