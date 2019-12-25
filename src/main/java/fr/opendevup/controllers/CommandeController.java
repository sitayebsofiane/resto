package fr.opendevup.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CommandeController {
	
	
	@RequestMapping(value="/admin/commandes",method = RequestMethod.GET)
	public String  commande()
	{
		
		return "/admin/commandes";
	}
	
}

	
	
	
