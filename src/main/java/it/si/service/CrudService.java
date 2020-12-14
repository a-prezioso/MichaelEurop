package it.si.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

public class CrudService<T extends CrudRepository<E, Integer>, E>{
	
	@Autowired
	protected T repository;
	
	
	public List<E> getAll(){
		return (List<E>) repository.findAll();
	}
	
	public E getById(Integer id) {
		return repository.findById(id).get();
	}
	
	public void saveOrUpdate(E entity) {
		repository.save(entity);
	}
	
	public void delete (Integer id) {
		repository.deleteById(id);
	}

}
