package br.com.tmsfasdom.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import br.com.tmsfasdom.model.Designacao;

@Transactional
public interface DesignacaoDAO extends CrudRepository<Designacao, Long> {
}
