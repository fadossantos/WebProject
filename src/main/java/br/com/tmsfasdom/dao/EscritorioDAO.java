package br.com.tmsfasdom.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import br.com.tmsfasdom.model.Escritorio;

@Transactional
public interface EscritorioDAO extends CrudRepository<Escritorio, Long> {
	public Escritorio findByDescEscritorio(String descEscritorio);
}
