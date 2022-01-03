package com.website.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class SecurityUserDTO implements UserDetails{
	
	private static final long serialVersionUID = 1L;

	private int id;
	private String email;
	private String password;
	private String roles;
	private boolean enabled;

	public SecurityUserDTO(int id, String email, String password, String roles, boolean enabled) {
		
		this.id = id;
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.enabled = enabled;
			
		}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		SimpleGrantedAuthority role1 = new SimpleGrantedAuthority(roles);
		authorities.add(role1);
		
		return authorities;
	}
	
	public int getId() {
		return id;
	}

	public String getEmail() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {

		if (enabled == false) {
			return false;
		}
		return true;
	}

}
