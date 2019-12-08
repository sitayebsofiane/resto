package fr.opendevup.entities;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity
public class Panier  implements Serializable{
	

	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
	private int idPanier;
	@JoinColumn
	private int IdClient;
	private ArrayList<Produit> produits;
	
	
	public Panier() {
		super();
	}


	public Panier(ArrayList<Produit> produits) {
		this.setProduits(produits);
	}


	public ArrayList<Produit> getProduits() {
		return produits;
	}


	public void setProduits(ArrayList<Produit> produits) {
		this.produits = produits;
	}
	
	

}
