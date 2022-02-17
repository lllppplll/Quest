package com.website.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.website.dao.MyAccountDAOI;
import com.website.dto.MyAccountDTO;

@Service
public class MyAccountServiceImpl implements MyAccountServiceI{
	
	@Autowired
	private MyAccountDAOI dao;

	@Override
	public MyAccountDTO getUserInformation(String email) {
		
		MyAccountDTO userInformation = dao.getUserInformation(email);
		return userInformation;
	}

	@Override
	public void updateUserInformation(MyAccountDTO myAccountDTO, String email) {
		// TODO Auto-generated method stub
		dao.updateUserInformation(myAccountDTO, email);	
	}

}
