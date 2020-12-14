package it.si.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.si.model.Progetto;
import it.si.repository.ProgettoRepository;

@Service
@Transactional
public class ProgettoService extends CrudService<ProgettoRepository, Progetto>{

}
