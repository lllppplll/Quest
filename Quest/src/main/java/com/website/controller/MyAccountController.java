package com.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.website.dto.MyAccountDTO;

@Controller
public class MyAccountController {
	
	@RequestMapping("/my_account")
	public String MyAccount(@ModelAttribute("myAccountDTO") MyAccountDTO myAccountDTO) {	
		return "my_account/my_account";
	}
	
}
