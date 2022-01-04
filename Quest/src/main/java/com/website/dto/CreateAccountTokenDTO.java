package com.website.dto;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class CreateAccountTokenDTO {

		private static final int EXPIRATION = 60 * 24;

		private int id;
		
		private String email;

		private String token;

		private Date expiryDate;

		
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}

		public Date getExpiryDate() {
			return expiryDate;
		}

		public void setExpiryDate(Date expiryDate) {
			this.expiryDate = expiryDate;
		}

		public static int getExpiration() {
			return EXPIRATION;
		}
		
		public Date calculateExpiryToken() {
		
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MINUTE, EXPIRATION);
			Date expiryDate = calendar.getTime();
		
			return expiryDate;
		}
	}

//public Date calculateExpiryToken() {
//
//	Calendar calendar = Calendar.getInstance();
//	calendar.add(Calendar.MINUTE, EXPIRATION);
//	Date expiry = calendar.getTime();
//
//	return expiry;
//}
//
//public VerificationToken() {
//	super();
//}
//
//public VerificationToken(String token, CreateAccount user) {
//	super();
////	this.id = id;
//	this.token = token;
//	this.userId = user;
//
//}
//
//public int getId() {
//	return id;
//}
//
//public void setId(int id) {
//	this.id = id;
//}
//
//public String getToken() {
//	return token;
//}
//
//public void setToken(String token) {
//	this.token = token;
//}
//
//public CreateAccount getUser() {
//	return userId;
//}
//
//public void setUser(CreateAccount user) {
//	this.userId = user;
//}
//
//public Date getCreatedDate() {
//	return createdDate;
//}
//
//public void setCreatedDate(Date createdDate) {
//	this.createdDate = createdDate;
//}
//
//public Date getExpiryDate() {
//	return expiryDate;
//}
//
//public void setExpiryDate(Date expiryDate) {
//	this.expiryDate = expiryDate;
//}
//
//public static int getExpiration() {
//	return EXPIRATION;
//}
//
//public String getEmail() {
//	return email;
//}
//
//public void setEmail(String email) {
//	this.email = email;
//}
//
//@Override
//public String toString() {
//	return "VerificationToken [id=" + id + ", token=" + token + ", email=" + email + ", createdDate=" + createdDate
//			+ ", expiryDate=" + expiryDate + "]";
//}