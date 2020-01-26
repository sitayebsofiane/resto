package fr.opendevup.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Menu implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idMenu;
	
	@NotNull
	@Size(min = 2, max = 30)
	private String nom;
	
	@NotNull
	@Size(min = 2)
	private String description;
	
	private double prix;

	private int quantite;

	public Menu() {
		super();
	}

	public Menu(String nom, String description, double prix, int quantite) {
		this.setNom(nom);
		;
		this.setDescription(description);
		;
		this.setPrix(prix);
		this.setQuantite(quantite);
	}

	public int getIdMenu() {
		return idMenu;
	}

	public void setIdMenu(int id) {
		this.idMenu = id;
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
		return "Menu [idMenu=" + idMenu + ", nom=" + nom + ", description=" + description + ", prix=" + prix
				+ "€" + ", quantité=" + quantite + "]";
	}

}
