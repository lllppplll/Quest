package com.website.service;

import com.website.dto.MyAccountDTO;

public interface MyAccountServiceI {
	
	//get users information
	MyAccountDTO getUserInformation(String email);
	//update users information
	void updateUserInformation(MyAccountDTO myAccountDTO, String email);
	
}
