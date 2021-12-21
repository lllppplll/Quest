package com.website.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CreateAccountDTO {

	
	@NotBlank(message = "* Email required")
	private String email;
	
	
	@Size(min = 8, max = 15, message = "* Password must be 8 - 15 characters")	
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
