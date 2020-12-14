package it.si.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.si.model.OrdineAcquisto;
import it.si.repository.OrdineAcquistoRepository;

@Service
@Transactional
public class OrdineAcquistoService extends CrudService<OrdineAcquistoRepository, OrdineAcquisto>{

	public OrdineAcquisto getByIdWithDetails(Integer id) {
		return repository.getByIdWithDetails(id);
	}

	public List<OrdineAcquisto> ricercaOrdini(Integer idFornitore, Integer idProgetto, Integer idSottocategoria){
		return repository.ricerca(idFornitore, idProgetto, idSottocategoria);
	}
	
	
	
}
