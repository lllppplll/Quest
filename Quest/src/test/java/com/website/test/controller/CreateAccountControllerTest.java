//package com.website.test.controller;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
//
//import java.util.HashMap;
//
//import org.junit.Before;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.validation.BeanPropertyBindingResult;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.FieldError;
//import org.springframework.validation.MapBindingResult;
//import org.springframework.web.context.WebApplicationContext;
//import org.springframework.web.context.request.WebRequest;
//
//import com.website.controller.CreateAccountController;
//import com.website.dao.CreateAccountDAOImpl;
//import com.website.dao.connection.DataSource;
//import com.website.dao.connection.DatabaseCreate;
//import com.website.dto.CreateAccountDTO;
//import com.website.service.CreateAccountServiceImpl;
//
//public class CreateAccountControllerTest {
//
//	private MockMvc mockMvc;
//
//	@BeforeEach
//	void setup() {
//		mockMvc = MockMvcBuilders.standaloneSetup(new CreateAccountController()).build();
//	}
//
//	@Test
//	void CreateAccountPage() throws Exception {
//
//		// arrange
//		// act
//		// assert
//		mockMvc.perform(get("/create_account_page"))
//		.andExpect(status().isOk())
//		.andExpect(view()
//				.name("create_account/create_account"));
//
//	}
//	
//
//	@Nested
//	class innerTest {
//
//		private DatabaseCreate database;
//		private CreateAccountDTO userData;
//		private CreateAccountDAOImpl dao;
//		private CreateAccountServiceImpl service;
//		private CreateAccountController test;
//
//		@BeforeEach
//		void setUp() throws Exception {
//
//			database = new DatabaseCreate();
//			DataSource data = new DataSource();
//			JdbcTemplate jdbc = data.jdbcDatasource();
//
//			dao = new CreateAccountDAOImpl(jdbc);
//			//service = new CreateAccountServiceImpl(dao);
//			test = new CreateAccountController(service);
//			// Set up database
//			database.db("src/test/java/com/website/dao/connection/CreateAccount.sql", "");
//
//			userData = new CreateAccountDTO();
//			//
//		}
//
//		@AfterEach
//		void tearDown() throws Exception {
//			// Call database and delete last Insert
//			database.db("src/test/java/com/website/dao/connection/CreateAccount.sql",
//					"src/test/java/com/website/dao/connection/CreateAccountDelete.sql");
//			// Close Database Connection
//			database.close_connection();
//		}
//
//		@Test
//		void CreateAccountSave_e2eTest() throws Exception {
//
//			// arrange
//			BindingResult result = mock(BindingResult.class);
//			when(result.hasErrors()).thenReturn(true);
//			//WebRequest request = mock(WebRequest.class);
//			//when(request.getContextPath()).thenReturn("");
//			userData.setEmail("email_user");
//			userData.setPassword("password_user");
//
//			// act
//			test.CreateAccountSave(userData, result, null);
//
//			// assert - database
//			CreateAccountDTO dbData = database.getUserDetails();
//			assertEquals(userData.getEmail(), dbData.getEmail());
//			assertEquals(userData.getPassword(), dbData.getPassword());
//			assertEquals(userData.getRoles(), dbData.getRoles());
//			assertEquals(userData.getEnabled(), dbData.getEnabled());
//		}
//		
//		@Test
//		void CreateAccountSaveErrors() throws Exception {
//
//			// arrange
//			BindingResult result = mock(BindingResult.class);
//			when(result.hasErrors()).thenReturn(true);
////			WebRequest request = mock(WebRequest.class);
////			when(request.getContextPath()).thenReturn("");
//
//			// act
//			String valid = test.CreateAccountSave(userData, result, null);
//
//			// assert
//			assertEquals(valid, "home");
//
//		}
//		
//		@Test
//		void CreateAccountSaveNoErrors() throws Exception {
//
//			// arrange
//			BindingResult result = mock(BindingResult.class);
//			when(result.hasErrors()).thenReturn(false);
////			WebRequest request = mock(WebRequest.class);
////			when(request.getContextPath()).thenReturn("");
//
//			// act
//			String valid = test.CreateAccountSave(userData, result, null);
//
//			// assert
//			assertEquals(valid, "create_account/create_account");
//
//		}
//		
//	}
//}
