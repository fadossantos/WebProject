package br.com.tmsfasdom.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import br.com.tmsfasdom.model.Vistoria;

@Transactional
public interface VistoriaDAO extends CrudRepository<Vistoria, Long> {
}
