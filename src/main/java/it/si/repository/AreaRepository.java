package it.si.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.si.model.Area;

@Repository
public interface AreaRepository extends CrudRepository<Area, Integer> {

	@Query("SELECT a FROM Area a LEFT JOIN FETCH a.sottocategorie s WHERE a.chiaveArea = :id")
	Area findByIdWithList(@Param ("id") Integer id);

}
