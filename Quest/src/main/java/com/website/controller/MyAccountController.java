package com.website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.website.dto.MyAccountDTO;
import com.website.security.SecurityUserDTO;
import com.website.service.MyAccountServiceI;

@Controller
public class MyAccountController {
	
	@Autowired
	private MyAccountServiceI service;
	
	@RequestMapping("/my_account")
	public String MyAccount(@ModelAttribute("myAccountDTO") MyAccountDTO myAccountDTO, @AuthenticationPrincipal SecurityUserDTO securityUserDTO, Model model) {

		//get details
		MyAccountDTO userInformation = service.getUserInformation(securityUserDTO.getEmail());
		model.addAttribute("userInformation", userInformation);
		
		return "my_account/my_account";
	}
	
	@RequestMapping("/update_my_account")
	public String updateMy_Account(@ModelAttribute("myAccountDTO") MyAccountDTO myAccountDTO, @AuthenticationPrincipal SecurityUserDTO securityUserDTO) {
		
		//update details
		service.updateUserInformation(myAccountDTO, securityUserDTO.getEmail());
		
		return "redirect:/my_account?update=true";
	}
	
}
