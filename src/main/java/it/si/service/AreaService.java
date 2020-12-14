package it.si.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.si.model.Area;
import it.si.repository.AreaRepository;

@Service
@Transactional
public class AreaService extends CrudService<AreaRepository, Area>{

	public Area getByIdWithList(Integer id) {
		return repository.findByIdWithList(id);
	}
	
}
