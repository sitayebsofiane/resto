package fr.opendevup.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class MenuController {
	
	@RequestMapping(value="pages/menu",method = RequestMethod.GET)
	public String  menu()
	{
		
		return "pages/menu";
	}
	

}

