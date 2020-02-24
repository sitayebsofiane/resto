package fr.opendevup.controllers;

import java.util.ArrayList;
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
import fr.opendevup.dao.ConsulterCommandeRepository;
import fr.opendevup.dao.MenuRepository;
import fr.opendevup.dao.PanierProduitRepository;
import fr.opendevup.dao.ProduitRepository;
import fr.opendevup.entities.Client;
import fr.opendevup.entities.ClientProduitCommande;
import fr.opendevup.entities.Menu;
import fr.opendevup.entities.PanierProduit;
import fr.opendevup.entities.Produit;

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
	@Autowired
	private ConsulterCommandeRepository consulterCommandeRepo;
	
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
	 * @return la page d'admin
	 */
	@GetMapping(value="/admin")
	public String  accueilAdmin()
	{
		
		return "/admin/clients";
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
	/**
	 * 
	 * @param model pour stocké la liste des produit ou menu commandé par le client 
	 * @param client c'est le client enrigistré de la session
	 * @return je le revoi vers la pages panier ou il ya tout les produit et ou menu selectioné par le client
	 */
	@RequestMapping(value = "/pages/panier")
	public String consulterPanier(Model model,Client client) {
		//je parcour la liste produit et menu dans le panier
		List<PanierProduit> paniers=  panierProduitRepo.findAll();
		//je creer une liste de produits vide
		List<Produit> produits= new ArrayList<Produit>();
		//je creer une liste de menu vide
		List<Menu> menus= new ArrayList<Menu>();
		//je creer une liste qui prend les deux type produit et menu
		List<Object> liste =new ArrayList<Object>();
		//je parcour la liste des produit/menu qui est les paniers et je remplie ma liste avec des produits et des menus
		 for (PanierProduit panierProduit : paniers) {
			 if(panierProduit.getIdClient()==client.getIdClient()) {
				 	if(panierProduit.getType().equals("produit"))
				 		produits.add(produitRepo.getOne((panierProduit.getIdProduit())));
				 	else
				 		menus.add(menuRepo.getOne((panierProduit.getIdProduit())));
				 }
			 }
		 	for (Produit p : produits) {
				liste.add(p);
			}
		 	for (Menu m : menus) {
				liste.add(m);
			}
		 	//je met la liste dans le model 
			model.addAttribute("liste", liste);
			
		return"/pages/panier";
	}
	/**
	 * 
	 * @param id du produit retourné par la methode get lors de la selection du client du produit a suprimé
	 * @param client je recupere le client enrigistré dans la section qui est connecter
	 * @return je suprime le produit et je le revoi vers la meme page ou il était qui la page du panier
	 */
	@RequestMapping(value = "/pages/deleteProduitDuPanier",method = RequestMethod.GET)
	public String deleteProduitDuPanier(int id,Client client) {
		//je selectione tout les paniers
		List<PanierProduit> paniers=  panierProduitRepo.findAll();
		//je parcour la liste des produit dans le panier et si l'id correspond un id d'un produit panier et id client corespond
		//au client et je le suprime 
		for (PanierProduit panierProduit : paniers) {
			if(id==panierProduit.getIdProduit() && panierProduit.getIdClient()==client.getIdClient()) {
				panierProduitRepo.delete(panierProduit);
				break;
			}
		}
		return "redirect:/pages/panier";
	}
	@RequestMapping(value = "/pages/commandes",method = RequestMethod.GET)
	public String commandes(Model model,Client client) {
		//je selectione tout les paniers
		List<ClientProduitCommande> listeProduitCommander=  consulterCommandeRepo.findAll();
		List<ClientProduitCommande> liste =  new ArrayList<ClientProduitCommande>();
		//je parcour la liste des produit dans le panier et si l'id correspond un id d'un produit panier et id client corespond
		//au client et je le suprime 
		for (ClientProduitCommande clientProduitCommande: listeProduitCommander) {
			if(clientProduitCommande.getIdClient() == client.getIdClient()) {
				liste.add(clientProduitCommande);
			}
		}
		model.addAttribute("liste", liste);
		return "/pages/commandes";
	}
}