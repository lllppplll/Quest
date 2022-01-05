package com.website.controller;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	@Autowired
	private PasswordEncoder bcrypt;
	
	@RequestMapping("/forgotten_password")
	public String ForgottenPasswordPage(@ModelAttribute("forgottenPasswordDTO") ForgottenPasswordDTO forgottenPasswordDTO) {
		return "forgotten_password/forgotten_password";
	}
	
	//Save Token In Database
	@RequestMapping("/forgotten_password_submit")
	public String ForgottenPasswordProcess(ForgottenPasswordDTO forgottenPasswordDTO, WebRequest request) {
		
		service.sendEmail(forgottenPasswordDTO.getEmail(), request.getContextPath());
		
		return "forgotten_password/forgotten_password_reset";
	}
	
	//Save Token In Database
	@RequestMapping("/reset")
	public String ForgottenPasswordReset(@ModelAttribute("forgottenPasswordNewDTO") ForgottenPasswordNewDTO forgottenPasswordNewDTO, 
			@RequestParam("token") String token, Model model) {
		
		//service.sendEmail(forgottenPasswordDTO.getEmail(), request.getContextPath());
		
		//Get Token From Database
		tokenDB = service.getToken(token);
		
		//Check If Token Is Present
		if (tokenDB == null) {  
			return "forgotten_password/forgotten_password_denied";
		}

		//Get Email From Database
		String email = tokenDB.getEmail();
		model.addAttribute("email", email);
		
		//Get Date
		Calendar calendar = Calendar.getInstance();

		//Check If Email Is Not Expired
		if ((tokenDB.getExpiryDate().getTime() - calendar.getTime().getTime()) <= 0) {
			return "forgotten_password/forgotten_password_expired";
		}

		//Set Enabled In DTO
		//forgottenPasswordDTO.setEnabled(true);
		//Save Enabled In Database
		//service.enableAccount(email, createAccountDTO.getEnabled());
		
		return "forgotten_password/forgotten_password_new";
	}
	
	@RequestMapping("/saveNewPassword")
	public String savePassword(ForgottenPasswordNewDTO forgottenPasswordNewDTO) {
		

//	    //passwords match
		boolean matches = forgottenPasswordNewDTO.getNewPassword().equals(forgottenPasswordNewDTO.getConfirmPassword());
		//boolean matches = bcryptPasswordEncoded.matches(resetPasswordDTO.getNewPassword(), resetPasswordDTO.getConfirmPassword());
	

		 if(matches) {
			//encode password
			 String passwordEncoded = bcrypt.encode(forgottenPasswordNewDTO.getNewPassword());
			//save new password in database
			service.saveNewPassword(forgottenPasswordNewDTO.getEmail(), passwordEncoded);
			
		}
		 
		 //if not matches
//		 if(!matches) {
////			 model.addAttribute("isNotMatch", "true");
//			 return "redirect:/resetPassword?token=" + resetPasswordDTO.getToken() + "&isnotmatch=1";
//		 }
		 
		return "forgotten_password/forgotten_password_success";

	}
	
	
	
	
	
	
}
