package fr.opendevup.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import fr.opendevup.dao.MenuRepository;
import fr.opendevup.dao.ProduitRepository;
import fr.opendevup.entities.Client;
import fr.opendevup.entities.Produit;
import fr.opendevup.entities.Menu;

/**
 * 
 * @author sitayeb sofiane
 * le controller qui gere:
 * 1)la page d'affichage des produits a la carte  pour le client
 * 2)la page d'affichage des menus  pour le client
 *
 */

@Controller
@SessionAttributes({"client"}) //je stock le client dans la vriable de la sessio
public class AccueilControllerProduits {
	
	@Autowired
	private ProduitRepository produitRepo;
	
	@Autowired
	private MenuRepository menuRepo;
	
	
	/**
	 * 
	 * @return j'enrigitre un nouveu client dans la session par defaut si il ya pas connection d'un client il est null
	 */
	@ModelAttribute("client")
	public Client client() {
		return new Client();	
	}
	
	/**
	 * 
	 * @param model pour enrigistré le numero de la page et le nombre d'ellement pour chaque page
	 * @param page le numero de la page renvoyé par la methode get
	 * @param size le nombre d'element renvoyé par la methode get
	 * @return je renvoie verzs la page des produits
	 */
	@RequestMapping(value = "pages/produits",method = RequestMethod.GET)
	public String accueilProduits(Model model,@RequestParam(name="page", defaultValue="0")int page,
			@RequestParam(name="size", defaultValue="6")int size) {
		Page<Produit> produits= produitRepo.findAll(PageRequest.of(page,size));
		model.addAttribute("listeProduit",produits.getContent());
		//creation d'un tableu de page
		int [] pages= new int [produits.getTotalPages()];
		model.addAttribute("pages",pages);
		model.addAttribute("size",size);
		model.addAttribute("pageCourante",page);
		return "pages/produits";
	}
	/**
	 * 
	  @param model pour enrigistré le numero de la page et le nombre d'ellement pour chaque page
	 * @param page le numero de la page renvoyé par la methode get
	 * @param size le nombre d'element renvoyé par la methode get
	 * @return je renvoie verzs la page des menis
	 */
	@RequestMapping(value = "pages/menus",method = RequestMethod.GET)
	public String accueilMenus(Model model,@RequestParam(name="page", defaultValue="0")int page,
			@RequestParam(name="size", defaultValue="6")int size) {
		Page<Menu> menus= menuRepo.findAll(PageRequest.of(page,size));
		model.addAttribute("listeMenu",menus.getContent());
		//creation d'un tableu de page
		int [] pages= new int [menus.getTotalPages()];
		model.addAttribute("pages",pages);
		model.addAttribute("size",size);
		model.addAttribute("pageCourante",page);
		return "pages/menus";
	
	}

}
