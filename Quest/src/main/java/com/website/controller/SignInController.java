package com.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.website.dto.SignInDTO;

@Controller
public class SignInController {
	
	@RequestMapping("/sign_in")
	public String SignInPage(@ModelAttribute("signInDTO") SignInDTO signInDTO, @RequestParam(required = false) boolean error, Model model) {
				
		if(error == true) {
			model.addAttribute("error", "Incorrect email and/or password");
		}	
		
		return "sign_in/sign_in";
	}
	
}
