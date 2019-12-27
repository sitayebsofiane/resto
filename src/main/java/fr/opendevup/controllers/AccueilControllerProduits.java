package fr.opendevup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fr.opendevup.dao.ProduitRepository;
import fr.opendevup.entities.Produit;

@Controller
public class AccueilControllerProduits {
	@Autowired
	private ProduitRepository produitRepo;
	@RequestMapping(value = "pages/produits",method = RequestMethod.GET)
	public String AccueilProduits(Model model,@RequestParam(name="page", defaultValue="0")int page,
			@RequestParam(name="size", defaultValue="10")int size,
			@RequestParam(name="mc",defaultValue = "")String mc) {
		Page<Produit> produits=  produitRepo.chercher("%"+mc+"%", PageRequest.of(page,size));
		
		model.addAttribute("listeProduit",produits.getContent());
		//creation d'un tableu de page
		int [] pages= new int [produits.getTotalPages()];
		model.addAttribute("pages",pages);
		model.addAttribute("size",size);
		model.addAttribute("pageCourante",page);
		model.addAttribute("mc",mc);
		
		return "pages/produits";
	
	}
	@RequestMapping(value = "pages/menus",method = RequestMethod.GET)
	public String AccueilMenus() {
		
		return "pages/menus";
	
	}
	
}
