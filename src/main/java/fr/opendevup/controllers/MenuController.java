package fr.opendevup.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class MenuController {
	
	@RequestMapping(value="pages/menu",method = RequestMethod.GET)
	public String  menu()
	{
		
		return "pages/menu";
	}
	

}

