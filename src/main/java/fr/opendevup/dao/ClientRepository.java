package fr.opendevup.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.opendevup.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

}
