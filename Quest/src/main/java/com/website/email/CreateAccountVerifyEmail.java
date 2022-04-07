package com.website.email;

import java.util.UUID;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.website.dao.CreateAccountDAOI;
import com.website.dto.CreateAccountTokenDTO;


@Component
@PropertySource("classpath:email.properties")
public class CreateAccountVerifyEmail {
	
	@Autowired 
	private Environment env;
	
	@Autowired
	private CreateAccountDAOI dao;
	
	@Autowired
	private CreateAccountTokenDTO expiry;
	
	@Autowired
	private Session sessionEmail;
	
	public void SendEmail(String sendToEmail, String password, String appURL){
		
		// create token
		String token = UUID.randomUUID().toString();
		
		// save token in database
		dao.createAccountWithToken(sendToEmail, password, token, expiry.calculateExpiryToken());
		
		//text to place in email
        String url = env.getProperty("email.path") + "/verify?token=" + token;
        
		String main_title = "Quest";
	    String sub_title = "Thank you for registering.";
		String messageText = "Please click on the link below to activate your account.";

		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(sessionEmail);

			// Set From: header field
			message.setFrom(new InternetAddress(env.getProperty("email.username")));

			// Set To: header field
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(sendToEmail));

			// Set Subject: header field
			message.setSubject("Verification Email");

	         // This mail has 2 part, the BODY and the embedded image
	         MimeMultipart multipart = new MimeMultipart("related");

	         // first part (the html)
	         BodyPart messageBodyPart = new MimeBodyPart();
	         String htmlText = "<h1 align='center' style='font-size: 50px; "
		              + "color: green;"
		              + "text-decoration: underline;' >"+main_title+"</h1>"      
		              //+ "<br/>"
		              
		              //+ "<div align='center' >"
		              //+ "<img  src='cid:swordAndShield' alt='sword and shield' ></div>"      
		              + "<br/>"
		              
		              + "<h1  align='center' style='color: black;' >"+sub_title+"</h1>" 
		              + "<br/>"
		              
		              + "<p align='center' style='color: black; "
		              + "font-size: 20px;' >"+messageText+"</p>"	              
		              + "<br/>"
		              
		              + "<div align='center' ><a align='center' style='width: 260px; "
		              + "font-size: 20px;"
		              + "height: 40px; "
		              + "border: 3px solid blue; "
		              + "border-radius: 5px; "
		              + "color: black; "
		              + "padding: 5px; "
		              + "text-decoration: none;'"
		              + " href='http://"+url+"' >Activate Account</a></div>"
		              + "<br/><br/><br/><br/><br/><br/>";
	         
	         messageBodyPart.setContent(htmlText, "text/html");
	         // add it
	         multipart.addBodyPart(messageBodyPart);

	         
//	         // second part (the image) //  C:/Users/oneph/git/Quest/Quest/src/main/webapp/resources/image/sword_and_shield-min-q.jpg
//	         messageBodyPart = new MimeBodyPart();       
//	         DataSource fds = new FileDataSource(new ClassPathResource("/image/sword_and_shield-min-q.jpg").getInputStream());
// 		     messageBodyPart.setDataHandler(new DataHandler(fds));
//	         messageBodyPart.setHeader("Content-ID", "swordAndShield");

////////////////////////////////////////////////////////////////////////////////
	         // add image to the multipart
//             multipart.addBodyPart(messageBodyPart);

	         
	         // put everything together
	         message.setContent(multipart);
	         

			// Send message
			Transport.send(message);

			//System.out.println("Sent message successfully....");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
}
