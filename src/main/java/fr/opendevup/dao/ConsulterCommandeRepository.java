package fr.opendevup.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.opendevup.entities.ClientProduitCommande;

public interface ConsulterCommandeRepository extends JpaRepository<ClientProduitCommande, Integer> {
	@Query("update ClientProduitCommande c set  c.statut = 1 where c.idClient = :x ")
	public void changerStatut(@Param("x")int idClient);
}
