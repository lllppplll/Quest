package com.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class KeyStage1Controller {
	
	@RequestMapping("/KS1_maths")
	public String KS1() {	
		return "key_stage_1/KS1_maths/KS1_maths";
	}
	
	@RequestMapping("/KS1_maths_print")
	public String KS1MathsPrint() {	
		return "key_stage_1/KS1_maths/KS1_maths_print";
	}
	
	
}
