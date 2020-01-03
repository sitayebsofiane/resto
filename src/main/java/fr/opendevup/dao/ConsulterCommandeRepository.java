package fr.opendevup.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.opendevup.entities.ClientProduitCommande;

public interface ConsulterCommandeRepository extends JpaRepository<ClientProduitCommande, Integer> {
	
}
