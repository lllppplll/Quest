package com.website.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	//sets up filter chain
	
	@Autowired
	private PasswordEncoder bcryptPasswordEncoder;
	
	@Autowired
	private SecurityUserService securityUserService;
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{

		  auth.userDetailsService(securityUserService)
		  .passwordEncoder(bcryptPasswordEncoder);

	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		//end points - antMathches - goes to login page
		http
		.authorizeRequests()
	//	.anyRequest()
//		.antMatchers("/my_account").authenticated()
		.and().formLogin().loginPage("/sign_in").loginProcessingUrl("/process_login").permitAll()
		.and().httpBasic()
		.and().logout().logoutUrl("/logout").logoutSuccessUrl("/").permitAll();	
		
	}
	
}
