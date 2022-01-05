package com.website.service;

import com.website.dto.ForgottenPasswordNewDTO;
import com.website.dto.ForgottenPasswordTokenDTO;

public interface ForgottenPasswordServiceI {
	
	//Send Email
	void sendEmail(String email, String appURL);
	//Save Token In Database
	void saveToken(String email, String appURL);
	//Get Token From Database
	ForgottenPasswordTokenDTO getToken(String token);
	//Save New Password In Database
	void saveNewPassword(String email, String password);
	
}
