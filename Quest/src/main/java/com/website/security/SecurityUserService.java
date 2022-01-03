package com.website.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.website.dto.CreateAccountDTO;

@Service
public class SecurityUserService implements UserDetailsService{
		
		@Autowired
		private SecurityUserDAO securityUserDAO;
		
		//Gets data from database and place in an object
		@Override
		public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

			CreateAccountDTO user = securityUserDAO.getUserByEmail(email);
					
			SecurityUserDTO securityUserObject = new SecurityUserDTO(user.getId(), user.getEmail(), user.getPassword(), user.getRoles(), user.getEnabled());
			
			return securityUserObject;
		}
	}
