package com.website.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CreateAccountDTO {

	private int id;
	
	@NotBlank(message = "* Email required")
	private String email;
	
	@Size(min = 8, max = 15, message = "* Password must be 8 - 15 characters")	
	private String password;
	
	private String roles = "ROLES_USER";
	
	private boolean enabled = false;
	
	
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

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean b) {
		this.enabled = b;
	}

	@Override
	public String toString() {
		return "CreateAccountDTO [id=" + id + ", email=" + email + ", password=" + password + ", roles=" + roles
				+ ", enabled=" + enabled + "]";
	}
	
}
