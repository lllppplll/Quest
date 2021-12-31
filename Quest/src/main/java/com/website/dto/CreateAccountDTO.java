package com.website.dto;

import java.sql.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CreateAccountDTO {

	private int id;
	
	@NotBlank(message = "* Email required")
	private String email;
	
	@Size(min = 8, max = 15, message = "* Password must be 8 - 15 characters")	
	private String password;
	
	private String role = "ROLE_USER";
	private int enabled = 0;
	
	
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int b) {
		this.enabled = b;
	}

	@Override
	public String toString() {
		return "CreateAccountDTO [id=" + id + ", email=" + email + ", password=" + password + ", role=" + role
				+ ", enabled=" + enabled + "]";
	}
	
}
