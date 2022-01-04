package com.website.dao;

import java.util.Date;

import com.website.dto.CreateAccountDTO;
import com.website.dto.CreateAccountTokenDTO;

public interface CreateAccountDAOI {
	
	void SaveCreateAccount(CreateAccountDTO userData);
	
	//Email Verification
	void saveToken(String email, String token, Date date);
	CreateAccountTokenDTO getToken(String token);
	void enableAccount(String email, boolean enable);
	
}
