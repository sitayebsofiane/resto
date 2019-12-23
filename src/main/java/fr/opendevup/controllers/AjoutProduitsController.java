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
public class AjoutProduitsController {
	@Autowired
	private ProduitRepository produitRepo;
		 
	@RequestMapping(value = "admin/ajoutProduits",method = RequestMethod.GET)
	public String ajoutProduits(Model model,@RequestParam(name="login", defaultValue="")String login,
			@RequestParam(name="password", defaultValue="")String password ,@RequestParam(name="page", defaultValue="0")int page,
			@RequestParam(name="size", defaultValue="4")int size) {
			if(login.equals("assia") && password.equals("as122014")) {
				model.addAttribute("login",login);
				model.addAttribute("password",password);
				Page<Produit> produits= produitRepo.findAll(PageRequest.of(page,size));
				model.addAttribute("listeProduit",produits.getContent());
				//creation d'un tableu de page
				int [] pages= new int [produits.getTotalPages()];
				model.addAttribute("pages",pages);
				model.addAttribute("size",size);
				model.addAttribute("pageCourante",page);
				
				return "admin/ajoutProduits";
			}else {
				return "redirect:../adminer";
			}
		
	}
		}
	
