package com.website.controller;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import com.website.dto.CreateAccountDTO;
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
	public String CreateAccountSave(@Valid @ModelAttribute("createAccountDTO") CreateAccountDTO userData, BindingResult result, WebRequest request, Model model) {
		
//      Check input fields requirements are met, and email does not exist
		String isValid = service.isValid(result.hasErrors(), userData, request.getContextPath());
		
		if(isValid == "create_account/create_account") {
			//send message
			model.addAttribute("isEmail", "Email already exists.");
		}
		
		return isValid;
	}
	
	//EMAIL CONFIRMATION
		@GetMapping("/verify")
		public String verify(@ModelAttribute("createAccountDTO") CreateAccountDTO createAccountDTO, @RequestParam("token") String token, Model model) {
			
			String isSuccess = service.CreateAccountSuccess(createAccountDTO, token);
			
			if (isSuccess == "create_account/create_account") {
				model.addAttribute("returnMessage",
						"Verification email expired or does not exist. Please enter details again to resend verification email.");
			}

			return isSuccess;
		}


}
