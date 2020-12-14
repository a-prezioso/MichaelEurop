package it.si.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.si.model.Preventivo;

@Repository
public interface PreventivoRepository extends CrudRepository<Preventivo, Integer>{

	@Query("SELECT p FROM Preventivo p JOIN p.fornitore f WHERE f.chiaveFornitore = :chiaveFornitore")
	List<Preventivo> getByFornitore(@Param("chiaveFornitore") Integer chiaveFornitore);

}
