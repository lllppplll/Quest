package com.website.service;

import com.website.dto.CreateAccountDTO;

public interface CreateAccountServiceI {
	
//  Form is Valid
//	String isValid(boolean result, CreateAccountDTO userData);
	String isValid(boolean result);
//  Input fields are valid
//	boolean InputFieldErrors(boolean result);
	//Return form
//	String Form(boolean input, CreateAccountDTO userData);
	//Save user details
	void SaveCreateAccountDetails(CreateAccountDTO userData, String isValid);
	void PasswordEncoding(CreateAccountDTO userData);

}
