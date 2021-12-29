package com.website.service;

import org.springframework.validation.BindingResult;

import com.website.dto.CreateAccountDTO;

public interface ServiceI {

//  Form is Valid
	String isValid(boolean result, CreateAccountDTO userData);
//	Send details to database
	void SaveCreateAccount(CreateAccountDTO userData);
//  Input fields are valid
	boolean InputFieldErrors(boolean result);
	
}
