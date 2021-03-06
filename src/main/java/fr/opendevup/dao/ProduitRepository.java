package fr.opendevup.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.opendevup.entities.Produit;

public interface ProduitRepository extends JpaRepository<Produit, Integer>{
	@Query("select p from Produit p where p.nom like :x ")
	public Page<Produit> chercher(@Param("x")String mc,Pageable pageable);
}
