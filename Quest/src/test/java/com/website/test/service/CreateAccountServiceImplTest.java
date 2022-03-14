package com.website.test.service;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.website.dao.CreateAccountDAOImpl;
import com.website.dto.CreateAccountDTO;
import com.website.dto.CreateAccountTokenDTO;
import com.website.email.CreateAccountVerifyEmail;
import com.website.service.CreateAccountServiceImpl;

public class CreateAccountServiceImplTest {
	
	private CreateAccountDAOImpl dao;
	private CreateAccountServiceImpl service;
	private CreateAccountDTO userData;
	private CreateAccountVerifyEmail verify;
	private CreateAccountTokenDTO tokenDB;
//	private String noErrors = "create_account/create_account_verify";
//	private String hasErrors = "create_account/create_account";
//	
//	private String createSQL = "src/test/java/com/website/dao/connection/CreateAccount.sql";
//	private String injectSQL = "";
//	private String deleteSQL = "src/test/java/com/website/dao/connection/CreateAccountDelete.sql";
	
	private PasswordEncoder bcrypt;
//	private String bCrypt;
	
	
	
	@BeforeEach
	void setUp() throws Exception {
		//Password Encoder
		bcrypt = new BCryptPasswordEncoder();
		//send email
	    verify = new CreateAccountVerifyEmail();
		//Mock DAO
		dao = mock(CreateAccountDAOImpl.class);
		verify = mock(CreateAccountVerifyEmail.class);
		//Call service methods
		service = new CreateAccountServiceImpl(dao, bcrypt, verify);
		//token data
		tokenDB = new CreateAccountTokenDTO();
		//User Data
		userData = new CreateAccountDTO();
		
	}
	
	@Test
	void isValidHasErrors() {
		
		// arrange - set values
		boolean result = true;
		String appURL = "";
		
		userData.setEmail("email@.com");	
		userData.setPassword("password");
		
		// act
		String output = service.isValid(result, userData, appURL);
		
		// assert
		assertEquals(output, "create_account/create_account");
	}
	
	@Test
	void isValidNoErrorsEmailExists() {
		
		// arrange - set values
		boolean result = false;
		String appURL = "";
		userData.setEmail("email@.com");	
		userData.setPassword("password");	
		//email exist
		when(dao.isEmail(userData.getEmail())).thenReturn(userData);
		
		// act
		String output = service.isValid(result, userData, appURL);
		
		// assert
		assertEquals(output, "create_account/create_account?isEmail=true");
	}
	
	@Test
	void isValidNoErrorsEmailNotExists() {
		
		// arrange - set values
		boolean result = false;
		String appURL = "";
		
        //set values
		userData.setEmail("email@.com");	
		userData.setPassword("password");	
		//email does not exist
		when(dao.isEmail(userData.getEmail())).thenReturn(null);
		
		// act
		String output = service.isValid(result, userData, appURL);

		
		// assert
		//assertEquals(output, hasErrors);
		verify(verify, times(1)).SendEmail(userData.getEmail(), userData.getPassword(), appURL);
		assertEquals(output, "create_account/create_account_verify");
	}
	
	@Test
	void checkPasswordIsEncoded() throws Exception {

		// arrange
		userData.setPassword("password");
	
		// act
		service.PasswordEncoding(userData);
		// assert
		assertNotEquals("password", userData.getPassword());
	}
	
	@Test
	void checkPasswordAfterEncodedThroughMethodIsSameAsEncodedPasswordThroughBCrypt() throws Exception {

		// arrange
		userData.setPassword("password");
	
		// act
		service.PasswordEncoding(userData);
		String password = userData.getPassword();
		
		// assert
		assertEquals(bcrypt.matches("password", password), true);
	}
	
	
	
	@Test
	void CheckTokenTokenNotNullNotExpiryDateSuccess() throws Exception {

		// arrange
		tokenDB.setToken("abc12345");
		// set Date
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, 60 * 24);
		Date expiryDate = calendar.getTime();
		tokenDB.setExpiryDate(expiryDate);
	
		// act
		
		// assert
		assertEquals(service.CheckToken(tokenDB), "create_account/create_account_success");
	}
	
	@Test
	void CheckTokenExpiryDate() throws Exception {

		// arrange
		tokenDB.setToken("abc12345");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		//date expired
		Date date = formatter.parse("2022-03-10");
		tokenDB.setExpiryDate(date);
	
		// act
		
		// assert
		assertEquals(service.CheckToken(tokenDB), "create_account/create_account");
	}
	
	@Test
	void CheckTokenExpiryDateTokenNull() throws Exception {

		// arrange
		tokenDB.setToken(null);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		//date expired
		Date date = formatter.parse("2022-03-10");
		tokenDB.setExpiryDate(date);
	
		// act
		
		// assert
		assertEquals(service.CheckToken(tokenDB), "create_account/create_account");
	}
	
	@Test
	public void SetEnabled() throws Exception {

		// arrange
		tokenDB.setToken("abc12345");
		tokenDB.setEmail("email@123.com");
	
		// act
		service.SetEnabled(userData, "create_account/create_account_success", tokenDB);
		
		// assert
		assertEquals(userData.getEnabled(), true);
		assertEquals(tokenDB.getEmail(), "email@123.com");
		verify(dao, times(1)).enableAccount("email@123.com", userData.getEnabled());
	}
	
	@Test
	public void CreateAccountSuccessTokenExpiryDatePass() throws Exception {

		// arrange
		String token = "abc12345";
		tokenDB.setToken("abc12345");
		tokenDB.setEmail("email@123.com");
		// set date - date not expired
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, 60 * 24);
		Date expiryDate = calendar.getTime();
		tokenDB.setExpiryDate(expiryDate);
		
		when(dao.getToken(token)).thenReturn(tokenDB);
	
		// act
		
		// assert
		assertEquals(service.CreateAccountSuccess(userData, tokenDB.getToken()), "create_account/create_account_success");
	}
	
	@Test
	public void CreateAccountSuccessNotTokenNotExpiryDate() throws Exception {

		// arrange
		String token = null;
		tokenDB.setToken(null);
		tokenDB.setEmail("email@123.com");
		//set date - date expired
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date date = formatter.parse("2022-03-10");
		tokenDB.setExpiryDate(date);
		
		when(dao.getToken(token)).thenReturn(tokenDB);
	
		// act
		
		// assert
		assertEquals(service.CreateAccountSuccess(userData, tokenDB.getToken()), "create_account/create_account");
	}
	
	@Test
	public void CreateAccountSuccessSetEnabled() throws Exception {

		// arrange
		String token = "abc12345";
		String isSuccess = "create_account/create_account_success";
		tokenDB.setToken("abc12345");
		tokenDB.setEmail("email@123.com");
		// set date - date not expired
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, 60 * 24);
		Date expiryDate = calendar.getTime();
		tokenDB.setExpiryDate(expiryDate);
		userData.setEmail("email@123.com");
		
		
		when(dao.getToken(token)).thenReturn(tokenDB);
	    service.SetEnabled(userData, isSuccess, tokenDB);
	
		// act
			
		// assert
		assertEquals(userData.getEnabled(), true);
		assertEquals(tokenDB.getEmail(), "email@123.com");
		verify(dao, times(1)).enableAccount("email@123.com", userData.getEnabled());
	}
	
	
	

	//	@Test
//	void SaveCreateAccountCalledWhenNoErrors() throws Exception {
//
//		// arrange - mock
//		userData.setEmail("email_user");
//		userData.setPassword("password_user");
//		
//	
//		// act
//		serviceDaoMock.SaveCreateAccountDetails(userData, null, noErrors);
//		
//		// assert
//		verify(daoMock, times(1)).SaveCreateAccount(userData);
//		
//	}
//	
//	@Test
//	void NoSaveCreateAccountCalledWhenHasErrors() throws Exception {
//
//		// arrange - mock
//		userData.setEmail("email_user");
//		userData.setPassword("password_user");
//	
//		// act
//		serviceDaoMock.SaveCreateAccountDetails(userData, null, hasErrors);
//		
//		// assert
//		verify(daoMock, times(0)).SaveCreateAccount(userData);
//		
//	}
//	

	
	

//	@Nested
//	class innerTest {
//		
//		private DatabaseCreate database;
//		private CreateAccountServiceImpl dataUser;
//		private CreateAccountDTO userData;
//		private CreateAccountDAOImpl dao;
//		
//		@BeforeEach
//		void setUp() throws Exception {
//			
//			database = new DatabaseCreate();
//			DataSource 	data = new DataSource();
//			JdbcTemplate jdbc = data.jdbcDatasource();
//			
//			dao = new CreateAccountDAOImpl(jdbc);
//			dataUser = new CreateAccountServiceImpl(dao, bcrypt);
//			//Set up database
//			database.db(createSQL, injectSQL);
//
//			userData = new CreateAccountDTO();
//			//
//		}
//		
//		@AfterEach
//		void tearDown() throws Exception {
//			//Call database and delete last Insert
//			database.db(createSQL, deleteSQL);
//			// Close Database Connection
//			database.close_connection();
//			}
//		
//		@Test
//		void SaveCreateAccount() throws Exception {
//	
//			// arrange
//			userData.setEmail("email_user");
//			userData.setPassword("password_user");
//		
//			// act
//			dataUser.SaveCreateAccountDetails(userData, null, noErrors);
//			
//			// assert
//			CreateAccountDTO dbData = database.getUserDetails();
//			assertEquals(userData.getEmail(), dbData.getEmail());
//			assertEquals(userData.getPassword(), dbData.getPassword());
//			assertEquals(userData.getRoles(), dbData.getRoles());
//			assertEquals(userData.getEnabled(), dbData.getEnabled());
//			
//		}
//	}
}

//package com.website.test.service;
//
//import static org.junit.Assert.assertNotEquals;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import com.website.dao.CreateAccountDAOImpl;
//import com.website.dao.connection.DataSource;
//import com.website.dao.connection.DatabaseCreate;
//import com.website.dto.CreateAccountDTO;
//import com.website.service.CreateAccountServiceImpl;
//
//public class CreateAccountServiceImplTest {
//	
//	private CreateAccountDAOImpl daoMock;
//	private CreateAccountServiceImpl serviceDaoMock;
//	private CreateAccountServiceImpl service;
//	private CreateAccountDTO userData;
//	private String noErrors = "create_account/create_account_verify";
//	private String hasErrors = "create_account/create_account";
//	
//	private String createSQL = "src/test/java/com/website/dao/connection/CreateAccount.sql";
//	private String injectSQL = "";
//	private String deleteSQL = "src/test/java/com/website/dao/connection/CreateAccountDelete.sql";
//	
//	private PasswordEncoder bcrypt;
////	private String bCrypt;
//	
//	@BeforeEach
//	void setUp() throws Exception {
//		//Password Encoder
//		bcrypt = new BCryptPasswordEncoder();
//		//Mock DAO
//		daoMock = mock(CreateAccountDAOImpl.class);
//		serviceDaoMock = new CreateAccountServiceImpl(daoMock, bcrypt);
//		//Call service methods
//		service = new CreateAccountServiceImpl();
//		//User Data
//		userData = new CreateAccountDTO();
//		
//	}
//	
//	@Test
//	void isValidNoErrors() {
//		
//		// arrange
//		boolean result = true;
//		
//		// act
//		String output = service.isValid(result);
//		
//		// assert
//		assertEquals(output, hasErrors);
//	}
//	
//	@Test
//	void isValidHasErrors() {
//		
//		// arrange	
//		boolean result = false;
//		
//		// act
//		String output = service.isValid(result);
//		
//		// assert
//		assertEquals(output, noErrors);
//	}
//	
//	@Test
//	void SaveCreateAccountCalledWhenNoErrors() throws Exception {
//
//		// arrange - mock
//		userData.setEmail("email_user");
//		userData.setPassword("password_user");
//		
//	
//		// act
//		serviceDaoMock.SaveCreateAccountDetails(userData, null, noErrors);
//		
//		// assert
//		verify(daoMock, times(1)).SaveCreateAccount(userData);
//		
//	}
//	
//	@Test
//	void NoSaveCreateAccountCalledWhenHasErrors() throws Exception {
//
//		// arrange - mock
//		userData.setEmail("email_user");
//		userData.setPassword("password_user");
//	
//		// act
//		serviceDaoMock.SaveCreateAccountDetails(userData, null, hasErrors);
//		
//		// assert
//		verify(daoMock, times(0)).SaveCreateAccount(userData);
//		
//	}
//	
//	@Test
//	void checkPasswordIsEncoded() throws Exception {
//
//		// arrange
//		//userData.setEmail("email_user");
//		userData.setPassword("password_user");
//	
//		// act
//		serviceDaoMock.PasswordEncoding(userData);
//		// assert
//		assertNotEquals("password_user", userData.getPassword());
//	}
//	
//	@Test
//	void checkPasswordAfterEncodedThroughMethodIsSameAsEncodedPasswordThroughBCrypt() throws Exception {
//
//		// arrange
//		userData.setPassword("password_user");
//	
//		// act
//		serviceDaoMock.PasswordEncoding(userData);
//		String password = userData.getPassword();
//		
//		// assert
//		assertEquals(bcrypt.matches("password_user", password), true);
//	}
//	
//	
//
//	@Nested
//	class innerTest {
//		
//		private DatabaseCreate database;
//		private CreateAccountServiceImpl dataUser;
//		private CreateAccountDTO userData;
//		private CreateAccountDAOImpl dao;
//		
//		@BeforeEach
//		void setUp() throws Exception {
//			
//			database = new DatabaseCreate();
//			DataSource 	data = new DataSource();
//			JdbcTemplate jdbc = data.jdbcDatasource();
//			
//			dao = new CreateAccountDAOImpl(jdbc);
//			dataUser = new CreateAccountServiceImpl(dao, bcrypt);
//			//Set up database
//			database.db(createSQL, injectSQL);
//
//			userData = new CreateAccountDTO();
//			//
//		}
//		
//		@AfterEach
//		void tearDown() throws Exception {
//			//Call database and delete last Insert
//			database.db(createSQL, deleteSQL);
//			// Close Database Connection
//			database.close_connection();
//			}
//		
//		@Test
//		void SaveCreateAccount() throws Exception {
//	
//			// arrange
//			userData.setEmail("email_user");
//			userData.setPassword("password_user");
//		
//			// act
//			dataUser.SaveCreateAccountDetails(userData, null, noErrors);
//			
//			// assert
//			CreateAccountDTO dbData = database.getUserDetails();
//			assertEquals(userData.getEmail(), dbData.getEmail());
//			assertEquals(userData.getPassword(), dbData.getPassword());
//			assertEquals(userData.getRoles(), dbData.getRoles());
//			assertEquals(userData.getEnabled(), dbData.getEnabled());
//			
//		}
//	}
//}