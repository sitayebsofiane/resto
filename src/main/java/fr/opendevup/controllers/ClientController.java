package fr.opendevup.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fr.opendevup.dao.ClientRepository;
import fr.opendevup.entities.Client;

@Controller
public class ClientController 
{
	@Autowired
	private ClientRepository clientRepo;
	@RequestMapping(value="admin/clients")
	public String client(Model model,@RequestParam(name="page",defaultValue = "0")int page,
							@RequestParam(name="size",defaultValue = "10")int size,
							@RequestParam(name="mc",defaultValue = "")String mc)
					{
			
						Page<Client> pageClients= clientRepo.chercher("%"+mc+"%", PageRequest.of(page,size));
						model.addAttribute("listeClient",pageClients.getContent());
						int[] pages= new int[pageClients.getTotalPages()];
						model.addAttribute("pages",pages);
						model.addAttribute("size",size);
						model.addAttribute("pageCourante",page);
						model.addAttribute("mc",mc);
					return "admin/clients";
			
			}
	@RequestMapping(value = "/admin/ajouterClients",method = RequestMethod.GET)
	public String ajoutClient(Model model) {
		model.addAttribute("client",new Client());
		return "admin/ajouterClients";	
	}
	@RequestMapping(value = "/admin/modifierClient",method = RequestMethod.GET)
	public String modifierClient(Model model,int idClient) {
		Client p= clientRepo.getOne(idClient);
		model.addAttribute("client",p);
		return "admin/modifierClient";		
	}
	
	@RequestMapping(value = "/admin/enregistrerClient",method = RequestMethod.POST)
	public String enregistrer(Model model, @Valid Client client,BindingResult erreur) {
		if (erreur.hasErrors()) 
			return "admin/ajouterClients";
		else
		 clientRepo.save(client);
		return "admin/confirmationAjoutClient";
	}
	@RequestMapping(value="admin/deleteClients",method=RequestMethod.GET)
	public String delete(int id,String mc,int page,int size) {
		clientRepo.deleteById(id);
		return "redirect:/admin/clients?page="+page+"&size="+size+"&mc="+mc;
	
	}
	
	}
	

