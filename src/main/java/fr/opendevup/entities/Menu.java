package fr.opendevup.entities;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "idProduit")
public class Menu extends Produit {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
}
