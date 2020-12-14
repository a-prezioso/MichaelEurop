package it.si.service;


import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.si.model.OrdineAcquistoDettaglio;
import it.si.model.Sottocategoria;
import it.si.model.SpesaInvestimento;
import it.si.repository.SottocategoriaRepository;

@Service
@Transactional
public class SottocategoriaService extends CrudService<SottocategoriaRepository, Sottocategoria>{

	public Sottocategoria updateBudget(final Sottocategoria sottocategoria) {
		
		Sottocategoria existingSottocategoria = repository.findById(sottocategoria.getChiaveSottocategoria()).get();
		
		existingSottocategoria.setBudget(sottocategoria.getBudget());
		return repository.save(existingSottocategoria);
	}

	public Sottocategoria getByIdWithList(Integer id) {
		return repository.findByIdWithList(id);
	}

	public void riconciliazione(Date dataInizio, Date dataFine) {
		List<Sottocategoria> sottocategorie = getAll();
		boolean modificato;
		for(Sottocategoria currentSottocategoria : sottocategorie) {
			
			currentSottocategoria.setBudgetSpeso(0.0);
			List<SpesaInvestimento> spese = currentSottocategoria.getSpeseInvestimento();
			modificato = false;
			
			for(SpesaInvestimento currentSpesa : spese) {
				
				List<OrdineAcquistoDettaglio> dettagli = currentSpesa.getOrdineDettagli();
				
				for(OrdineAcquistoDettaglio currentDettaglio : dettagli) {
					
					Date dataOrdine = currentDettaglio.getOrdineAcquisto().getData();
					
					if(dataOrdine.compareTo(dataInizio) >= 0 && dataOrdine.compareTo(dataFine) <=0) {
						
						currentSottocategoria.setBudgetSpeso(currentSottocategoria.getBudgetSpeso() + currentDettaglio.getQuantita() * currentDettaglio.getImportoUnitario());
						modificato = true;
					}
						
				}
			}
			if(modificato)
				saveOrUpdate(currentSottocategoria);
		}
		
	}

}
