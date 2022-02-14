package com.website.email;

import java.util.Properties;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.website.dao.CreateAccountDAOI;
import com.website.dto.CreateAccountTokenDTO;

@Component
public class CreateAccountVerifyEmail {
	
	@Autowired
	private CreateAccountDAOI dao;
	
	@Autowired
	private CreateAccountTokenDTO expiry;
	
	public void SendEmail(String sendToEmail, String password, String appURL) {
		
		// create token
		String token = UUID.randomUUID().toString();
		
		// save token in database
		//dao.saveToken(sendToEmail, token, expiry.calculateExpiryToken());
		dao.createAccountWithToken(sendToEmail, password, token, expiry.calculateExpiryToken());
		
		
		String host = "smtp.aol.com";
		final String username = "OnePhoenixF";
		final String passwordEmail = "zzhbswgmljbifnjq";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");// it’s optional in Mailtrap
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");// use one of the options in the SMTP settings tab in your Mailtrap Inbox

		// Get the Session object.
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, passwordEmail);
			}
		});
		

		String url = appURL + "/verify?token=" + token;
//		String url = "adventure.eu-west-2.elasticbeanstalk.com" + "/resetPassword?token=" + token;

		String messageText = "Please click on the below " 
		                     + "link to activate your account.";

		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);

			// Set From: header field
			message.setFrom(new InternetAddress("OnePhoenixF@aol.com"));

			// Set To: header field
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(sendToEmail));

			// Set Subject: header field
			message.setSubject("Verification Email");

			// Put the content of your message
			message.setText(messageText + "http://localhost:8080" + url);

			// Send message
			Transport.send(message);

			//System.out.println("Sent message successfully....");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
}
