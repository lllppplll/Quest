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
	private PasswordEncoder bcrypt;
	
	
	
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
}