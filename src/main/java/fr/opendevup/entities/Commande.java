package fr.opendevup.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Commande implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue
	private int idCommande;
	
	@Temporal(TemporalType.DATE)
	private Date date;
	private double prixTotal;
	
	private int idClient;
	private String nomClient;
	private String adresseClient;
	private String telephoneClient;
	private String Produits;
	
	public Commande() {
		super();
	}
	
	public Commande(Date date, double prixTotal, int idClient,String nomClient,String Produits,String adresseClient,String telephoneClient) {
		this.date = date;
		this.setPrixTotal(prixTotal);
		this.idClient = idClient;
		this.nomClient=nomClient;
		this.Produits=Produits;
		this.setAdresseClient(adresseClient);
		this.setTelephoneClient(telephoneClient);
	}
	
	public int getIdCommande() {
		return idCommande;
	}
	public void setIdCommande(int idCommande) {
		this.idCommande = idCommande;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public int getIdClient() {
		return idClient;
	}
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public double getPrixTotal() {
		return prixTotal;
	}

	public void setPrixTotal(double prixTotal) {
		this.prixTotal = prixTotal;
	}

	@Override
	public String toString() {
		return "Commande [idCommande=" + idCommande + ", date=" + date + ", prixTotal=" + prixTotal + ", idClient="
				+ idClient + "]";
	}

	public String getNomClient() {
		return nomClient;
	}

	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}

	public String getNomProduit() {
		return Produits;
	}

	public void setNomProduit(String Produits) {
		this.Produits = Produits;
	}

	public String getAdresseClient() {
		return adresseClient;
	}

	public void setAdresseClient(String adresseClient) {
		this.adresseClient = adresseClient;
	}

	public String getTelephoneClient() {
		return telephoneClient;
	}

	public void setTelephoneClient(String telephoneClient) {
		this.telephoneClient = telephoneClient;
	}
	
}
