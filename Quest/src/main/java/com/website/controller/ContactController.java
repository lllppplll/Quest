package com.website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.website.dto.ContactDTO;
import com.website.security.SecurityUserDTO;
import com.website.service.ContactServiceI;

@Controller
public class ContactController {

	@Autowired
	private ContactServiceI service;

	@RequestMapping("/contact") 
	public String ContactPage(@ModelAttribute("contactDTO") ContactDTO contactDTO) {
		return "contact/contact";
	}

	@RequestMapping("/process_contact")
	public String SendToContact(@ModelAttribute("contactDTO") ContactDTO contactDTO, @AuthenticationPrincipal SecurityUserDTO securityUserDTO, Model model) {

		// send email
		boolean sent = service.SendEmail(contactDTO.getTo(), securityUserDTO.getEmail(), contactDTO.getSubject(),
				contactDTO.getBody());

//		if (sent == true) {
//			model.addAttribute("email_sent_to", contactDTO.getTo());
//		}
//		if (sent == false) {
//			model.addAttribute("email_not_sent", "'To' field needed");
//		}
		
		model.addAttribute("email_sent", sent);
		
		return "contact/contact";
	}

}
