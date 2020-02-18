package fr.opendevup.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.opendevup.entities.Commande;
@Repository
public interface CommandeRepository extends JpaRepository<Commande, Integer> {
	@Query("select c from Commande c where c.nomClient like :x and c.statut = 0")
	public Page<Commande> chercher(@Param("x")String mc,Pageable pageable);
	
	@Modifying
	@Query("UPDATE Commande c SET c.statut = 1 WHERE c.idCommande = :x")
	public void changerStatut(@Param("x")int idCommande);
}
