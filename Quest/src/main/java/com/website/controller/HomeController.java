package com.website.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.website.security.SecurityUserDTO;

@Controller
public class HomeController {
	

	@RequestMapping("/")
	public String Home(@AuthenticationPrincipal SecurityUserDTO user, Model model) {
		
		if(user != null) {
			model.addAttribute("signIn", "true");
			if(user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {				
				model.addAttribute("admin", "true");				
			}
		}
		return "home";
	}
	
	//@PreAuthorize("hasRole('ROLES_ADMIN')")
	@RequestMapping("/admin")
	public String Admin() {	
		return "admin/admin";
	}
	
	@RequestMapping("/game")
	public String Bridge() {	
		return "bridge";
	}
	
	
}
