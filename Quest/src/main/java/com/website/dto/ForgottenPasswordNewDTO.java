package com.website.dto;

import javax.validation.constraints.Size;

public class ForgottenPasswordNewDTO {
	//@Size(min = 8, max = 15, message = "* Password must be 8 - 15 characters")
	private String newPassword;
	
	//@Size(min = 8, max = 15, message = "* Password must be 8 - 15 characters")
	private String confirmPassword;
	
	private String email;

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassord) {
		this.confirmPassword = confirmPassord;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
