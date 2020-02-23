package fr.opendevup.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.opendevup.entities.Commande;
@Repository
public interface CommandeRepository extends JpaRepository<Commande, Integer> {
	/**
	 * 
	 * @param mc mot clé sur le nom du client
	 * @param pageable la page actuel
	 * @return les pages avec les element commade non traité
	 */
	@Query("select c from Commande c where c.nomClient like :x and c.statut = 0")
	public Page<Commande> chercherCommandeNonTraiter(@Param("x")String mc,Pageable pageable);
	
	/**
	 * 
	 *@param mc mot clé sur le nom du client
	 *@param pageable la page actuel
	 *@return les pages avec les element commade traité
	 */
	@Query("select c from Commande c where c.nomClient like :x and c.statut = 1")
	public Page<Commande> chercherCommandeTraiter(@Param("x")String mc,Pageable pageable);
	
	
}
