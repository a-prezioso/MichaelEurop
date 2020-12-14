package it.si.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.si.model.SpesaInvestimento;

@Repository
public interface SpesaInvestimentoRepository extends CrudRepository<SpesaInvestimento, Integer>{

}
