package com.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.website.dto.ContactDTO;

@Controller
public class ContactController {
	
	@RequestMapping("/contact")
	public String ContactPage(@ModelAttribute("contactDTO") ContactDTO contactDTO) {	
		return "contact/contact";
	}
	
	@RequestMapping("/process_contact")
	public String SendToContact(@ModelAttribute("contactDTO") ContactDTO contactDTO, Model model) {
		model.addAttribute("email_sent_to", contactDTO.getEmail());
		//send email
		return "contact/contact";
	}
	
}
