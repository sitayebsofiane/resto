package fr.opendevup.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Panier implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idPanier;
	
	@OneToOne
	private Client client;
	@OneToOne
	private Produit produit;
	public Panier() {
		super();
	}
	
	public Panier( Client client, Produit produit) {
		this.client = client;
		this.produit = produit;
	}

	public int getIdPanier() {
		return idPanier;
	}
	public void setIdPanier(int idPanier) {
		this.idPanier = idPanier;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Produit getProduit() {
		return produit;
	}
	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	
	
}
