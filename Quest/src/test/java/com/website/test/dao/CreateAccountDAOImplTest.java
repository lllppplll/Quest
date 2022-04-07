package com.website.test.dao;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.website.dao.CreateAccountDAOImpl;
import com.website.dao.connection.DataSource;
import com.website.dao.connection.DatabaseCreate;
import com.website.dto.CreateAccountDTO;
import com.website.dto.CreateAccountTokenDTO;
import com.website.dto.MyAccountDTO;


class CreateAccountDAOImplTest {
	
	private DatabaseCreate database;
	private DataSource data; 
	private JdbcTemplate jdbc;
	private CreateAccountDAOImpl dao;
	private CreateAccountDTO createAccount;
	private CreateAccountTokenDTO createAccountTokenDTO;
	//get database
	private CreateAccountDTO dbUsers;
	private MyAccountDTO dbUsersDetails;
	private CreateAccountTokenDTO dbVerificationTokens;
	
	
	@BeforeEach
	void setUpWithTables() throws Exception {
		database = new DatabaseCreate();
		data = new DataSource();
		//datasource for in-memory database - com.website.test.connection
		jdbc = data.jdbcDatasource();
		
		createAccount = new CreateAccountDTO();
		createAccountTokenDTO = new CreateAccountTokenDTO();
		
		//create account instance using hyperSQL jdbc
		dao = new CreateAccountDAOImpl(jdbc);
		
	}	
	
	@AfterEach
	public void dropTables() throws Exception {
		//drop database
		database.db("src/test/java/com/website/dao/tables/Users.sql", "src/test/java/com/website/dao/tables/DropUsers.sql");
		database.db("src/test/java/com/website/dao/tables/UsersDetails.sql", "src/test/java/com/website/dao/tables/DropUsersDetails.sql");
		database.db("src/test/java/com/website/dao/tables/VerificationTokens.sql", "src/test/java/com/website/dao/tables/DropVerificationTokens.sql");
		
		// Close Database Connection
		database.close_connection();
	}
	
	@Test
	void createAccountWithToken() throws Exception {

		// Arrange - Create in-memory database
		database.db("src/test/java/com/website/dao/tables/Users.sql", "");
		database.db("src/test/java/com/website/dao/tables/UsersDetails.sql", "");
		database.db("src/test/java/com/website/dao/tables/VerificationTokens.sql", "");
		
		// Act
		//set email and passwords
		createAccount.setEmail("email@.com");
		createAccount.setPassword("password");
		//set date	
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date date = formatter.parse("2022-03-09");
		createAccountTokenDTO.setExpiryDate(date);
		//arguments into method
		dao.createAccountWithToken(createAccount.getEmail(), createAccount.getPassword(), "abc123", createAccountTokenDTO.getExpiryDate());
		//get database
		dbUsers = database.getUsers();
		dbUsersDetails = database.getUsersDetails();
		dbVerificationTokens = database.getVerificationTokens();
		
		
		// Assert - Check Update - compare set values to database values
		//Users database
		assertEquals("email@.com", dbUsers.getEmail());
		assertEquals("password", dbUsers.getPassword());
		assertEquals("ROLES_USER", dbUsers.getRoles());
		assertEquals(false, dbUsers.getEnabled());
		
		//UsersDetails database
		assertEquals("email@.com", dbUsersDetails.getEmail());
		
		//VerificationTokens database
		assertEquals("email@.com", dbVerificationTokens.getEmail());
		assertEquals("abc123", dbVerificationTokens.getToken());
		assertEquals(date, dbVerificationTokens.getExpiryDate());
		
	}
	
	@Test
	void isEmail() throws Exception {

		// Arrange
		database.db("src/test/java/com/website/dao/tables/Users.sql", "src/test/java/com/website/dao/tables/InjectUsers.sql");
		
		// Act
		
		// Assert
		//get database
		dbUsers = database.getUsers();

		//email same as in database
		assertEquals("email@.com", dbUsers.getEmail());
		//email exists
		assertEquals(dao.isEmail("email@.com"), "email@.com");
		//email does not exist
		assertEquals(dao.isEmail("emailNot@.com"), null);
	
	}
	
	@Test
	void enableAccount() throws Exception {

		// Arrange 
		database.db("src/test/java/com/website/dao/tables/Users.sql", "src/test/java/com/website/dao/tables/InjectUsers.sql");
	
		// Act
		dao.enableAccount("email@.com", true);
		//get database
		dbUsers = database.getUsers();
		
		// Assert - is enabled set to 1
		assertEquals(true, dbUsers.getEnabled());
		
	}
	
	@Test
	void getToken() throws Exception {

		// Arrange 
		database.db("src/test/java/com/website/dao/tables/VerificationTokens.sql", "src/test/java/com/website/dao/tables/InjectVerificationTokens.sql");
	
		// Act
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date date = formatter.parse("2022-03-09");
		//get database
		dbVerificationTokens = database.getVerificationTokens();
		
		// Assert
		assertEquals(dao.getToken("abc12345").getEmail(), "email@12345.com");
		assertEquals(dao.getToken("abc12345").getToken(), "abc12345");
		assertEquals(dao.getToken("abc12345").getExpiryDate(), date);
		
	}
}
