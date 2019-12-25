package fr.opendevup.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AccueilControllerProduits {
	@RequestMapping(value = "pages/produits",method = RequestMethod.GET)
	public String AccueilProduits() {
		
		return "pages/produits";
	
	}
	@RequestMapping(value = "pages/menus",method = RequestMethod.GET)
	public String AccueilMenus() {
		
		return "pages/menus";
	
	}
	
}
