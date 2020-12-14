package it.si.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.si.model.Fornitore;
import it.si.model.Preventivo;
import it.si.repository.PreventivoRepository;

@Service
@Transactional
public class PreventivoService extends CrudService<PreventivoRepository, Preventivo>{

	public List<Preventivo> getByFornitore(Fornitore fornitore) {
		List<Preventivo> preventivi;
		if(fornitore!=null) {
			preventivi = repository.getByFornitore(fornitore.getChiaveFornitore());
		} else {
			preventivi = new ArrayList<Preventivo>();
		}
		return preventivi;
	}

}
