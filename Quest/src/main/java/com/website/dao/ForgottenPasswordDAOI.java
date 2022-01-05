package com.website.dao;

import java.util.Date;

import com.website.dto.ForgottenPasswordTokenDTO;

public interface ForgottenPasswordDAOI {
	
    //Save Token In Database
	void saveToken(String email, String token, Date calculateExpiryToken);

	ForgottenPasswordTokenDTO getToken(String token);
	void saveNewPassword(String email, String password);
	
}
