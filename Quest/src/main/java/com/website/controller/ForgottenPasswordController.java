package com.website.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import com.website.dto.ForgottenPasswordDTO;
import com.website.dto.ForgottenPasswordNewDTO;
import com.website.dto.ForgottenPasswordTokenDTO;
import com.website.service.ForgottenPasswordServiceI;

@Controller
public class ForgottenPasswordController {
	
	@Autowired
	private ForgottenPasswordServiceI service;
	
	@Autowired
	ForgottenPasswordTokenDTO tokenDB;

	
	@RequestMapping("/forgotten_password")
	public String ForgottenPasswordPage(@ModelAttribute("forgottenPasswordDTO") ForgottenPasswordDTO forgottenPasswordDTO) {
		return "forgotten_password/forgotten_password";
	}
	
	//Save Token In Database
	@RequestMapping("/forgotten_password_submit")
	public String ForgottenPasswordProcess(@ModelAttribute("forgottenPasswordDTO") ForgottenPasswordDTO forgottenPasswordDTO, WebRequest request, Model model) {
		
		//check if email exists
		int isEmail = service.checkEmail(forgottenPasswordDTO.getEmail());
		//if email does not exist
		if(isEmail == 0) {
			model.addAttribute("emailError", "Email not found");
			return "forgotten_password/forgotten_password";
		}
		
		if(isEmail == 1) {
		//send email
	    service.sendEmail(forgottenPasswordDTO.getEmail(), request.getContextPath());	
		}
		
	    return "forgotten_password/forgotten_password_reset";
	}
	
	//change
	
	//Save Token In Database
	@RequestMapping("/reset")
	public String ForgottenPasswordReset(@ModelAttribute("forgottenPasswordNewDTO") ForgottenPasswordNewDTO forgottenPasswordNewDTO, 
			@RequestParam("token") String token, Model model) {
		
		String isSuccess = service.checkToken(token, model);
		return isSuccess;

	}
	
	@RequestMapping("/reset-password")
	public String savePassword(@Valid @ModelAttribute("forgottenPasswordNewDTO") ForgottenPasswordNewDTO forgottenPasswordNewDTO, BindingResult result, Model model) {

		//check if passwords match, save password
		String isSuccess = service.checkNewPassword(forgottenPasswordNewDTO.getEmail(), forgottenPasswordNewDTO.getNewPassword(), forgottenPasswordNewDTO.getConfirmPassword(), result.hasErrors(), model);
		return isSuccess;
	}
}
