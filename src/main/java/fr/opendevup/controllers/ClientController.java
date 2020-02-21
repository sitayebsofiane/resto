package fr.opendevup.controllers;

import java.util.List;

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
/*
 * le controller pour gerer :
 * 1)l'affichage de la table des clients
 * 2)ajout d'un client lors de son inscription
 * 3)modifiaction des information client par l'admin
 */
@Controller
public class ClientController 
{
	@Autowired
	private ClientRepository clientRepo;
	
	/*
	 * je recupere les parametre page et la taille des elememnt dans la page size,et le mot clé par nom du client
	 *  pour faire la recherche pour ce mot clé pour affiché l'enrigistrement qui correspond 
	 */
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

	 /**
	  * 
	  * @param model pour enrigistrer un object client dans le model
	  * @return vers la page d'ajout client avec l'object client qui vide pour etre remplie et transmis via
	  * le formulaire de la page
	  */
	@RequestMapping(value = "/admin/ajouterClients",method = RequestMethod.GET)
	public String ajoutClient(Model model) {
		model.addAttribute("client",new Client());
		return "admin/ajouterClients";	
	}
	/**
	 * 
	 * @param model pour enrigistré  l'object client apres la modifiacation 
	 * @param idClient recureperation via la method get l'id client a modifier
	 * @return je retourne vers la page modifierClient pour donné la main l'admin de le modifier via le formulaire de la pade
	 */
	@RequestMapping(value = "/admin/modifierClient",method = RequestMethod.GET)
	public String modifierClient(Model model,int idClient) {
		Client p= clientRepo.getOne(idClient);
		model.addAttribute("client",p);
		return "admin/modifierClient";		
	}
	/**
	 * 
	 * @param client je recupere le client qui est envoyé via la methode post par la page modifier ou ajout client
	 * @param erreur je verifie la validité des parametre client 
	 * @return je retourne vers la page commpteExisteDeja ou sinon si tout va bien je l'enristre dans la base
	 */
	@RequestMapping(value = "/admin/enregistrerClient",method = RequestMethod.POST)
	public String enregistrer(@Valid Client client,BindingResult erreur) {
		//verification si le compte existe déja
		List<Client> clients=clientRepo.findAll();
		for(Client cl:clients) { 
			if(cl.getEmail().equals(client.getEmail()))
				return "pages/compteExisteDeja";
		}
		if (erreur.hasErrors()) 
			return "admin/ajouterClients";
		else
			try {
				clientRepo.save(client);
				}catch (Exception e) {
					return "/admin/erreur";
				}
		
		return "admin/confirmationAjoutClient";
	}
	/**
	 * 
	 * @param id je recupere l'id de client a suprimé
	 * @param mc l'eventuel mot clé de la page 
	 * @param page le numero de la page
	 * @param size et la taille le nombre d'elemeent de la page
	 * @return je redirige vers la page d'affichage des client
	 */
	@RequestMapping(value="admin/deleteClients",method=RequestMethod.GET)
	public String delete(int id,String mc,int page,int size) {
		try {
		clientRepo.deleteById(id);
		return "redirect:/admin/clients?page="+page+"&size="+size+"&mc="+mc;
		}catch (Exception e) {
			return "/admin/erreur";
		}
	
	}
	
	}
	

