package com.website.configuration;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.website" })
public class ViewResolverConfiguration implements WebMvcConfigurer {

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
	JdbcTemplate jdbcTemplate() {	
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());	
		return jdbcTemplate;
	}
	
	//Database Connection
	@Bean
	DataSource dataSource() {
		//In development
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl("jdbc:sqlserver://DESKTOP-D199DEA:1433;databaseName=Quest;integratedSecurity=true;");
		dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		
		//In production
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setUrl("jdbc:sqlserver://adventure.ck1mwepqmauh.eu-west-2.rds.amazonaws.com;databaseName=Adventure;");
//		dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//		dataSource.setUsername("admin");
//		dataSource.setPassword("adventuredb");
	
		return dataSource;
	}

}
