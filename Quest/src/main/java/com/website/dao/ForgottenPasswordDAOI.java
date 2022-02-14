package com.website.dao;

import java.util.Date;

import com.website.dto.ForgottenPasswordTokenDTO;

public interface ForgottenPasswordDAOI {
	
    //Save Token In Database
	void saveToken(String email, String token, Date calculateExpiryToken);
	//get token
	ForgottenPasswordTokenDTO getToken(String token);
	//save new password
	void saveNewPassword(String email, String password);
//	//enable account enable = 1
//	void enableAccount(String email);
	
}
