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

/**
 * @author sitayeb sofiane
 *le controller qui gere:
 *1) l'affichage de la page d'acceuil
 *2)page d'erreur pour les role des admins
 *3)la page de login pour l'admin
 *4)la page de connection pour le client
 *5)page panier du client
 *
 */

@Controller
@SessionAttributes({"client"}) //je stock le client dans la vriable de la session
public class AccueilController {
	@Autowired
	private ClientRepository clientRepo;
	@Autowired
	private ProduitRepository produitRepo;
	@Autowired
	private MenuRepository menuRepo;
	@Autowired
	private PanierProduitRepository panierProduitRepo;
	
	/**
	 * 
	 * @return la page d'accueil
	 */
	@GetMapping(value="/")
	public String  accueil()
	{
		
		return "/index";
	}
	/**
	 * 
	 * @return la page de login de l'admin
	 */
	@GetMapping(value="/login")
	public String login() {
		
		return "/admin/login";
	
	}
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
	 * @return la page de non autorisation pour l'admin
	 */
	@RequestMapping(value="/403")
	public String aceesRefuser() {
		
		return "/admin/403";
	
	}
	/**
	 * 
	 * @return page invitant client a se connecter
	 */
	@GetMapping(value = "/pages/seConnecter")
	public String seConnecter() {
		
		return "/pages/seConnecter";
		
	}
	/**
	 * 
	 * @param model pour enrigistré le client qui est authentifié
	 * @param email saisie par le client 
	 * @param motDePasse saisie par le client
	 * @return je redirige soit vers l'acueil si la conesion est bien passer sino vers la page de connection
	 */
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
	
	/**
	 * 
	 * @param model pour enrigistré un client mais avec null pour ces attribut
	 * @return et je redirige vers la page d'acueil
	 */
	@GetMapping(value = "/pages/seDeconnecter")
	public String deconnection(Model model) {
		model.addAttribute("client",new Client());
		
		return "redirect:/";	
	}
	
	/**
	 * 
	 * @param page je la recupere par la methode get pour l'utiliser pour la page de retour
	 * @param size je la recupere par la methode get l'utiliser pour la page de retour
	 * @param idproduit je la recupere par la methode get
	 * @param client je la recupere via la variable de session
	 * @param type je la recupere par la methode get
	 * @return la page ou client se situe si l'enrigistrement du panier et bien effectuer sinon vers une page d'erreur
	 */
	@RequestMapping(value = "/pages/panierProduit")
	public String ajouterAuPanier(int page,int size,String idproduit,Client client ,
			@RequestParam(name="type", defaultValue="")String type) {
		int idProduit=Integer.parseInt(idproduit);
		// je verifier si le produit/menu existe dans la base et que le client est bien conneté via son mail
		if ((produitRepo.existsById(idProduit) || menuRepo.existsById(idProduit)) && client.getEmail()!=null) {
			// j'enrigistre un nouveau panier avec un produit pour le client
			PanierProduit panier= new PanierProduit();
			panier.setIdClient(client.getIdClient());
			panier.setIdProduit(idProduit);
			panier.setType((type.equals("menu"))? "menu":"produit");
			try {
				panierProduitRepo.save(panier);
			}catch (Exception e) {
				return "/admin/erreur";
			}
		}
		//ensuite je redirige vers la page ou il était le client
		if(type.equals("menu")) {
		return "redirect:/pages/menus?page="+page+"&size="+size;
		}
		return "redirect:/pages/produits?page="+page+"&size="+size;
	}
	
}