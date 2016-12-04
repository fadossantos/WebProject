package br.com.tmsfasdom.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import br.com.tmsfasdom.model.Gravidade;

@Transactional
public interface GravidadeDAO extends CrudRepository<Gravidade, Long> {
	public Gravidade findByDescGravidade(String descGravidade);
}
