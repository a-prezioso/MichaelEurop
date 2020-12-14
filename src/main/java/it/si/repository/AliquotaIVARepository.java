package it.si.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.si.model.AliquotaIVA;

@Repository
public interface AliquotaIVARepository extends CrudRepository<AliquotaIVA, Integer>{

}
