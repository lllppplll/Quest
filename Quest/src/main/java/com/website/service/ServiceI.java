package com.website.service;

import com.website.dto.CreateAccountDTO;

public interface ServiceI {
	
//	Send details to database
	void SaveCreateAccount(CreateAccountDTO userData);
	
}
