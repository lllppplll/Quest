package com.website.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.website.dto.CreateAccountDTO;
import com.website.service.CreateAccountServiceI;

@Controller
public class CreateAccountController {
	
	@Autowired	
	private CreateAccountServiceI createAccountServiceI;
	
	//Constructor - Empty
	public CreateAccountController() {
	}

	//Constructor
	public CreateAccountController(CreateAccountServiceI createAccountServiceI) {
		this.createAccountServiceI = createAccountServiceI;
	}	

//  CreateAccountDTO gets model data and puts it in "createAccountDTO".
//  This goes to create_account page where the data can be used in the <form>.
	@RequestMapping("/create_account")
	public String CreateAccounPage(@ModelAttribute("createAccountDTO") CreateAccountDTO createAccountDTO) {
		return "create_account/create_account";
	}


	@RequestMapping("/create_account_submit")
	public String CreateAccountSave(@Valid @ModelAttribute("createAccountDTO") CreateAccountDTO userData, BindingResult result) {

//      Check input fields requirements are met
//		String isValid = createAccountServiceI.isValid(result.hasErrors(), userData);
		String isValid = createAccountServiceI.isValid(result.hasErrors());
//		String isValid = result.hasErrors() ? "home" : "create_account/create_account";
		createAccountServiceI.SaveCreateAccountDetails(userData, isValid);

		return isValid;
	}


}
