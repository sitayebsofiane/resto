package fr.opendevup.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DispatcherPagesAdminController {
	
	@RequestMapping(value = "dispatcherAdmin",method = RequestMethod.GET)
	public String dispatcherAdmin(Model model,@RequestParam(name="login", defaultValue="")String login,
			@RequestParam(name="password", defaultValue="")String password ) {
			if(login.equals("assia") && password.equals("as122014")) {
				model.addAttribute("login",login);
				model.addAttribute("password",password);
				
				
				return "admin/dispatcherAdmin";
			}else {
				return "adminer";
			}
		
	}
	}
	
