package it.si.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.si.model.Fornitore;
import it.si.repository.FornitoreRepository;

@Service
@Transactional
public class FornitoreService extends CrudService<FornitoreRepository, Fornitore>{

	public Fornitore getByIdWithListPreventivi(Integer id) {
		return repository.findByIdWithListPreventivi(id);
	}
	
	public Fornitore getByIdWithListOrdini(Integer id) {
		return repository.findByIdWithListOrdini(id);
	}

	public Fornitore getByIdWithListFatture(Integer id) {
		return repository.findByIdWithListFatture(id);
	}

}
