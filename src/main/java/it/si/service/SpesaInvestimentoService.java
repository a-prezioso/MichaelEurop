package it.si.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.si.model.SpesaInvestimento;
import it.si.repository.SpesaInvestimentoRepository;

@Service
@Transactional
public class SpesaInvestimentoService extends CrudService<SpesaInvestimentoRepository, SpesaInvestimento>{

}
