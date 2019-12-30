package fr.opendevup.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fr.opendevup.dao.ClientRepository;
import fr.opendevup.dao.PanierRepository;
import fr.opendevup.dao.ProduitRepository;
import fr.opendevup.entities.Client;
import fr.opendevup.entities.Panier;
import fr.opendevup.entities.Produit;
@Controller
public class AccueilController {
	@Autowired
	private ClientRepository clientRepo;
	@Autowired
	private ProduitRepository produitRepo;
	@Autowired
	private PanierRepository panierRepo;
	@RequestMapping(value="/")
	public String  accueil()
	{
		
		return "/index";
	}
	@RequestMapping(value="/login")
	public String login() {
		
		return "/admin/login";
	
	}
	
	@RequestMapping(value="/403")
	public String aceesRefuser() {
		
		return "/admin/403";
	
	}

	@RequestMapping(value = "/pages/seConnecter",method = RequestMethod.GET)
	public String seConnecter(Model model,int idProduit) {
		Produit p= produitRepo.getOne(idProduit);
		model.addAttribute("produit",p);
		return "/pages/seConnecter";
		
	}
	@RequestMapping(value = "pages/authClient",method = RequestMethod.POST)
	public String connection(Model model,@RequestParam(name = "email" ,defaultValue="")String email
			,@RequestParam(name = "motDePasse",defaultValue = "")String motDePasse
			,@RequestParam(name="page", defaultValue="0")int page,
			@RequestParam(name="size", defaultValue="6")int size,Produit produit) {
		List<Client> clients=clientRepo.findAll();
		for(Client client:clients) {
			if(client.getEmail().equals(email) && client.getMotDePasse().equals(motDePasse)) {
				Panier panier=new Panier(client,produit);
				panierRepo.save(panier);
				Page<Produit> produits= produitRepo.findAll(PageRequest.of(page,size));
				model.addAttribute("listeProduit",produits.getContent());
				//creation d'un tableu de page que j'affiche au client
				int [] pages= new int [produits.getTotalPages()];
				model.addAttribute("pages",pages);
				model.addAttribute("size",size);
				model.addAttribute("pageCourante",page);
				return "pages/produits";
				
				}
		}
		return"/pages/seConnecter";
	}
	
}