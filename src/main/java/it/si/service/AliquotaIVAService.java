package it.si.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.si.model.AliquotaIVA;
import it.si.repository.AliquotaIVARepository;

@Service
@Transactional
public class AliquotaIVAService extends CrudService<AliquotaIVARepository, AliquotaIVA>{

}
