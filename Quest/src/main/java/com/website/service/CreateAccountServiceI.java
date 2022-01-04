package com.website.service;

import org.springframework.web.context.request.WebRequest;

import com.website.dto.CreateAccountDTO;
import com.website.dto.CreateAccountTokenDTO;

public interface CreateAccountServiceI {
	
    //Form is Valid
	String isValid(boolean result);
	//Save user details
	void SaveCreateAccountDetails(CreateAccountDTO userData, String appURL, String isValid);
	void PasswordEncoding(CreateAccountDTO userData);
	//Email Verification
	void SendVerificationEmail(String email, String appURL);
	CreateAccountTokenDTO getToken(String token);
	void enableAccount(String email, boolean enable);

}
