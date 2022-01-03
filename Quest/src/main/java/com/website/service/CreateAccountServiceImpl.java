package com.website.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.website.dao.CreateAccountDAOI;
import com.website.dao.CreateAccountDAOImpl;
import com.website.dto.CreateAccountDTO;

@Service
public class CreateAccountServiceImpl implements CreateAccountServiceI {
	
	@Autowired
	private PasswordEncoder bcrypt;

	@Autowired
	private CreateAccountDAOI dao;

	// Constructor Empty
	public CreateAccountServiceImpl() {
	}

	// Constructor
	public CreateAccountServiceImpl(CreateAccountDAOI dao, PasswordEncoder bcrypt) {
		this.dao = dao;
		this.bcrypt = bcrypt;
	}

	@Override
	public String isValid(boolean result) {

		String bool = result ? "create_account/create_account" : "home";
		return bool;
		
	}
	
	//"home" = no errors, is valid
	@Override
	public void SaveCreateAccountDetails(CreateAccountDTO userData,String isValid) {
		
		if(isValid == "home") { 
			
			PasswordEncoding(userData);
			
			dao.SaveCreateAccount(userData); }
		}
	
	@Override
	public void PasswordEncoding(CreateAccountDTO userData) {
		//Encode password before saving in database
		String encodedPassword = bcrypt.encode(userData.getPassword());
		userData.setPassword(encodedPassword);
	}
}