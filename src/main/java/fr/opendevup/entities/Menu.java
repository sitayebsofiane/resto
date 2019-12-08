package fr.opendevup.entities;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
@Entity
@Table(name = "menu")
@PrimaryKeyJoinColumn(name = "idProduit")
public class Menu extends Produit implements Serializable {

	
	private static final long serialVersionUID = 1L;

	
	 private ArrayList<Produit> produits;
	public Menu() {
		super();
	}
	public Menu(String nom, String description, Tarif tarif, int idCommande,ArrayList<Produit> produits) {
		super(nom, description, tarif, idCommande);
		this.produits=produits;
		
	}
	public ArrayList<Produit> getProduits() {
		return produits;
	}
	public void setProduits(ArrayList<Produit> produits) {
		this.produits = produits;
	}
	
	 
	 

}
