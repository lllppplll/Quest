package com.website.service;

public interface ContactServiceI {
	
	void SendEmail(String to, String from, String subject, String body, String filename);
	
}
