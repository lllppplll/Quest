package com.website.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.website.dao.DAOI;
import com.website.dto.CreateAccountDTO;

@Service
public class ServiceImpl implements ServiceI {
	
	@Autowired
	private DAOI dao;
	
	
	@Override
	public String isValid(boolean result, CreateAccountDTO userData) {
		
		boolean Inputs = InputFieldErrors(result);
		
		if(Inputs) {
			//Save User Data
			SaveCreateAccount(userData);
			
			return "home";			
		}
		else{
			return "create_account/create_account";
		}
	}
	
	@Override
	public void SaveCreateAccount(CreateAccountDTO userData) {	
		//Save User Data
		dao.SaveCreateAccount(userData);	
	}
	

	@Override
	public boolean InputFieldErrors(boolean result) {
		
		if(result) {			
		return false;
		}
		else 
		{ return true; 
		}
	}

	
}
