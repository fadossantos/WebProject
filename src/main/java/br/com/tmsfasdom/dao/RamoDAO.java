package br.com.tmsfasdom.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import br.com.tmsfasdom.model.Ramo;

@Transactional
public interface RamoDAO extends CrudRepository<Ramo, Long> {

	public Ramo findByDescRamos(String descRamos);
}
