package fr.opendevup.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class Produit implements Serializable{

	
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
	private int idProduit;
	private String nom;
	private String description;
	private double prix;
	
	public Produit() {
		super();
	}
	
	public Produit(String nom, String description,double prix) {
		this.nom = nom;
		this.description = description;
		this.prix=prix;
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

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	@Override
	public String toString() {
		return "Produit [idProduit=" + idProduit + ", nom=" + nom + ", description=" + description + ", prix=" + prix
				+ "]";
	}

	
	
	
}
