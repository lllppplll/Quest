package com.website.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.website.dao.ForgottenPasswordDAOI;
import com.website.dto.ForgottenPasswordNewDTO;
import com.website.dto.ForgottenPasswordTokenDTO;
import com.website.email.ForgottenPasswordResetEmail;

@Service
public class ForgottenPasswordServiceImpl implements ForgottenPasswordServiceI {
	
	@Autowired
	private ForgottenPasswordResetEmail reset;
	
	@Autowired
	private ForgottenPasswordDAOI dao;
	

	@Override
	public void sendEmail(String email, String appURL) {
		reset.sendEmail(email, appURL);
	}

	@Override
	public void saveToken(String email, String appURL) {
			//dao.saveToken(email, appURL);
	}

	@Override
	public ForgottenPasswordTokenDTO getToken(String token) {
		return dao.getToken(token);
	}

	@Override
	public void saveNewPassword(String email, String password) {
		dao.saveNewPassword(email, password);
		
	}
	
}
