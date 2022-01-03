package com.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.website.dto.SignInDTO;

@Controller
public class SignInController {
	
	@RequestMapping("/sign_in")
	public String SignInPage(@ModelAttribute("signInDTO") SignInDTO signInDTO) {
		return "sign_in/sign_in";
	}
	
}
