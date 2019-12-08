package fr.opendevup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.opendevup.dao.ClientRepository;
import fr.opendevup.entities.Client;

@Controller
public class ClientController 
{
	@Autowired
	private ClientRepository clientrepo;
	@RequestMapping(value="pages/client")
	public String client(Model model,@RequestParam(name="page",defaultValue = "0")int page,
							@RequestParam(name="size",defaultValue = "10")int size,
							@RequestParam(name="mc",defaultValue = "")String mc)
					{
						
						//Page<Client> pageClients= cleintrepo.findAll(PageRequest.of(page,size));
						Page<Client> pageClients= clientrepo.chercher("%"+mc+"%", PageRequest.of(page,size));
						model.addAttribute("listeClient",pageClients.getContent());
						int[] pages= new int[pageClients.getTotalPages()];
						model.addAttribute("pages",pages);
						model.addAttribute("size",size);
						model.addAttribute("pageCourante",page);
						model.addAttribute("mc",mc);
						return "pages/client";
				}
	@RequestMapping(value="/delete")
	public String delete() {
		return  "redirect:/";
		
	}
	
}
