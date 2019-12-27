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

import fr.opendevup.dao.ProduitRepository;
import fr.opendevup.entities.Produit;

@Controller
public class ProduitsController {
	@Autowired
	private ProduitRepository produitRepo;
		 
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
	@RequestMapping(value = "/admin/deleteProduits",method = RequestMethod.GET)
	public String delete(int id,String mc,int page,int size) {
		produitRepo.deleteById(id);
		return "redirect:/admin/consulterProduits?page="+page+"&size="+size+"&mc="+mc;
	
	}
	@RequestMapping(value = "/admin/ajouterProduits",method = RequestMethod.GET)
	public String ajoutProduit(Model model) {
		model.addAttribute("produit",new Produit());
		return "admin/ajouterProduits";	
	}
	@RequestMapping(value = "/admin/modifierProduit",method = RequestMethod.GET)
	public String modifierProduit(Model model,int idProduit) {
		Produit p= produitRepo.getOne(idProduit);
		model.addAttribute("produit",p);
		return "admin/modifierProduit";		
	}
	@RequestMapping(value = "/admin/enregistrerProduit",method = RequestMethod.POST)
	public String enregistrer(Model model, @Valid Produit produit,BindingResult erreur) {
		if (erreur.hasErrors()) 
			return "admin/ajouterProduits";
		else
		 produitRepo.save(produit);
		return "admin/confirmationAjoutProduit" ;
		
	}
	}
		
	
