package fr.opendevup.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.opendevup.entities.ClientProduitCommande;
@Repository
public interface ConsulterCommandeRepository extends JpaRepository<ClientProduitCommande, Integer> {
	
	@Modifying
	@Query("UPDATE ClientProduitCommande c SET c.statut = 1 WHERE c.idClientProduitCommande = :x")
	public void changerStatut(@Param("x")int idClientProduitCommande);
}
