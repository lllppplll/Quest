//package com.website.configuration;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import com.website.service.StudentUserDetailsServiceImpl;
//
////import com.website.service.StudentUserDetailsServiceImpl;
//
////(debug = true)
//
//
//@EnableWebSecurity
//public class MySecurityConfiguration extends WebSecurityConfigurerAdapter {
//	//sets up filter chain
//	
//	@Autowired
//	private PasswordEncoder bcryptPasswordEncoder;
//	
////	@Autowired
////	private DataSource datasource;
////	@Autowired
////	Student student;
//	
//	@Autowired
//	private StudentUserDetailsServiceImpl userDetailsServiceImpl;
//	
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//		
//		
////		auth
////		.inMemoryAuthentication()
////		.withUser("ayur")
////		.password("$2a$10$YYACTohWAc8hQRS4K1uLEuHPghvV.IRTmqOmbB5RhIHvUYrh5fV3e")
////		.roles("admin");
////		
////		System.out.println("password encoded = " + bcryptPasswordEncoder.encode("a123"));
//		
//		
//		
////		  auth.jdbcAuthentication().dataSource(datasource)
////	  //.dataSource(datasource)
////	  .usersByUsernameQuery("select email, password, role from SignInTest where email = ? ")
////	  .authoritiesByUsernameQuery("select email, roles from SignInTest where email = ? ")
////	  .passwordEncoder(bcryptPasswordEncoder);
//		
//		
//		  auth.userDetailsService(userDetailsServiceImpl)
////		  .dataSource(datasource)
////		  .usersByUsernameQuery("select email, password, enabled from customers where email = ? ")
////		  .authoritiesByUsernameQuery("select email, roles from customers where email = ? ")
//		  .passwordEncoder(bcryptPasswordEncoder);
//
//	}
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception{
//		
//		//end points - antMathches - goes to login page
//		http
//		.authorizeRequests()
////		.anyRequest()
//		.antMatchers("/my_account").authenticated()
//		
//		.and().formLogin().loginPage("/sign_in").loginProcessingUrl("/process_login").permitAll()
//		.and().httpBasic()
//		.and().logout().logoutSuccessUrl("/").permitAll();	
//		
//	}
//	
//}
