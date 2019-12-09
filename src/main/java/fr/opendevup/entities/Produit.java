package fr.opendevup.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Produit implements Serializable{

	
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
	private int idProduit;
	private String nom;
	private String description;
	@OneToMany
	private Collection<Commande> commande;
	@OneToMany
	private Collection<Tarif> tarif;
	
	
	public Produit() {
		super();
	}
	
	public Produit(String nom, String description) {
		this.nom = nom;
		this.description = description;
	}

	public int getIdProduit() {
		return idProduit;
	}
	public void setIdProduit(int id) {
		this.idProduit = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
	
}
