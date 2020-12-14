package it.si.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.si.model.FatturaPassiva;

@Repository
public interface FatturaPassivaRepository extends CrudRepository<FatturaPassiva, Integer>{

	@Query("SELECT f FROM FatturaPassiva f JOIN FETCH f.dettagli d WHERE f.chiaveFatturaPassiva = :id")
	FatturaPassiva getByIdWithDetails(@Param("id") Integer id);
}
