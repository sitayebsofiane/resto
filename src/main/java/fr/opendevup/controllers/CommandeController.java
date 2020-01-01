package fr.opendevup.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import fr.opendevup.dao.CommandeRepository;
import fr.opendevup.dao.PanierProduitRepository;
import fr.opendevup.dao.ProduitRepository;
import fr.opendevup.entities.Client;
import fr.opendevup.entities.Commande;
import fr.opendevup.entities.PanierProduit;
import fr.opendevup.entities.Produit;

@Controller
@SessionAttributes({"client"})
public class CommandeController {
	@Autowired
	private ProduitRepository produitRepo;
	@Autowired
	private PanierProduitRepository panierProduitRepo;
	@Autowired
	private CommandeRepository commandeRepo;
	
	@RequestMapping(value="/admin/commandes",method = RequestMethod.GET)
	public String  commande(Model model,@RequestParam(name="page",defaultValue = "0")int page,
			@RequestParam(name="size",defaultValue = "10")int size,
			@RequestParam(name="mc",defaultValue = "")String mc)
	{

		Page<Commande> pageCommandes= commandeRepo.chercher("%"+mc+"%", PageRequest.of(page,size));
		model.addAttribute("listeCommande",pageCommandes.getContent());
		int[] pages= new int[pageCommandes.getTotalPages()];
		model.addAttribute("pages",pages);
		model.addAttribute("size",size);
		model.addAttribute("pageCourante",page);
		model.addAttribute("mc",mc);
	
		
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
		List<PanierProduit> paniers=  panierProduitRepo.findAll();
		double prixTotal=0.0;
		String produits="";
		Date date=null;
		for (PanierProduit panierProduit : paniers) {
			if(panierProduit.getIdClient() ==client.getIdClient()) {
				Produit p=produitRepo.getOne(panierProduit.getIdProduit());
				produits+=p.getNom()+",";
				prixTotal+=p.getPrix();
				date=panierProduit.getDate();
				panierProduitRepo.deleteById(panierProduit.getIdPanierProduit());
			}
		}
		commandeRepo.save(new Commande(date, prixTotal, client.getIdClient(), client.getNom(),
				produits, client.getAdresse(), client.getTelephone()));
		return "redirect:/";
	}
	@RequestMapping(value = "/admin/deleteCommande",method = RequestMethod.GET)
	public String deleteCommande(int idCommande,String mc,int page,int size) {
		commandeRepo.deleteById(idCommande);
		return "redirect:/admin/commandes?page="+page+"&size="+size+"&mc="+mc;
	
	}
}

	
	
	
