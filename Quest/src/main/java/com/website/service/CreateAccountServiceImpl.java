package com.website.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.website.dao.CreateAccountDAOI;
import com.website.dto.CreateAccountDTO;

@Service
public class CreateAccountServiceImpl implements CreateAccountServiceI {
	
	@Autowired
	private PasswordEncoder bcryptPasswordEncoded;

	@Autowired
	private CreateAccountDAOI dao;

	// Constructor Empty
	public CreateAccountServiceImpl() {
	}

	// Constructor
	public CreateAccountServiceImpl(CreateAccountDAOI dao) {
		this.dao = dao;
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
			//Encode password before saving in database
			String encodedPassword = bcryptPasswordEncoded.encode(userData.getPassword());
			userData.setPassword(encodedPassword);

			dao.SaveCreateAccount(userData); }
		}
}