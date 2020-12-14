package it.si.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.si.model.Utente;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Integer>{

	Utente findByUsername(String username);
}
