package fr.opendevup.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProduitController {
	@RequestMapping(value="pages/produits",method = RequestMethod.GET)
	public String  menu()
	{
		
		return "pages/produits";
	}
	
}
