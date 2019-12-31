package fr.opendevup.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import fr.opendevup.entities.Client;

@Controller
@SessionAttributes({"client"})
public class CommandeController {
	
	
	@RequestMapping(value="/admin/commandes",method = RequestMethod.GET)
	public String  commande()
	{
		
		return "/admin/commandes";
	}
	@RequestMapping(value = "/pages/panier")
	public String consulterPanier(Model model,Client client ) {
		
		return"/pages/panier";
	}
	
}

	
	
	
