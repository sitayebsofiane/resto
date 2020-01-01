package fr.opendevup.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import fr.opendevup.dao.PanierProduitRepository;
import fr.opendevup.dao.ProduitRepository;
import fr.opendevup.entities.Client;
import fr.opendevup.entities.PanierProduit;
import fr.opendevup.entities.Produit;

@Controller
@SessionAttributes({"client"})
public class CommandeController {
	@Autowired
	private ProduitRepository produitRepo;
	@Autowired
	private PanierProduitRepository panierProduitRepo;
	
	@RequestMapping(value="/admin/commandes",method = RequestMethod.GET)
	public String  commande()
	{
		
		return "/admin/commandes";
	}
	@RequestMapping(value = "/pages/panier")
	public String consulterPanier(Model model,Client client ) {
		List<PanierProduit> paniers=  panierProduitRepo.findAll();
		List<Produit> produits= new ArrayList<Produit>();
		 for (PanierProduit panierProduit : paniers) {
			 if(panierProduit.getIdClient()==client.getIdClient()) {
				 produits.add(produitRepo.getOne((panierProduit.getIdProduit())));
			 }
			model.addAttribute("listeProduit", produits);
		}
		
		
		return"/pages/panier";
	}
	
	@RequestMapping(value = "/pages/deleteProduitDuPanier",method = RequestMethod.GET)
	public String deleteProduitDuPanier(int id,Client client) {
		List<PanierProduit> paniers=  panierProduitRepo.findAll();
		for (PanierProduit panierProduit : paniers) {
			if(id==panierProduit.getIdProduit() && panierProduit.getIdClient()==client.getIdClient()) {
				panierProduitRepo.delete(panierProduit);
				break;
			}
		}
		return "redirect:/pages/panier";
	}
	@RequestMapping(value = "/pages/commander",method = RequestMethod.GET)
	public String commander(Client client) {
		//enrigistrer le panier qui corespend au client dans une table commande pour l'afficher a l'admin user
		return "redirect:/";
	}
}

	
	
	
