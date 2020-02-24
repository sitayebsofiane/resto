package fr.opendevup.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
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
	
	//lien vers la photo
		private String photo;
	
	private double prix;

	private int quantite;

	public Produit() {
		super();
	}


	public Produit(@NotNull @Size(min = 2, max = 30) String nom, @NotNull @Size(min = 2) String description,
			String photo, double prix, int quantite) {
		this.nom = nom;
		this.description = description;
		this.photo = photo;
		this.prix = prix;
		this.quantite = quantite;
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
	
	public String getPhoto() {
		return photo;
	}



	public void setPhoto(String photo) {
		this.photo = photo;
	}



	@Override
	public String toString() {
		return "Produit [idProduit=" + idProduit + ", nom=" + nom + ", description=" + description + ", prix=" + prix
				+ "€" + ", quantité=" + quantite + "]";
	}

}
