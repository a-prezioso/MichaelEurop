package it.si.repository;

import java.util.List;

import it.si.model.OrdineAcquisto;

public interface OrdineAcquistoRepositoryCustom {
	
	List<OrdineAcquisto> ricerca(Integer idFornitore, Integer idProgetto, Integer idSottocategoria);

}
