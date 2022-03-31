package com.website.service;

public interface ContactServiceI {
	
	boolean SendEmail(String to, String from, String subject, String body, String filename);
	
}
