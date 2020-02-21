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

import fr.opendevup.dao.MenuRepository;
import fr.opendevup.dao.ProduitRepository;
import fr.opendevup.entities.Menu;
import fr.opendevup.entities.Produit;
/**
 * 
 * @author sitayeb sofiane
 * controller qui gere:
 * 1)la gestion des produits par l'admin
 * 2)la gestion des menu par l'admin
 *
 */
@Controller
public class AdminProduitsController {
	@Autowired
	private ProduitRepository produitRepo;
	@Autowired
	private MenuRepository menuRepo;
		 /**
		  * 
		  * @param model pour enrigistre dans le model le numero de la page le nombre d'element et le mon clé pour chercher par le nom du produit
		  * @param page numero de la page envoyé par la methode get
		  * @param size nombre d'element de la page envoyé par la method get
		  * @param mc mot cle envoyer par la methode get
		  * @return je revoie vers la page conslterProduits
		  */
	@RequestMapping(value = "/admin/consulterProduits",method = RequestMethod.GET)
	public String consultProduits(Model model,@RequestParam(name="page", defaultValue="0")int page,
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
				return "admin/consulterProduits";
			
			}
	/**
	 * 
	 * @param model pour enrigistre dans le model le numero de la page le nombre d'element et le mon clé pour chercher par le nom du produit
	 * @param page numero de la page envoyé par la methode get
	 * @param size nombre d'element de la page envoyé par la method get
	 * @param mc mot cle envoyer par la methode get
	 * @return je revoie vers la page conslterMenus
	 */
	 
	@RequestMapping(value = "admin/consulterMenus",method = RequestMethod.GET)
	public String consultMenus(Model model,@RequestParam(name="page", defaultValue="0")int page,
			@RequestParam(name="size", defaultValue="10")int size,
			@RequestParam(name="mc",defaultValue = "")String mc) {
			
				Page<Menu> menus=  menuRepo.chercher("%"+mc+"%", PageRequest.of(page,size));
			
				model.addAttribute("listeMenu",menus.getContent());
				//creation d'un tableu de page
				int [] pages= new int [menus.getTotalPages()];
				model.addAttribute("pages",pages);
				model.addAttribute("size",size);
				model.addAttribute("pageCourante",page);
				model.addAttribute("mc",mc);
				return "admin/consulterMenus";
			
			}
	/**
	 * 
	 * @param id produits renvoyé par la method get
	 * @param mc mot clé renvoyé par la methode get 
	 * @param page numero de la page par la methode get
	 * @param size nombre d'ellement dans la page par la mthod get
	 * @return apres la supression de l'element je redirige  vers la meme page ou il été si tout va bien 
	 * si la suppression n'a pas bien fonctioné je le renvoi vers la page d'erreur
	 */
	@RequestMapping(value = "/admin/deleteProduits",method = RequestMethod.GET)
	public String deleteProduit(int id,String mc,int page,int size) {
		try {
		produitRepo.deleteById(id);
		return "redirect:/admin/consulterProduits?page="+page+"&size="+size+"&mc="+mc;
		}catch (Exception e) {
			return "/admin/erreur";
		}
	
	}
	/* @param id produits renvoyé par la method get
	 * @param mc mot clé renvoyé par la methode get 
	 * @param page numero de la page par la methode get
	 * @param size nombre d'ellement dans la page par la mthod get
	 * @return apres la supression de l'element je redirige  vers la meme page ou il été si tout va bien 
	 * si la suppression n'a pas bien fonctioné je le renvoi vers la page d'erreur
	 */
	@RequestMapping(value = "/admin/deleteMenus",method = RequestMethod.GET)
	public String deleteMenu(int id,String mc,int page,int size) {
		try {
		menuRepo.deleteById(id);
		return "redirect:/admin/consulterMenus?page="+page+"&size="+size+"&mc="+mc;
		}catch (Exception e) {
			return "/admin/erreur";
		}
	}
	/**
	 * 
	 * @param model pour enrigistré dans le model un nouveu produit vide
	 * @return je revoi vers la page ajoutProduits ou il ya le formulaire d'ajout du produit
	 */
	@RequestMapping(value = "/admin/ajouterProduits",method = RequestMethod.GET)
	public String ajoutProduit(Model model) {
		model.addAttribute("produit",new Produit());
		return "admin/ajouterProduits";	
	}
	/**
	 
	 * @param model pour enrigistré dans le model un nouveu menu vide
	 * @return je revoi vers la page ajoutProduits ou il ya le formulaire d'ajout du menu
	 */
	@RequestMapping(value = "/admin/ajoutMenus",method = RequestMethod.GET)
	public String ajoutMenu(Model model) {
		model.addAttribute("menu",new Menu());
		return "admin/ajoutMenus";	
	}
	
	/**
	 * 
	 * @param model j'enrigistre dans le model le produit a modifier 
	 * @param idProduit envoyé par la method get qui selection par l'admin pour etre modifié
	 * @return je revoie vers la page modifier produit si tout va bien sinon vers la page d'eurreur
	 */
	@RequestMapping(value = "/admin/modifierProduit",method = RequestMethod.GET)
	public String modifierProduit(Model model,int idProduit) {
		try {
		Produit p= produitRepo.getOne(idProduit);
		model.addAttribute("produit",p);
		return "admin/modifierProduit";		
		}catch (Exception e) {
			return "/admin/erreur";
		}
	}
	/**
	 @param model j'enrigistre dans le model le menu a modifier 
	 * @param idProduit envoyé par la method get qui selection par l'admin pour etre modifié
	 * @return je revoie vers la page modifier menu si tout va bien sinon vers la page d'eurreur
	 */
	@RequestMapping(value = "/admin/modifierMenu",method = RequestMethod.GET)
	public String modifierMenu(Model model,int idProduit) {
		try {
		Menu m= menuRepo.getOne(idProduit);
		model.addAttribute("menu",m);
		return "admin/modifierMenu";	
		}catch (Exception e) {
			return "/admin/erreur";
		}
	}
	/**
	 * @param produit produit retourner par la methode post via le formulaire qui a été remplie par l'admin
	 * @param erreur les eventuelle erreur lors de l'enrigistrement du produit
	 * @return si il ya mauvaise saisie de l'admin je revoie vers la page ajouterProduit sinon si tout va bien j'enrigistre ou j'envoie
	 * vers la page d'erreur
	 */
	@RequestMapping(value = "/admin/enregistrerProduit",method = RequestMethod.POST)
	public String enregistrerProduit( @Valid Produit produit,BindingResult erreur) {
		if (erreur.hasErrors()) 
			return "admin/ajouterProduits";
		else
		try {
		 produitRepo.save(produit);
		return "admin/confirmationAjoutProduit" ;
		}catch (Exception e) {
			return "/admin/erreur";
		}
		
	}
	/**
	 * @param produit produit retourner par la methode post via le formulaire qui a été remplie par l'admin
	 * @param erreur les eventuelle erreur lors de l'enrigistrement du produit
	 * @return si il ya mauvaise saisie de l'admin je revoie vers la page ajouterProduit sinon si tout va bien j'enrigistre ou j'envoie
	 * vers la page d'erreur
	 */
	@RequestMapping(value = "/admin/enregistrerMenu",method = RequestMethod.POST)
	public String enregistrerMenu(@Valid Menu menu,BindingResult erreur) {
		if (erreur.hasErrors()) 
			return "/admin/ajoutMenus";
		else
			try {
		 menuRepo.save(menu);
		return "admin/confirmationAjoutMenu" ;
			}catch (Exception e) {
				return "/admin/erreur";
			}
		}
	}
		
	
