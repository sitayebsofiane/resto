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
	
	@RequestMapping(value="/admin/commandes",method = RequestMethod.GET)
	public String  commande(Model model,@RequestParam(name="page",defaultValue = "0")int page,
			@RequestParam(name="size",defaultValue = "10")int size,
			@RequestParam(name="mc",defaultValue = "")String mc)
	{
		// dans pageCommandes il ya que les commande qui ont le statut a 0 qui vont etre ajouter a listeCommande
		Page<Commande> pageCommandes = commandeRepo.chercher("%"+mc+"%", PageRequest.of(page,size));
		model.addAttribute("listeCommande",pageCommandes.getContent());
		int[] pages= new int[pageCommandes.getTotalPages()];
		model.addAttribute("pages",pages);
		model.addAttribute("size",size);
		model.addAttribute("pageCourante",page);
		model.addAttribute("mc",mc);
		return "/admin/commandes";
	}
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
	
	@RequestMapping(value = "/admin/traiterCommande",method = RequestMethod.GET)
	public String traiterCommande(int idCommande,String mc,int page,int size,int idClient) {
		//je declare une variable pour enrigistré idClientProduitCommande
		int  idClientProduitCommande = 0;
		//je selectione tout les produits du panier
		List<ClientProduitCommande> produitPanier=  consulterCommandeRepo.findAll();
		// apres le traitement de la commande je mets le statut a 1
		for (ClientProduitCommande clientProduitCommande : produitPanier) {
			idClientProduitCommande = clientProduitCommande.getIdClientProduitCommande();
			if(idClient == clientProduitCommande.getIdClient() && clientProduitCommande.getType().equals("produit")
					&& idCommande == clientProduitCommande.getIdCommande()) {
				//je creer un nouveau produit dans la table Client ProduitCommande avec les même caracteristique 
				Produit produit = produitRepo.getOne(clientProduitCommande.getIdProduit());
				//je suprime le produit dans la table ClientProduitCommande
				consulterCommandeRepo.deleteById(idClientProduitCommande);
				//j'enrigistre ce produit dans la table avec le statut a 1
				consulterCommandeRepo.save(new ClientProduitCommande(idClient, produit.getIdProduit(), idCommande, produit.getNom(), 
						produit.getDescription(), produit.getPrix(),"produit", 1));
				}else if(idClient == clientProduitCommande.getIdClient() && idCommande == clientProduitCommande.getIdCommande()) {
					//je creer un nouveau menu dans la table ClientProduitCommande avec les même caracteristique 
					Menu menu = menuRepo.getOne(clientProduitCommande.getIdProduit());
					//je suprime le produit dans la table ClientProduitCommande
					consulterCommandeRepo.deleteById(idClientProduitCommande);
					//j'enrigistre ce menu dans la table  ClientProduitCommande avec le statut a 1
					consulterCommandeRepo.save(new ClientProduitCommande(idClient, menu.getIdProduit(), idCommande, menu.getNom(), 
							menu.getDescription(), menu.getPrix(),"menu", 1));
				}
			}
			//je creer une nouvelle commande dans la table Commande avec les même caratiristique 
			Commande commande = commandeRepo.getOne(idCommande);
			//esuite je suprime la commande qui est dans la base 
			commandeRepo.deleteById(idCommande);
			//je change le statut de la commande
			commande.setStatut(1);
			//et je la enrigistre avec le nouveau statut
			commandeRepo.save(commande);
			
		return "redirect:/admin/commandes?page="+page+"&size="+size+"&mc="+mc;
	}
	
	@RequestMapping(value = "/admin/consulterCommande")
	public String consulterCommande(Model model,int idClient,int idCommande) {
		List<ClientProduitCommande> produitPanier = consulterCommandeRepo.findAll();
		List<ClientProduitCommande> listeProduitClient = new ArrayList<ClientProduitCommande>();
		for (ClientProduitCommande clientProduitCommande : produitPanier) {
			if(idClient == clientProduitCommande.getIdClient() && idCommande == clientProduitCommande.getIdCommande()
					&& clientProduitCommande.getStatut() == 0)
				listeProduitClient.add(clientProduitCommande);
		}
		model.addAttribute("listeProduitClient", listeProduitClient);
		return "/admin/consulterCommande";
	}
}

	
	
	
