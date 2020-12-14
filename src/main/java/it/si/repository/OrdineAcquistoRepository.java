package it.si.repository;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.si.model.OrdineAcquisto;

@Repository
public interface OrdineAcquistoRepository extends CrudRepository<OrdineAcquisto, Integer>, OrdineAcquistoRepositoryCustom{

	@Query("SELECT o FROM OrdineAcquisto o JOIN FETCH o.dettagli d WHERE o.chiaveOrdineAcquisto = :id")
	OrdineAcquisto getByIdWithDetails(@Param("id") Integer id);
	


}
