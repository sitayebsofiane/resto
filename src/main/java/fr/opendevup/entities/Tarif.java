package fr.opendevup.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Tarif implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
	private int idTarif;
	private double prix;
	
	

	public Tarif() {
		super();
	}

	public Tarif(double prix) {
		this.setPrix(prix);
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}
	
	
	

}
