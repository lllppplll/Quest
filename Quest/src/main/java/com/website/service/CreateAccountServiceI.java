package com.website.service;

import com.website.dto.CreateAccountDTO;
import com.website.dto.CreateAccountTokenDTO;

public interface CreateAccountServiceI {

    //Form is Valid
	String isValid(boolean result, CreateAccountDTO userData, String appURL);
	void PasswordEncoding(CreateAccountDTO userData);

	
	//Create Account Success Or Not
	String CreateAccountSuccess(CreateAccountDTO createAccountDTO, String token);
	String CheckToken(CreateAccountTokenDTO tokenDB);
	void SetEnabled(CreateAccountDTO createAccountDTO, String isSuccess, CreateAccountTokenDTO tokenDB);
}
