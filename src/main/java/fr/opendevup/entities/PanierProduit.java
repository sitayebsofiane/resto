package fr.opendevup.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class PanierProduit implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idPanierProduit;
	private int idClient;
	private int idProduit;
	@Temporal(TemporalType.DATE)
	private Date date;
	private static final long serialVersionUID = 1L;
	public PanierProduit() {
		super();
		this.date=new Date(System.currentTimeMillis()); 
		
	}
	public PanierProduit(int idClient, int idProduit,Date date) {
		this.idClient = idClient;
		this.idProduit = idProduit;
		this.date=date;
	}
	public int getIdPanierProduit() {
		return idPanierProduit;
	}
	public void setIdPanierProduit(int idPanierProduit) {
		this.idPanierProduit = idPanierProduit;
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	

}
