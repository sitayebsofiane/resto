package fr.opendevup.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.opendevup.dao.ClientRepository;
import fr.opendevup.entities.Client;

@Controller
public class ClientController {
	@Autowired
	private ClientRepository cleintrepo;
	@RequestMapping(value="/client")
	public String client(@RequestParam(required = false,defaultValue = "salut") String name,ModelMap model) {
		
		model.put("name",name);
		List<Client> clients= cleintrepo.findAll();
		model.addAttribute("listeClient",clients);
		return "pages/client";
	}
}
