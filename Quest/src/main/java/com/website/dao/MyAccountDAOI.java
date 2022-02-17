package com.website.dao;

import com.website.dto.MyAccountDTO;

public interface MyAccountDAOI {
	
	//get users information
	MyAccountDTO getUserInformation(String email);
	//update users information
	void updateUserInformation(MyAccountDTO myAccountDTO, String email);
	
}
