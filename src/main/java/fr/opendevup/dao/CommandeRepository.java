package fr.opendevup.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import fr.opendevup.entities.Commande;

public interface CommandeRepository extends JpaRepository<Commande, Integer> {
	@Query("select c from Commande c where c.nomClient like :x")
	public Page<Commande> chercher(@Param("x")String mc,Pageable pageable);
}
