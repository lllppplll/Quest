package com.website.service;

import org.springframework.ui.Model;
import org.springframework.web.context.request.WebRequest;

import com.website.dto.CreateAccountDTO;
import com.website.dto.CreateAccountTokenDTO;

public interface CreateAccountServiceI {
	

	//Save user details
	//void SaveCreateAccountDetails(CreateAccountDTO userData, String appURL);
    //Form is Valid
	String isValid(boolean result, String email, CreateAccountDTO userData, String appURL, Model model);
	void PasswordEncoding(CreateAccountDTO userData);
	//Email Verification
	//void SendVerificationEmail(String email, String appURL);

	
	//Create Account Success Or Not
	String CreateAccountSuccess(CreateAccountDTO createAccountDTO, String token, Model model);
	String CheckToken(CreateAccountTokenDTO tokenDB);
	void SetEnabled(CreateAccountDTO createAccountDTO, String isSuccess, CreateAccountTokenDTO tokenDB);
	void ReturnMessageIfNotValid(String isSuccess, Model model);
}
