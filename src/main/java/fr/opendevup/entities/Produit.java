package fr.opendevup.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
@Entity
public class Produit implements Serializable{

	
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
	private int idProduit;
	private String nom;
	private String description;
	private Tarif tarif;
	@JoinColumn
	private int IdCommande;
	
	
	public Produit() {
		super();
	}
	
	public Produit(String nom, String description, Tarif tarif, int idCommande) {
		this.nom = nom;
		this.description = description;
		this.tarif = tarif;
		IdCommande = idCommande;
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
	public Tarif getTarif() {
		return tarif;
	}
	public void setTarif(Tarif tarif) {
		this.tarif = tarif;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getIdCommande() {
		return IdCommande;
	}
	public void setIdCommande(int idCommande) {
		IdCommande = idCommande;
	}
	
	
	
}
