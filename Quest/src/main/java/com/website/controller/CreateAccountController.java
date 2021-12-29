package com.website.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
	public String CreateAccountSave(@Valid @ModelAttribute("createAccountDTO") CreateAccountDTO userData, BindingResult result) {

//      Check input fields requirements are met
		String isValid = serviceI.isValid(result.hasErrors(), userData);
		System.out.println(result);
		System.out.println(result.hasErrors());
		return isValid;
	}
}
