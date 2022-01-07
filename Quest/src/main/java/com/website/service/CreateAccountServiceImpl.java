package com.website.service;

import java.util.Calendar;

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

////////////////////////////////////////////////////////////////////////////////
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
	public String isValid(boolean result) {

		String bool = result ? "create_account/create_account" : "create_account/create_account_verify";
		return bool;
		
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
////////////////////////////////////////////////////////////////////////////////
	
	
////////////////////////////////////////////////////////////////////////////////
	@Override
	public String CreateAccountSuccess(CreateAccountDTO createAccountDTO, String token) {
		//Get Token From Database
		CreateAccountTokenDTO tokenDB = dao.getToken(token);	
		//Check If Email Is Not Expired Or Is Not Null
		String isSuccess = CheckToken(tokenDB);
		SetEnabled(createAccountDTO, isSuccess, tokenDB);
		
		return isSuccess;
	}
	
	@Override
	public String CheckToken(CreateAccountTokenDTO tokenDB) {
		
		//Get Date
		Calendar calendar = Calendar.getInstance();
		
		boolean result = (tokenDB == null || ((tokenDB.getExpiryDate().getTime() - calendar.getTime().getTime()) <= 0));
		String isSuccess = result ? "create_account/create_account" : "create_account/create_account_success";
		
		return isSuccess;
		}
	
	@Override
	public void SetEnabled(CreateAccountDTO createAccountDTO, String isSuccess, CreateAccountTokenDTO tokenDB) {
		if(isSuccess == "create_account/create_account_success") {
		//Get Email
		String email = tokenDB.getEmail();
		//Set Enabled In DTO
		createAccountDTO.setEnabled(true);
		//Save Enabled In Database
		dao.enableAccount(email, createAccountDTO.getEnabled());
		}
	}
////////////////////////////////////////////////////////////////////////////////
		
	}
	