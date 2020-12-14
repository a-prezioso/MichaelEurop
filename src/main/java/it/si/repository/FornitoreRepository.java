package it.si.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.si.model.Fornitore;

@Repository
public interface FornitoreRepository extends CrudRepository<Fornitore, Integer>{

	@Query("SELECT f FROM Fornitore f LEFT JOIN FETCH f.preventivi p WHERE f.chiaveFornitore = :id")
	Fornitore findByIdWithListPreventivi(@Param ("id") Integer id);
	
	@Query("SELECT f FROM Fornitore f LEFT JOIN FETCH f.ordini o WHERE f.chiaveFornitore = :id")
	Fornitore findByIdWithListOrdini(@Param ("id") Integer id);

	@Query("SELECT f FROM Fornitore f LEFT JOIN FETCH f.fatture p WHERE f.chiaveFornitore = :id")
	Fornitore findByIdWithListFatture(Integer id);
}
