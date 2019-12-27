package fr.opendevup.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class AccueilController {
	
	@RequestMapping(value="/")
	public String  accueil()
	{
		
		return "/index";
	}
	@RequestMapping(value="/login")
	public String login() {
		
		return "/admin/login";
	
	}
	
	@RequestMapping(value="/403")
	public String aceesRefuser() {
		
		return "/admin/403";
	
	}
}
