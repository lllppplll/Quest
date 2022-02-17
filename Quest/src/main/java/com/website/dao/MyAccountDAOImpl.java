package com.website.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.website.dto.MyAccountDTO;
import com.website.rowmapper.MyAccountRowMapper;

@Repository
public class MyAccountDAOImpl implements MyAccountDAOI {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public MyAccountDTO getUserInformation(String email) {
		
        String sql = "SELECT * FROM UsersDetails WHERE email = ? ";	
		
         MyAccountDTO userInformation = jdbcTemplate.queryForObject(sql, new MyAccountRowMapper(), email);
		
		return userInformation;
	}

	@Override
	public void updateUserInformation(MyAccountDTO myAccountDTO, String email) {
		// TODO Auto-generated method stub
		
		Object[] sqlParameter = {
				myAccountDTO.getFirstname(), 
				myAccountDTO.getSurname(), 
				myAccountDTO.getHousename(), 
				myAccountDTO.getAddress1(),
				myAccountDTO.getAddress2(), 
				myAccountDTO.getTown(), 
				myAccountDTO.getPostcode(), 
				myAccountDTO.getPhonenumber(), 
				myAccountDTO.getDob(), email};	
		
		String sql = "UPDATE UsersDetails Set firstname = ?, surname = ?, housename = ?, address1 = ?, address2 = ?, "
				+ "town = ?, postcode = ?, phonenumber = ?, dob = ? WHERE email = ?";
		
		
	    jdbcTemplate.update(sql, sqlParameter);

		
	}

}
