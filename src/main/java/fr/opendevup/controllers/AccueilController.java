package fr.opendevup.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class AccueilController {
	
	@RequestMapping(value="/",method = RequestMethod.GET)
	public String  accueil(Model model,@RequestParam(required = false, defaultValue = "----------") String nom)
	{
		model.addAttribute("nom",nom);
		return "/index";
	}
	

}
