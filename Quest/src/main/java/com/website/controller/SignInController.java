package com.website.controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.website.dto.SignInDTO;

public class SignInController {
	
	@RequestMapping("/sign_in_page")
	public String CreateAccounPage(@ModelAttribute("signInDTO") SignInDTO signInDTO) {
		return "sign_in/sign_in";
	}
	
}
