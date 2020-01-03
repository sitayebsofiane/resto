package fr.opendevup.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ClientProduitCommande implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue
	private int idClientProduitCommande;
	private int idClient;
	private int idProduit;
	
	private String nomProduit;
	
	private String descriptionProduit;
	
	private double prixProduit;

	public ClientProduitCommande() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClientProduitCommande(int idClient, int idProduit, String nomProduit, String descriptionProduit,
			double prixProduit) {
		this.idClient = idClient;
		this.idProduit = idProduit;
		this.nomProduit = nomProduit;
		this.descriptionProduit = descriptionProduit;
		this.prixProduit = prixProduit;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public int getIdProduit() {
		return idProduit;
	}

	public void setIdProduit(int idProduit) {
		this.idProduit = idProduit;
	}

	public String getNomProduit() {
		return nomProduit;
	}

	public void setNomProduit(String nomProduit) {
		this.nomProduit = nomProduit;
	}

	public String getDescriptionProduit() {
		return descriptionProduit;
	}

	public void setDescriptionProduit(String descriptionProduit) {
		this.descriptionProduit = descriptionProduit;
	}

	public double getPrixProduit() {
		return prixProduit;
	}

	public void setPrixProduit(double prixProduit) {
		this.prixProduit = prixProduit;
	}
	
	public int getIdClientProduitCommande() {
		return idClientProduitCommande;
	}

	public void setIdClientProduitCommande(int idClientProduitCommande) {
		this.idClientProduitCommande = idClientProduitCommande;
	}

	@Override
	public String toString() {
		return "ClientProduitCommande [idClientProduitCommande=" + idClientProduitCommande + ", idClient=" + idClient
				+ ", idProduit=" + idProduit + ", nomProduit=" + nomProduit + ", descriptionProduit="
				+ descriptionProduit + ", prixProduit=" + prixProduit + "]";
	}
	
	
}
