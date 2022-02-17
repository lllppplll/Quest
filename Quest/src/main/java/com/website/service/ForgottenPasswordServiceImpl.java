package com.website.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.website.dao.ForgottenPasswordDAOI;
import com.website.dto.ForgottenPasswordTokenDTO;
import com.website.email.ForgottenPasswordResetEmail;

@Service
public class ForgottenPasswordServiceImpl implements ForgottenPasswordServiceI {
	
	@Autowired
	private ForgottenPasswordResetEmail reset;
	
	@Autowired
	private ForgottenPasswordDAOI dao;
	
	@Autowired
	private PasswordEncoder bcrypt;
	

	@Override
	public void sendEmail(String email, String appURL) {
		reset.sendEmail(email, appURL);
	}

	@Override
	public void saveToken(String email, String appURL) {
			//dao.saveToken(email, appURL);
	}


	@Override
	public String checkToken(String token, Model model) {
				
		//Get Token From Database
		ForgottenPasswordTokenDTO tokenDB = dao.getToken(token);
		//Get Date
		Calendar calendar = Calendar.getInstance();
		
		//Check If Token Is Present/Check If Email message Is Not Expired
		if (tokenDB == null || (tokenDB.getExpiryDate().getTime() - calendar.getTime().getTime()) <= 0) {
			//return message
			model.addAttribute("tokenNotValid", "Verification email expired or does not exist. Please enter details again to resend verification email.");
			return "forgotten_password/forgotten_password";
		}
		
		model.addAttribute("email", tokenDB.getEmail());
		model.addAttribute("token", tokenDB.getToken());
		
		return "forgotten_password/forgotten_password_new";
		
	}

	@Override
	public String checkNewPassword(String email, String passwordNew, String passwordConfirm, String token, Model model) {
		
//	    //check if passwords match
		boolean matches = passwordNew.equals(passwordConfirm);
		//boolean matches = bcryptPasswordEncoded.matches(resetPasswordDTO.getNewPassword(), resetPasswordDTO.getConfirmPassword());
	
		 if(matches) {
			//encode password
			 String passwordEncoded = bcrypt.encode(passwordNew);
			//save new password in database
			dao.saveNewPassword(email, passwordEncoded);
		}	 
		 //if not matches
		 if(!matches) {
				//check for match error
			    model.addAttribute("passwordsNotMatch", "Passwords do not match, Please try again.");

			 return "forgotten_password/forgotten_password_new";	 
		 }
		 
		return "forgotten_password/forgotten_password_success";
		
	}
	
}
