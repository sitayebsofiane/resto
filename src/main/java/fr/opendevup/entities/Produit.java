package fr.opendevup.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Produit implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idProduit;
	
	@NotNull
	@Size(min = 2, max = 30)
	private String nom;
	
	@NotNull
	@Size(min = 2)
	private String description;
	
	private double prix;

	private int quantite;

	public Produit() {
		super();
	}

	public Produit(String nom, String description, double prix, int quantite) {
		this.setNom(nom);
		;
		this.setDescription(description);
		;
		this.setPrix(prix);
		this.setQuantite(quantite);
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

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	@Override
	public String toString() {
		return "Produit [idProduit=" + idProduit + ", nom=" + nom + ", description=" + description + ", prix=" + prix
				+ "€" + ", quantité=" + quantite + "]";
	}

}
