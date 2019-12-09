package fr.opendevup.entities;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Panier  implements Serializable{
	

	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
	private int idPanier;
	@OneToOne
	private Client client;
	
	
	public Panier() {
		super();
	}
	

}
