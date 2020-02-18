package fr.opendevup.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.opendevup.entities.ClientProduitCommande;
@Repository
public interface ConsulterCommandeRepository extends JpaRepository<ClientProduitCommande, Integer> {
	
	
}
