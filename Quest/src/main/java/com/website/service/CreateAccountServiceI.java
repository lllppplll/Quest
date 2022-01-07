package com.website.service;

import com.website.dto.CreateAccountDTO;
import com.website.dto.CreateAccountTokenDTO;

public interface CreateAccountServiceI {
	

	//Save user details
	void SaveCreateAccountDetails(CreateAccountDTO userData, String appURL, String isValid);
    //Form is Valid
	String isValid(boolean result);
	void PasswordEncoding(CreateAccountDTO userData);
	//Email Verification
	void SendVerificationEmail(String email, String appURL);

	
	//Create Account Success Or Not
	String CreateAccountSuccess(CreateAccountDTO createAccountDTO, String token);
	String CheckToken(CreateAccountTokenDTO tokenDB);
	void SetEnabled(CreateAccountDTO createAccountDTO, String isSuccess, CreateAccountTokenDTO tokenDB);

}
