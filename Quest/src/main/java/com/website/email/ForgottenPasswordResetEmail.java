package com.website.email;

import java.util.UUID;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.website.dao.ForgottenPasswordDAOI;
import com.website.dto.ForgottenPasswordTokenDTO;

@Component
@PropertySource("classpath:email.properties")
public class ForgottenPasswordResetEmail {
	
	@Autowired
	private Environment env; 
	
	@Autowired
	private ForgottenPasswordDAOI dao;

	@Autowired
	private ForgottenPasswordTokenDTO expiry;
	
	@Autowired
	private Session sessionEmail;

	public void sendEmail(String sendToEmail, String appURL) {

		// create token
		String token = UUID.randomUUID().toString();

		// save token in database
		dao.saveToken(sendToEmail, token, expiry.calculateExpiryToken());
		
		//production
        String url = env.getProperty("email.path") + "/reset?token=" + token; 
    	
		String messageText = "Please click on the below " + "link to change your password.";

		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(sessionEmail);

			// Set From: header field
			message.setFrom(new InternetAddress(env.getProperty("email.username")));

			// Set To: header field
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(sendToEmail));

			// Set Subject: header field
			message.setSubject("Forgotten Password");

			// Put the content of your message
			message.setText(messageText + "http://" + url);

			// Send message
			Transport.send(message);

			// System.out.println("Sent message successfully....");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

}