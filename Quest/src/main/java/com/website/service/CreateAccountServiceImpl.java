package com.website.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.website.dao.CreateAccountDAOI;
import com.website.dto.CreateAccountDTO;
import com.website.dto.CreateAccountTokenDTO;
import com.website.email.CreateAccountVerifyEmail;

@Service
public class CreateAccountServiceImpl implements CreateAccountServiceI {
	
	@Autowired
	private PasswordEncoder bcrypt;

	@Autowired
	private CreateAccountDAOI dao;
	
	@Autowired
	private CreateAccountVerifyEmail verify;

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

		String bool = result ? "create_account/create_account" : "create_account/create_account_verify";
		return bool;
		
	}
	
	//"home" = no errors, is valid
	@Override
	public void SaveCreateAccountDetails(CreateAccountDTO userData, String appURL, String isValid) {
		
		if(isValid == "create_account/create_account_verify") { 
			
			//Encode Password
			PasswordEncoding(userData);
			//Save User Details
			dao.SaveCreateAccount(userData);
			//Send Verification Email
			SendVerificationEmail(userData.getEmail(), appURL);
		}
	}

	@Override
	public void PasswordEncoding(CreateAccountDTO userData) {
		//Encode password before saving in database
		String encodedPassword = bcrypt.encode(userData.getPassword());
		userData.setPassword(encodedPassword);
	}


	@Override
	public void SendVerificationEmail(String email, String appURL) {
			verify.SendEmail(email, appURL);
	}

	@Override
	public CreateAccountTokenDTO getToken(String token) {
		
		return dao.getToken(token);
	}

	@Override
	public void enableAccount(String email, boolean enable) {
		dao.enableAccount(email, enable);
	}
	
}