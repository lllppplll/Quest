package com.website.test.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Calendar;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.WebRequest;

import com.website.controller.CreateAccountController;
import com.website.dto.CreateAccountDTO;
import com.website.dto.CreateAccountTokenDTO;
import com.website.service.CreateAccountServiceImpl;


public class CreateAccountControllerTest {

	private MockMvc mockMvc;
	
	private CreateAccountServiceImpl service;
	private CreateAccountController controller;
	private CreateAccountTokenDTO tokenDB;
	private CreateAccountDTO userData;
	private Date expiryDate;
	private BindingResult result;
	private WebRequest request;
	private Model model;
	private String token;

	@BeforeEach
	void setup() {
		
		userData = new CreateAccountDTO();
	    //set date
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, 60 * 24);
		expiryDate = calendar.getTime();
	    tokenDB = new CreateAccountTokenDTO();
	    model = mock(Model.class);
	    result = mock(BindingResult.class);
	    request = mock(WebRequest.class);
	    
		service = mock(CreateAccountServiceImpl.class);
		controller = new CreateAccountController(service);
		
		//set up
		token = "token123";
		userData.setEmail("email@123.com");
	    tokenDB.setToken(token);
		tokenDB.setEmail("email@123.com");
		tokenDB.setExpiryDate(expiryDate);
		
		mockMvc = MockMvcBuilders.standaloneSetup(new CreateAccountController(service)).build();		
	}

	@Test
	void CreateAccountPage() throws Exception {

		// arrange
		// act
		// assert
		mockMvc.perform(get("/create_account"))
		.andExpect(status().isOk())
		.andExpect(view()
				.name("create_account/create_account"));
	}
	
	@Test
	void CreateAccountSaveModelGetsCalled() throws Exception {

		// arrange
		// act
    	when(result.hasErrors()).thenReturn(false);
    	when(request.getContextPath()).thenReturn("local");
		when(service.isValid(result.hasErrors(), userData, request.getContextPath())).thenReturn("create_account/create_account");
		
		// assert
		assertEquals(controller.CreateAccountSave(userData, result, request, model), "create_account/create_account");
		verify(model, times(1)).addAttribute("isEmail", "Email already exists.");
	}
	 
	@Test
	void CreateAccountSaveModeNotCalled() throws Exception {

		// arrange	
		// act
    	when(result.hasErrors()).thenReturn(false);
    	when(request.getContextPath()).thenReturn("local");
		when(service.isValid(result.hasErrors(), userData, request.getContextPath())).thenReturn("create_account/create_account_success");
	
		// assert
		assertEquals(controller.CreateAccountSave(userData, result, request, model), "create_account/create_account_success");
		verify(model, times(0)).addAttribute("isEmail", "Email already exists.");
	}
	
	@Test
	void VerifyModelGetsCalled() throws Exception {

		// arrange		
		// act
		when(service.CreateAccountSuccess(userData, token)).thenReturn("create_account/create_account");

		// assert
		assertEquals(controller.verify(userData, token, model), "create_account/create_account");
		verify(model, times(1)).addAttribute("returnMessage",
						"Verification email expired or does not exist. Please enter details again to resend verification email.");

	}
	
	@Test
	void VerifyModelNotCalled() throws Exception {

		// arrange		
		// act
		when(service.CreateAccountSuccess(userData, token)).thenReturn("create_account/create_account_success");

		// assert
		assertEquals(controller.verify(userData, token, model), "create_account/create_account_success");
		verify(model, times(0)).addAttribute("returnMessage",
						"Verification email expired or does not exist. Please enter details again to resend verification email.");
	}
}
