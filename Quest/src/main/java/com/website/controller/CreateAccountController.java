package com.website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.website.dto.CreateAccountDTO;
import com.website.service.ServiceI;

@Controller
public class CreateAccountController {
	
	@Autowired
	private ServiceI serviceI;

//  CreateAccountDTO gets model data and puts it in "createAccountDTO".
//  This goes to create_account page where the data can be used in the <form>.
	@RequestMapping("/create_account_page")
	public String CreateAccounPage(@ModelAttribute("createAccountDTO") CreateAccountDTO createAccountDTO) {
		return "create_account/create_account";
	}

	@RequestMapping("/create_account_submit")
	public String CreateAccountSave(CreateAccountDTO userData) {

//		Goes to the class ServiceI
		serviceI.SaveCreateAccount(userData);

		return "home";
	}

}
