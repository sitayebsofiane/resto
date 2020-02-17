package fr.opendevup.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import fr.opendevup.entities.Commande;

public interface CommandeRepository extends JpaRepository<Commande, Integer> {
	@Query("select c from Commande c where c.nomClient like :x and c.statut = 0")
	public Page<Commande> chercher(@Param("x")String mc,Pageable pageable);

	@Query("update Commande c set  c.statut = 1 where c.idClient = :x ")
	public void changerStatut(@Param("x")int idClient);
}
