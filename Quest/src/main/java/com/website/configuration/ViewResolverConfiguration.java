package com.website.configuration;

import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@PropertySource("classpath:jdcb.properties")
@PropertySource("classpath:email.properties")
@ComponentScan(basePackages = { "com.website" })
public class ViewResolverConfiguration implements WebMvcConfigurer {
	
	//Getting properties file variables
	@Autowired
	private Environment env;

	//Prefix and Suffix of controller text
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;
	}

	//Getting static files
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/URL/**").addResourceLocations("/resources/");
	}
	
	//Template for connecting to database
	@Bean
	public JdbcTemplate jdbcTemplate() {	
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());	
		return jdbcTemplate;
	}
	
	//Database Connection
	@Bean
	public DataSource dataSource() {
		//In development
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(env.getProperty("mssql.datasource.url"));
		dataSource.setDriverClassName(env.getProperty("mssql.datasource.driverClassName"));
	
		return dataSource;
	}
	
    //Transactions in DAO Layer
	@Bean
	public PlatformTransactionManager txManager() {
		return new DataSourceTransactionManager(dataSource());
	}
	
	//Password Encryption - on create account
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//Send Email
	@Bean
	public Session SessionEmail() {
	String host = "smtp.gmail.com";
	final String username = env.getProperty("email.username");
	final String passwordEmail = env.getProperty("email.password");

	Properties props = new Properties();
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.starttls.enable", "true");// it’s optional in Mailtrap
	props.put("mail.smtp.host", host);
	props.put("mail.smtp.port", "587");// use one of the options in the SMTP settings tab in your Mailtrap Inbox

	// Get the Session object.
	Session session = Session.getInstance(props, new javax.mail.Authenticator() {
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(username, passwordEmail);
		}
	});
	return session;
	}
}
