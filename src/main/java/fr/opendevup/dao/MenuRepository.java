package fr.opendevup.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.opendevup.entities.Menu;

public interface MenuRepository extends JpaRepository<Menu, Integer>{
	@Query("select m from Menu m where m.nom like :x ")
	public Page<Menu> chercher(@Param("x")String mc,Pageable pageable);
}
