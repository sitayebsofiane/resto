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

import fr.opendevup.dao.*;
import fr.opendevup.entities.*;
/**
 * @author sitayeb sofiane
 * controller qui gere:
 * 1)la page d'affichage des commande non traiter par l'admin
 * 2)la page d'affichage des commande traiter par l'admin
 * 3)la page panier pour le client
 * 4)methode qui suprime un produit du panier avant la commande par le client
 * 5)la methode commander par le client 
 * 6)la methode traitement commande par l'admin qui change le statut de la commande de 0 a 1
 * 7)la page consulter la commande par l'admin pour voir les produit q'elle contient
 * 8)suprimer une commande deja traité
 */
@Controller
@SessionAttributes({"client"})
public class CommandeController {
	@Autowired
	private ProduitRepository produitRepo;
	@Autowired
	private PanierProduitRepository panierProduitRepo;
	@Autowired
	private CommandeRepository commandeRepo;
	@Autowired
	private ConsulterCommandeRepository consulterCommandeRepo;
	@Autowired
	private MenuRepository menuRepo;
	/**
	 * 
	 * @param model pour stocké les paramettre de la page a afiicher
	 * @param page le numero de la page par defaut il est a 0
	 * @param size le nombre d'element par page par defaut il est a 10
	 * @param mc mot cle pour la recherche par defaut il est a chaine vide
	 * @return si tout va bien je retourn la page a afiché avec les commande non traité si non je retourne la page d'erreur
	 */
	@RequestMapping(value="/admin/commandesNonTraiter",method = RequestMethod.GET)
	public String  commande(Model model,@RequestParam(name="page",defaultValue = "0")int page,
			@RequestParam(name="size",defaultValue = "10")int size,
			@RequestParam(name="mc",defaultValue = "")String mc)
	{
		try {
			// dans pageCommandes il ya que les commande qui ont le statut a 0 qui vont etre ajouter a listeCommande
			Page<Commande> pageCommandes = commandeRepo.chercherCommandeNonTraiter("%"+mc+"%", PageRequest.of(page,size));
			model.addAttribute("listeCommande",pageCommandes.getContent());
			int[] pages= new int[pageCommandes.getTotalPages()];
			model.addAttribute("pages",pages);
			model.addAttribute("size",size);
			model.addAttribute("pageCourante",page);
			model.addAttribute("mc",mc);
			return "/admin/commandesNonTraiter";
		}catch (Exception e) {
			return "/admin/erreur";
		}
	}
	
	/**
	 * 
	 * @param model pour enrigistré les paramtre de la page a affiché 
	 * @param page numero de page par defaut 0
	 * @param size nombre d'element a affiché dans la page par defaut 10
	 * @param mc mot cle pour la recherche par nom du client par defaut chaine vide
	 * @return je revoi vers la page des commande traité  ou la page d'erreur si il ya erreur
	 */
	@RequestMapping(value="/admin/commandesTraiter",method = RequestMethod.GET)
	public String  commandeTraite(Model model,@RequestParam(name="page",defaultValue = "0")int page,
			@RequestParam(name="size",defaultValue = "10")int size,
			@RequestParam(name="mc",defaultValue = "")String mc)
	{
		try {
			// dans pageCommandes il ya que les commande qui ont le statut a 0 qui vont etre ajouter a listeCommande
			Page<Commande> pageCommandes = commandeRepo.chercherCommandeTraiter("%"+mc+"%", PageRequest.of(page,size));
			model.addAttribute("listeCommande",pageCommandes.getContent());
			int[] pages= new int[pageCommandes.getTotalPages()];
			model.addAttribute("pages",pages);
			model.addAttribute("size",size);
			model.addAttribute("pageCourante",page);
			model.addAttribute("mc",mc);
			return "/admin/commandesTraiter";
		}catch (Exception e) {
		return "/admin/erreur";
		}
	}
	/**
	 * 
	 * @param client je recupere le client de secssion pour lui faire l'action commander
	 * @return je lui enrigistre la commande et je le revoi vers la page d'accueil
	 */
	@RequestMapping(value = "/pages/commander",method = RequestMethod.GET)
	public String commander(Client client) {
		Commande commande = new Commande(null, 0.0, client.getIdClient(),
				client.getNom(), client.getAdresse(),client.getTelephone(),0);
		commandeRepo.save(commande);
		//je recherche la commande avec son id dans la base
		for (Commande el : commandeRepo.findAll()) {
			if (el.getDate() ==null)
				commande = el;
		}
		//je recupere toute les ligne de la table panier dans une liste
		List<PanierProduit> paniers=  panierProduitRepo.findAll();
		double prixTotal=0.0;
		Date date=null;
		//je parcours la liste pour recupere les produit qui conserne le client enrigistré dans la session
		for (PanierProduit panierProduit : paniers) {
			if(panierProduit.getIdClient() == client.getIdClient()) {
				//je verifier le type du produit pour le chercher dans sa table
				if(panierProduit.getType().equals("produit")) {
						Produit p=produitRepo.getOne(panierProduit.getIdProduit());
						//je calcule le prix total
						prixTotal += p.getPrix();
						//je recupere la date du panier
						date=panierProduit.getDate();
						//je enrigistre dans la table des commandes de produit du client avec le statut 0
						consulterCommandeRepo.save(new ClientProduitCommande(client.getIdClient(), 
								p.getIdProduit(), commande.getIdCommande(), p.getNom(), p.getDescription(), p.getPrix(),"produit", 0));
						panierProduitRepo.deleteById(panierProduit.getIdPanierProduit());
				}else {
					Menu m=menuRepo.getOne(panierProduit.getIdProduit());
					//je calcule le prix total
					prixTotal += m.getPrix();
					//je recupere la date du panier
					date = panierProduit.getDate();
					//je enrigistre le produit dans la table des commandes de produit du client et je le suprime du panier avec statut 0
					consulterCommandeRepo.save(new ClientProduitCommande(client.getIdClient(), 
							m.getIdProduit(), commande.getIdCommande(), m.getNom(), m.getDescription(), m.getPrix(),"menu",0));
					panierProduitRepo.deleteById(panierProduit.getIdPanierProduit());
				}
			}
		}
		//a la fin j'enregistre dans la table les commande le prix total ainsi les informations du client et le statut a 0
		//pour dire que la commande n'est pas encore vue par l'admin et je redirige vers l'acueill
		commande.setPrixTotal(prixTotal);
		commande.setDate(date);
		commandeRepo.save(commande);
		return "redirect:/";
	}
	/**
	 * 
	 * @param idCommande je recupere l'id de la commande a traité
	 * @param mc mot cle (nom du client qui correspond a la commande) envoyé par les para par defaut est chaine vide envoyé par la requtte get
	 * @param page numero de la page  envoyé par la requtte get
	 * @param size le nombre d'element de la page envoyé par la requtte get
	 * @param idClient id du client envoyé par la requette get 
	 * @return je change le statut de la commande a 1 et je le renvoi a la meme page qui est la page commandesNonTraiter
	 */
	@RequestMapping(value = "/admin/traiterCommande",method = RequestMethod.GET)
	public String traiterCommande(int idCommande,String mc,int page,int size,int idClient) {
		
		//je selectione tout les produits du panier commandé
		List<ClientProduitCommande> produitPanier=  consulterCommandeRepo.findAll();
		// je parcours la liste apres le traitement de la commande je mets le statut a 1 de tout les produits de la commande
		for (ClientProduitCommande clientProduitCommande : produitPanier) {
			if(idClient == clientProduitCommande.getIdClient() && idCommande == clientProduitCommande.getIdCommande()) {
				ClientProduitCommande element = consulterCommandeRepo.getOne(clientProduitCommande.getIdClientProduitCommande());
				element.setStatut(1);
				consulterCommandeRepo.save(element);
				}
			}
			//je selectione la commande dans la table Commande avec les même caratiristique 
			Commande commande = commandeRepo.getOne(idCommande);
			//je change le statut de la commande
			commande.setStatut(1);
			//et je la enrigistre avec le nouveau statut
			commandeRepo.save(commande);
			
		return "redirect:/admin/commandesNonTraiter?page="+page+"&size="+size+"&mc="+mc;
	}
	/**
	 * 
	 * @param model pour stoké la liste des produit de la commande
	 * @param idClient id du client envoyé par la requette get 
	 * @param idCommande id de la commande envoyé par la requette get 
	 * @return je le retourne vers la page consulterCommandeNonTraiter ou pourra voir les produit ou menu non trité de la commande
	 */
	@RequestMapping(value = "/admin/consulterCommandeNonTraiter")
	public String consulterCommandeNonTraiter(Model model,int idClient,int idCommande) {
		//je selectione toutes la liste des produit commander par le client 
		List<ClientProduitCommande> produitPanier = consulterCommandeRepo.findAll();
		List<ClientProduitCommande> listeProduitClientNonTraiter = new ArrayList<ClientProduitCommande>();
		//je parcours les commandes du client
		for (ClientProduitCommande clientProduitCommande : produitPanier) {
			//si le statut est ==0 c'est a dire non traiter je l'enrigistre dans une liste pour l'afficher dans la vue conslter Commande
			if(idClient == clientProduitCommande.getIdClient() && idCommande == clientProduitCommande.getIdCommande()
					&& clientProduitCommande.getStatut() == 0)
				listeProduitClientNonTraiter.add(clientProduitCommande);
		}
		model.addAttribute("listeProduitClient", listeProduitClientNonTraiter);
		return "/admin/consulterCommandeNonTraiter";
	}
	/**
	 * 
	 *@param model pour stoké la liste des produit de la commande
	 * @param idClient id du client envoyé par la requette get 
	 * @param idCommande id de la commande envoyé par la requette get 
	 * @return je le retourne vers la page consulterCommandeNonTraiter ou pourra voir les produit ou menu trité de la commande
	 */
	@RequestMapping(value = "/admin/consulterCommandeTraiter")
	public String consulterCommandeTraiter(Model model,int idClient,int idCommande) {
		//je selectione toutes la liste des produit commander par le client 
		List<ClientProduitCommande> produitPaniercommande = consulterCommandeRepo.findAll();
		List<ClientProduitCommande> listeProduitClientTraiter = new ArrayList<ClientProduitCommande>();
		//je parcours les commandes du client
		for (ClientProduitCommande clientProduitCommande : produitPaniercommande) {
			//si le statut est == 1 c'est a dire non traiter je l'enrigistre dans une liste pour l'afficher dans la vue conslter Commande
			if(idClient == clientProduitCommande.getIdClient() && idCommande == clientProduitCommande.getIdCommande()
					&& clientProduitCommande.getStatut() == 1)
				listeProduitClientTraiter.add(clientProduitCommande);
		}
		model.addAttribute("listeProduitClient", listeProduitClientTraiter);
		return "/admin/consulterCommandeTraiter";
	}
	
	/**
	 * 
	 * @param idCommande envoyé par la method get lors de selection de l'admin du la commande a suprimé
	 * @param mc  envoyé par la method get lors de selection de l'admin du la commande a suprimé
	 * @param page  envoyé par la method get lors de selection de l'admin du la commande a suprimé
	 * @param size  envoyé par la method get lors de selection de l'admin du la commande a suprimé
	 * @return je suprime la commande si tout va bien je le renvoi vers la même page sinon vers une page d'erreur
	 */
	@RequestMapping(value="/admin/deleteCommande",method = RequestMethod.GET)
	public String  deleteCommande(int idClient,int idCommande,String mc,int page,int size)
	{
		List<ClientProduitCommande> produitPaniercommande = consulterCommandeRepo.findAll();
		try {
			//je supprime egalement les produit de la commande que je vais de suprimé
			for (ClientProduitCommande clientProduitCommande : produitPaniercommande) {
				if(clientProduitCommande.getIdClient() == idClient)
					consulterCommandeRepo.deleteById(clientProduitCommande.getIdClientProduitCommande());;
			}
			commandeRepo.deleteById(idCommande);
			return "redirect:/admin/commandesTraiter?page="+page+"&size="+size+"&mc="+mc;
			}catch (Exception e) {
				return "/admin/erreur";
			}
	}
}

	
	
	
