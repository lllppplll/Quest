package com.website.service;


import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactServiceI{
	
	@Autowired
	private Session sessionEmail;
	
	
	
	public boolean SendEmail(String to, String from, String subject, String body) {
		
		if(to == "") {
			return false;
		};
	

		
		// text to place in email

		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(sessionEmail);

			// Set From: header field
			message.setFrom(new InternetAddress(from));

			// Set To: header field
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

			// Set Subject: header field
			message.setSubject(subject);
			
			//message part
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText(body);
	
			// add multiparts
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			
			///////////////////////////////////////////
//			if(filename != ""){
//			//attachment part
//			MimeBodyPart attachmentPart = new MimeBodyPart();
//			attachmentPart.attachFile(new File(filename));	
//			multipart.addBodyPart(attachmentPart);
//			}
			///////////////////////////////////////////
			
			// Send message
			message.setContent(multipart);
			Transport.send(message);

			// System.out.println("Sent message successfully....");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		return true;
	}

}
