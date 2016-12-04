package br.com.tmsfasdom.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import br.com.tmsfasdom.model.Cobertura;

@Transactional
public interface CoberturaDAO extends CrudRepository<Cobertura, Long> {
	public Cobertura findByDescCobertura(String descCobertura);
}
