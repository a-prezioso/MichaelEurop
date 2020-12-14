package it.si.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.si.model.FatturaPassiva;
import it.si.repository.FatturaPassivaRepository;

@Service
@Transactional
public class FatturaPassivaService extends CrudService<FatturaPassivaRepository, FatturaPassiva>{

	public FatturaPassiva getByIdWithDetails(Integer id) {
		return repository.getByIdWithDetails(id);
	}

}
