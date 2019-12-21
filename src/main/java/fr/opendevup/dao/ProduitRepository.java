package fr.opendevup.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.opendevup.entities.Produit;

public interface ProduitRepository extends JpaRepository<Produit, Integer>{

}
