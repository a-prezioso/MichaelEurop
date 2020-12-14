package it.si.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import it.si.model.OrdineAcquisto;

public class OrdineAcquistoRepositoryCustomImpl implements OrdineAcquistoRepositoryCustom{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<OrdineAcquisto> ricerca(Integer idFornitore, Integer idProgetto, Integer idSottocategoria) {
		StringBuilder sql = new StringBuilder("SELECT o FROM OrdineAcquisto o ");
		String join = "";
		String where = "";
		
		if(idFornitore!=0) {
			join+= "JOIN o.fornitore f ";
			where+= "WHERE f.chiaveFornitore = "+idFornitore;
		}
		if(idProgetto!=0 || idSottocategoria!=0) {
			if(idProgetto!=0) {
				join+= "JOIN o.dettagli d JOIN d.progetto p ";
				if(idFornitore!=0) {
					where+= " AND p.chiaveProgetto = "+idProgetto;
				} else {
					where+= "WHERE p.chiaveProgetto = "+idProgetto;
				}
				if(idSottocategoria!=0) {
					
					join+= "JOIN o.dettagli d JOIN d.spesaInvestimento i JOIN i.sottocategoria s ";
					where+= " AND s.chiaveSottocategoria = "+idSottocategoria;
				}
			} else {
				join+= "JOIN o.dettagli d JOIN d.spesaInvestimento i JOIN i.sottocategoria s ";
				if(idFornitore!=0) {
					where+= " AND s.chiaveSottocategoria = "+idSottocategoria;
				} else {
					where+= "WHERE s.chiaveSottocategoria = "+idSottocategoria;
				}
			}
		}
		sql.append(join);
		sql.append(where);
		Query query = entityManager.createQuery(sql.toString());
		@SuppressWarnings("unchecked")
		List<OrdineAcquisto> ordini = query.getResultList();
		return ordini;
	}

}
