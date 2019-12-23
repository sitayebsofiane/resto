package fr.opendevup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fr.opendevup.dao.ClientRepository;
import fr.opendevup.entities.Client;

@Controller
public class AjoutClientController 
{
	@Autowired
	private ClientRepository clientRepo;
	@RequestMapping(value="admin/ajoutClients")
	public String client(Model model,@RequestParam(name="page",defaultValue = "0")int page,
							@RequestParam(name="size",defaultValue = "4")int size,
							@RequestParam(name="mc",defaultValue = "")String mc)
					{
			
						Page<Client> pageClients= clientRepo.chercher("%"+mc+"%", PageRequest.of(page,size));
						model.addAttribute("listeClient",pageClients.getContent());
						int[] pages= new int[pageClients.getTotalPages()];
						model.addAttribute("pages",pages);
						model.addAttribute("size",size);
						model.addAttribute("pageCourante",page);
						model.addAttribute("mc",mc);
					return "admin/ajoutClients";
			
			}
				
	@RequestMapping(value="admin/deleteClients",method=RequestMethod.GET)
	public String delete(int id,String mc,int page,int size) {
		clientRepo.deleteById(id);
		return "redirect:/admin/ajoutClients?page="+page+"&size="+size+"&mc="+mc;
	
	}
		
	}
	

