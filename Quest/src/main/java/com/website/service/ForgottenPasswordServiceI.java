package com.website.service;

import org.springframework.ui.Model;

import com.website.dto.ForgottenPasswordNewDTO;
import com.website.dto.ForgottenPasswordTokenDTO;

public interface ForgottenPasswordServiceI {
	
	//Send Email
	void sendEmail(String email, String appURL);
	//Save Token In Database
	void saveToken(String email, String appURL);
	//Get Token From Database
	String checkToken(String token, Model model);
	//Save New Password In Database
	String checkNewPassword(String email, String passwordNew, String passwordConfirm, String token, Model model);
	
	
}
