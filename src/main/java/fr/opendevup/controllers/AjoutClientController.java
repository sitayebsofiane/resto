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
public class AjoutClientController 
{
	@Autowired
	private ClientRepository clientrepo;
	@RequestMapping(value="admin/ajoutClients")
	public String client(Model model,@RequestParam(name="login", defaultValue="")String login,
			@RequestParam(name="password", defaultValue="")String password,@RequestParam(name="page",defaultValue = "0")int page,
							@RequestParam(name="size",defaultValue = "10")int size,
							@RequestParam(name="mc",defaultValue = "")String mc)
					{
			if(login.equals("assia") && password.equals("as122014")) {	
						Page<Client> pageClients= clientrepo.chercher("%"+mc+"%", PageRequest.of(page,size));
						model.addAttribute("listeClient",pageClients.getContent());
						int[] pages= new int[pageClients.getTotalPages()];
						model.addAttribute("pages",pages);
						model.addAttribute("size",size);
						model.addAttribute("pageCourante",page);
						model.addAttribute("mc",mc);
						return "admin/ajoutClients";
			}else {
				return "redirect:../adminer";
			}
				}
	@RequestMapping(value="/delete")
	public String delete() {
		return  "redirect:/";
		
	}
	
}
