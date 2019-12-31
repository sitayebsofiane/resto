package fr.opendevup.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.opendevup.entities.PanierProduit;

public interface PanierProduitRepository extends JpaRepository<PanierProduit, Integer>{
	
}
