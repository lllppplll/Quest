package com.website.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.website.dao.DAOI;
import com.website.dto.CreateAccountDTO;

@Service
public class ServiceImpl implements ServiceI {
	
	@Autowired
	private DAOI dao;

	@Override
	public void SaveCreateAccount(CreateAccountDTO userData) {
		
		//Goes to class DAOI
		dao.SaveCreateAccount(userData);
		
	}
	
}
