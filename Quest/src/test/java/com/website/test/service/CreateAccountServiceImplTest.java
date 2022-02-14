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