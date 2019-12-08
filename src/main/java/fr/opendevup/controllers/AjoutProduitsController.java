package fr.opendevup.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AjoutProduitsController {
		 
	@RequestMapping(value = "admin/adminer",method = RequestMethod.GET)
	public String ajoutProduits(Model model,@RequestParam(name="login", defaultValue="")String login,
			@RequestParam(name="password", defaultValue="")String password) {
			if(login.equals("momo") && password.equals("momo")) {
				
				return "admin/ajoutProduits";
			}else {
				return "/admin/adminer";
			}
		
	}
	
}
