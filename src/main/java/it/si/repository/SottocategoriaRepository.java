package it.si.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.si.model.Sottocategoria;

@Repository
public interface SottocategoriaRepository extends CrudRepository<Sottocategoria, Integer>{

	@Query("SELECT s FROM Sottocategoria s LEFT JOIN FETCH s.speseInvestimento i WHERE s.chiaveSottocategoria = :id")
	Sottocategoria findByIdWithList(@Param ("id") Integer id);
	
}
