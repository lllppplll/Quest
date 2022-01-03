package com.website.test.dao;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.website.dao.CreateAccountDAOImpl;
import com.website.dao.connection.DataSource;
import com.website.dao.connection.DatabaseCreate;
import com.website.dto.CreateAccountDTO;


class CreateAccountDAOImplTest {
	
	private DatabaseCreate database;
	private DataSource data;
	private JdbcTemplate jdbc;
	private CreateAccountDAOImpl dao;
	
	
	@BeforeEach
	void setUp() {
		database = new DatabaseCreate();
		data = new DataSource();
		jdbc = data.jdbcDatasource();
	}
	
	@Test
	void saveUserDetails() throws Exception {

		// Arrange - Create Database
		database.db("src/test/java/com/website/dao/connection/CreateAccount.sql", "");
		dao = new CreateAccountDAOImpl(jdbc);
	
		// Act -
		CreateAccountDTO createAccount = new CreateAccountDTO();
		createAccount.setEmail("email user");
		createAccount.setPassword("password user");
		dao.SaveCreateAccount(createAccount);
		
		// Assert - Check Update
		CreateAccountDTO dbData = database.getUserDetails();
		assertEquals(createAccount.getEmail(), dbData.getEmail());
		assertEquals(createAccount.getPassword(), dbData.getPassword());
		assertEquals(createAccount.getRoles(), dbData.getRoles());
		assertEquals(createAccount.getEnabled(), dbData.getEnabled());
		
		// Close Database Connection
		database.close_connection();
	}
}
