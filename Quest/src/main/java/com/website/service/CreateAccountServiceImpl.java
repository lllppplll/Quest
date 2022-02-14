package com.website.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.context.request.WebRequest;

import com.website.dao.CreateAccountDAOI;
import com.website.dto.CreateAccountDTO;
import com.website.dto.CreateAccountTokenDTO;
import com.website.email.CreateAccountVerifyEmail;

import jdk.internal.org.jline.utils.Log;

@Service
public class CreateAccountServiceImpl implements CreateAccountServiceI {

	@Autowired
	private PasswordEncoder bcrypt;

	@Autowired
	private CreateAccountDAOI dao;

	@Autowired
	private CreateAccountVerifyEmail verify;

	private CreateAccountTokenDTO tokenDB;

	// Constructor Empty
	public CreateAccountServiceImpl() {
	}

	// Constructor
	public CreateAccountServiceImpl(CreateAccountDAOI dao, PasswordEncoder bcrypt) {
		this.dao = dao;
		this.bcrypt = bcrypt;
	}

////////////////////////////////////////////////////////////////////////////////
	// "home" = no errors, is valid
//	@Override
//	public void SaveCreateAccountDetails(CreateAccountDTO userData, String appURL) {
//
//		// Encode Password
//		PasswordEncoding(userData);
//		// Save User Details
//		dao.SaveCreateAccount(userData);
//		// Send Verification Email
//		SendVerificationEmail(userData.getEmail(), appURL);
//
//	}


	@Override
	public String isValid(boolean result, String email, CreateAccountDTO userData, String appURL, Model model) {

//		String bool = result ? "create_account/create_account" : "create_account/create_account_verify";
//		return bool;
		
		//if errors on input fields
		if(result) { return "create_account/create_account"; }
		
		
		//if no errors on input fields
		if(!result) { 
			
			//check email
			int user = dao.isEmail(email);
			
			//email exists
			if(user == 1) {
				//send message
				model.addAttribute("isEmail", "Email already exists.");
				return "create_account/create_account";
			}
			//email does not exist
			else {
				
				// Encode Password
				PasswordEncoding(userData);
				// Save User Details
				//dao.SaveCreateAccount(userData);
				// Send Verification Email
				verify.SendEmail(userData.getEmail(), userData.getPassword(), appURL);
				//SendVerificationEmail(userData.getEmail(), appURL);
				
			}	
		}
		
		return "create_account/create_account_verify";	
	}

	@Override
	public void PasswordEncoding(CreateAccountDTO userData) {
		// Encode password before saving in database
		String encodedPassword = bcrypt.encode(userData.getPassword());
		userData.setPassword(encodedPassword);
	}

	/*
	 * @Override public void SendVerificationEmail(String email, String appURL) {
	 * verify.SendEmail(email, appURL); }
	 */
////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////
	@Override
	public String CreateAccountSuccess(CreateAccountDTO createAccountDTO, String token, Model model) {

		// Get Token From Database
		tokenDB = dao.getToken(token);
		// Check If Email Is Not Expired Or Is Not Null
		String isSuccess = CheckToken(tokenDB);

		// Set Enable In Database To True To Allow Access By User
		SetEnabled(createAccountDTO, isSuccess, tokenDB);
		// Return Message If Null Or Expired
		ReturnMessageIfNotValid(isSuccess, model);

		return isSuccess;
	}

	@Override
	public String CheckToken(CreateAccountTokenDTO tokenDB) {

		// Get Date
		Calendar calendar = Calendar.getInstance();

		boolean result = (tokenDB == null || ((tokenDB.getExpiryDate().getTime() - calendar.getTime().getTime()) <= 0));
		String isSuccess = result ? "create_account/create_account" : "create_account/create_account_success";

		return isSuccess;
	}

	@Override
	public void SetEnabled(CreateAccountDTO createAccountDTO, String isSuccess, CreateAccountTokenDTO tokenDB) {
		if (isSuccess == "create_account/create_account_success") {
			// Get Email
			String email = tokenDB.getEmail();
			// Set Enabled In DTO
			createAccountDTO.setEnabled(true);
			// Save Enabled In Database
			dao.enableAccount(email, createAccountDTO.getEnabled());
		}
	}

	@Override
	public void ReturnMessageIfNotValid(String isSuccess, Model model) {
		if (isSuccess == "create_account/create_account") {
			model.addAttribute("returnMessage",
					"Verification email expired or does not exist. Please enter details again to resend verification email.");
		}
	}
////////////////////////////////////////////////////////////////////////////////


}
