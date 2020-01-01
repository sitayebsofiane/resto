package fr.opendevup.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.opendevup.entities.Commande;

public interface CommandeRepository extends JpaRepository<Commande, Integer> {
	
}
