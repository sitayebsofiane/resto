package fr.opendevup.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import fr.opendevup.dao.ClientRepository;
import fr.opendevup.dao.MenuRepository;
import fr.opendevup.dao.PanierProduitRepository;
import fr.opendevup.dao.ProduitRepository;
import fr.opendevup.entities.Client;
import fr.opendevup.entities.PanierProduit;
@Controller
@SessionAttributes({"client"})
public class AccueilController {
	@Autowired
	private ClientRepository clientRepo;
	@Autowired
	private ProduitRepository produitRepo;
	@Autowired
	private MenuRepository menuRepo;
	@Autowired
	private PanierProduitRepository panierProduitRepo;
	
	
	@GetMapping(value="/")
	public String  accueil()
	{
		
		return "/index";
	}
	@GetMapping(value="/login")
	public String login() {
		
		return "/admin/login";
	
	}
	
	@ModelAttribute("client")
	public Client client() {
		return new Client();
		
	}
	
	@RequestMapping(value="/403")
	public String aceesRefuser() {
		
		return "/admin/403";
	
	}

	@GetMapping(value = "/pages/seConnecter")
	public String seConnecter(Model model) {
		
		return "/pages/seConnecter";
		
	}
	
	@RequestMapping(value = "pages/authClient",method = RequestMethod.POST)
	public String connection(Model model,String email,String motDePasse) {
		//si le client existe dans la base je l'enrigitsre dans la session si non je redirige ver la conexion
		List<Client> clients=clientRepo.findAll();
		for(Client client:clients) {
			if(client.getEmail().equals(email) && client.getMotDePasse().equals(motDePasse)) {
				model.addAttribute("client",client);
				
				return "redirect:/";
				
				}
		}
		return"redirect:/pages/seConnecter";
	}
	@GetMapping(value = "/pages/seDeconnecter")
	public String deconnection(Model model) {
		model.addAttribute("client",new Client());
		
		return "redirect:/";
		
	}
	@RequestMapping(value = "/pages/panierProduit")
	public String ajouterAuPanier(Model model,int page,int size,String idproduit,Client client ,
			@RequestParam(name="type", defaultValue="")String type) {
		int idProduit=Integer.parseInt(idproduit);
		if ((produitRepo.existsById(idProduit) || menuRepo.existsById(idProduit))& client.getEmail()!=null) {
			PanierProduit panier= new PanierProduit();
			panier.setIdClient(client.getIdClient());
			panier.setIdProduit(idProduit);
			panierProduitRepo.save(panier);
		}
		if(type.equals("menu")) {
		return "redirect:/pages/menus?page="+page+"&size="+size;
		}
		return "redirect:/pages/produits?page="+page+"&size="+size;
	}
	
}