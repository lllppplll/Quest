package com.website.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.website.dao.CreateAccountDAOImpl;
import com.website.dao.connection.DataSource;
import com.website.dao.connection.DatabaseCreate;
import com.website.dto.CreateAccountDTO;
import com.website.service.CreateAccountServiceImpl;

public class CreateAccountServiceImplTest {
	
	@Test
	void isValidNoErrors() {
		
		// arrange
		CreateAccountServiceImpl test = new CreateAccountServiceImpl();
		boolean result = true;
		
		// act
		String output = test.isValid(result);
		
		// assert
		assertEquals(output, "create_account/create_account");
	}
	
	@Test
	void isValidHasErrors() {
		
		// arrange
		CreateAccountServiceImpl test = new CreateAccountServiceImpl();
		boolean result = false;
		
		// act
		String output = test.isValid(result);
		
		// assert
		assertEquals(output, "home");
	}
	
	@Test
	void SaveCreateAccount() throws Exception {

		// arrange - mock
		CreateAccountDAOImpl daoMock = mock(CreateAccountDAOImpl.class);
		CreateAccountServiceImpl test = new CreateAccountServiceImpl(daoMock);
		//user details
		CreateAccountDTO userData = new CreateAccountDTO();
		userData.setEmail("email_user");
		userData.setPassword("password_user");
		String isValid = "home";
	
		// act
		test.SaveCreateAccountDetails(userData, isValid);
		
		// assert
		verify(daoMock, times(1)).SaveCreateAccount(userData);
		
	}
	
	@Test
	void SaveCreateAccountNull() throws Exception {

		// arrange - mock
		CreateAccountDAOImpl daoMock = mock(CreateAccountDAOImpl.class);
		CreateAccountServiceImpl test = new CreateAccountServiceImpl(daoMock);
		//user details
		CreateAccountDTO userData = new CreateAccountDTO();
		userData.setEmail("email_user");
		userData.setPassword("password_user");
		String isValid = "create_account/create_account";
	
		// act
		test.SaveCreateAccountDetails(userData, isValid);
		
		// assert
		verify(daoMock, times(0)).SaveCreateAccount(userData);
		
	}

	@Nested
	class innerTest {
		
		private DatabaseCreate database;
		private CreateAccountServiceImpl dataUser;
		private CreateAccountDTO userData;
		private CreateAccountDAOImpl dao;
		
		@BeforeEach
		void setUp() throws Exception {
			
			database = new DatabaseCreate();
			DataSource 	data = new DataSource();
			JdbcTemplate jdbc = data.jdbcDatasource();
			
			dao = new CreateAccountDAOImpl(jdbc);
			dataUser = new CreateAccountServiceImpl(dao);
			//Set up database
			database.db("src/test/java/com/website/dao/connection/CreateAccount.sql", "");

			userData = new CreateAccountDTO();
			//
		}
		
		@AfterEach
		void tearDown() throws Exception {
			//Call database and delete last Insert
			database.db("src/test/java/com/website/dao/connection/CreateAccount.sql", 
					"src/test/java/com/website/dao/connection/CreateAccountDelete.sql");
			// Close Database Connection
			database.close_connection();
			}
		
		@Test
		void SaveCreateAccount() throws Exception {
	
			// arrange
			userData.setEmail("email_user");
			userData.setPassword("password_user");
			String isValid = "home";
		
			// act
			dataUser.SaveCreateAccountDetails(userData, isValid);
			
			// assert
			CreateAccountDTO dbData = database.getUserDetails();
			assertEquals(userData.getEmail(), dbData.getEmail());
			assertEquals(userData.getPassword(), dbData.getPassword());
			assertEquals(userData.getRoles(), dbData.getRoles());
			assertEquals(userData.getEnabled(), dbData.getEnabled());
			
		}
	}
	
	
//	@Test
//	void InputFieldErrorsHasErrors() {
//		
//		// arrange
//		CreateAccountServiceImpl test = new CreateAccountServiceImpl();
//		boolean result = true;
//		
//		// act
//		boolean output = test.InputFieldErrors(result);
//		
//		// assert
//		assertEquals(output, false);
//	}
//	
//	@Test
//	void InputFieldErrorsNoErrors() {
//		
//		// arrange
//		CreateAccountServiceImpl test = new CreateAccountServiceImpl();
//		boolean result = false;
//		
//		// act
//		boolean output = test.InputFieldErrors(result);
//		
//		// assert
//		assertEquals(output, true);
//	}
//	
//	@Test
//	void FormIsValid() {
//		
//		// arrange
//		CreateAccountDAOImpl daoMock = mock(CreateAccountDAOImpl.class);
//		CreateAccountServiceImpl test = new CreateAccountServiceImpl(daoMock);
//		CreateAccountDTO userData = new CreateAccountDTO();
//		boolean result = true;
//		
//		// act
//		doNothing().when(daoMock);
//		String output = test.Form(result, userData);
//		
//		// assert
//		assertEquals(output, "home");
//	}
//	
//	@Test
//	void FormisNotValid() {
//		
//		// arrange
//		CreateAccountDAOImpl daoMock = mock(CreateAccountDAOImpl.class);
//		CreateAccountServiceImpl test = new CreateAccountServiceImpl(daoMock);
//		CreateAccountDTO userData = new CreateAccountDTO();
//		boolean result = false;
//		
//		// act
//		doNothing().when(daoMock);
//		String output = test.Form(result, userData);
//		
//		// assert
//		assertEquals(output, "create_account/create_account");
//	}
//	
//	@Nested
//	class innerTest {
//		
//		private DatabaseCreate database;
//		private CreateAccountServiceImpl dataUser;
//		private CreateAccountDTO userData;
//		
//		@BeforeEach
//		void setUp() throws Exception {
//			
//			database = new DatabaseCreate();
//			DataSource 	data = new DataSource();
//			JdbcTemplate jdbc = data.jdbcDatasource();
//			
//			CreateAccountDAOImpl dao = new CreateAccountDAOImpl(jdbc);
//			dataUser = new CreateAccountServiceImpl(dao);
//			//Set up database
//			database.db("src/test/java/com/website/dao/connection/CreateAccount.sql", "");
//
//			userData = new CreateAccountDTO();
//			//
//		}
//		
//		@AfterEach
//		void tearDown() throws Exception {
//			//Call database and delete last Insert
//			database.db("src/test/java/com/website/dao/connection/CreateAccount.sql", 
//					"src/test/java/com/website/dao/connection/CreateAccountDelete.sql");
//			// Close Database Connection
//			database.close_connection();
//			}
//		
//	@Test
//	void SaveCreateAccountDAO() throws Exception {
//
//		// arrange
//		userData.setEmail("email");
//		userData.setPassword("password");
//	
//		// act - call repository
//		dataUser.Form(true, userData);
//		
//		// assert - data in database, same as user input
//		CreateAccountDTO dbData = database.getUserDetails();
//		assertEquals(userData.getEmail(), dbData.getEmail());
//		assertEquals(userData.getPassword(), dbData.getPassword());
//		assertEquals(userData.getRole(), dbData.getRole());
//		assertEquals(userData.getEnabled(), dbData.getEnabled());
//		
//	}
//	
//	@Test
//	void isValidNoErrors_e2eTest() throws Exception {
//
//		// arrange
//		userData.setEmail("email user");
//		userData.setPassword("password user");
//	
//		// act - no errors
//		dataUser.isValid(false, userData);
//		
//		// assert - data in database, same as user input
//		CreateAccountDTO dbData = database.getUserDetails();
//		assertEquals(userData.getEmail(), dbData.getEmail());
//		assertEquals(userData.getPassword(), dbData.getPassword());
//		assertEquals(userData.getRole(), dbData.getRole());
//		assertEquals(userData.getEnabled(), dbData.getEnabled());
//	}	
//	}
//	
//	@Test
//	void isValidHasErrors_e2eTest() throws Exception {
//
//		// arrange
//		CreateAccountServiceImpl test = new CreateAccountServiceImpl();
//		CreateAccountDTO userData = new CreateAccountDTO();
//	
//		// act 
//		
//		// assert
//		assertEquals(test.isValid(true, userData), "create_account/create_account");
//	}
//	
//	@Test
//	void isValidHasNoErrors_e2eTest() throws Exception {
//
//		// arrange
//		CreateAccountDAOImpl daoMock = mock(CreateAccountDAOImpl.class);
//		CreateAccountServiceImpl test = new CreateAccountServiceImpl(daoMock);
//		CreateAccountDTO userData = new CreateAccountDTO();
//	
//		// act 
//		doNothing().when(daoMock);
//		// assert
//		assertEquals(test.isValid(false, userData), "home");
//	}

}
