package com.website.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.website.dao.DAOI;

@Service
public class ServiceImpl implements ServiceI {
	
	@Autowired
	private DAOI dao;
	
}
