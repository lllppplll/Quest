package com.website.controller;

import java.util.Calendar;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import com.website.dto.CreateAccountDTO;
import com.website.dto.CreateAccountTokenDTO;
import com.website.service.CreateAccountServiceI;

@Controller
public class CreateAccountController {
	
	@Autowired	
	private CreateAccountServiceI service;
	
	//Constructor - Empty
	public CreateAccountController() {
	}

	//Constructor
	public CreateAccountController(CreateAccountServiceI service) {
		this.service = service;
	}	

//  CreateAccountDTO gets model data and puts it in "createAccountDTO".
//  This goes to create_account page where the data can be used in the <form>.
	@RequestMapping("/create_account")
	public String CreateAccounPage(@ModelAttribute("createAccountDTO") CreateAccountDTO createAccountDTO) {
		return "create_account/create_account";
	}


	@RequestMapping("/create_account_submit")
	public String CreateAccountSave(@Valid @ModelAttribute("createAccountDTO") CreateAccountDTO userData, BindingResult result, WebRequest request) {

//      Check input fields requirements are met
//		String isValid = createAccountServiceI.isValid(result.hasErrors(), userData);
		String isValid = service.isValid(result.hasErrors());
//		String isValid = result.hasErrors() ? "home" : "create_account/create_account";
		service.SaveCreateAccountDetails(userData, request.getContextPath(), isValid);

		return isValid;
	}
	
	//EMAIL CONFIRMATION
		@GetMapping("/verify")
		public String verify(@ModelAttribute("create_account_success") CreateAccountDTO createAccountDTO,
				@RequestParam("token") String token) {
			
			//Get Token From Database
			CreateAccountTokenDTO tokenDB = service.getToken(token);
			
			//Check If Token Is Present
			if (tokenDB == null) {  
				return "create_account/create_account_denied";
			}

			//Get Email From Database
			String email = tokenDB.getEmail();
			
			//Get Date
			Calendar calendar = Calendar.getInstance();

			//Check If Email Is Not Expired
			if ((tokenDB.getExpiryDate().getTime() - calendar.getTime().getTime()) <= 0) {
				return "create_account/create_account_expired";
			}

			//Set Enabled In DTO
			createAccountDTO.setEnabled(true);
			//Save Enabled In Database
			service.enableAccount(email, createAccountDTO.getEnabled());
			
			return "create_account/create_account_success";
		}


}
