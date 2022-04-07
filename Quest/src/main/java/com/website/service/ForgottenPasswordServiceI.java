package com.website.service;

import org.springframework.ui.Model;

public interface ForgottenPasswordServiceI {
	
	//check if email exists
	int checkEmail(String email);
	//Send Email
	void sendEmail(String email, String appURL);
	//Save Token In Database
	void saveToken(String email, String appURL);
	//Get Token From Database
	String checkToken(String token, Model model);
	//Save New Password In Database
	String checkNewPassword(String email, String newPassword, String confirmPassword, boolean hasErrors, Model model);
	
}
