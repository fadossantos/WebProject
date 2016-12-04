package br.com.tmsfasdom.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import br.com.tmsfasdom.model.Autorizacao;

@Transactional
public interface AutorizacaoDAO extends CrudRepository<Autorizacao, Long> {
	public Autorizacao findByDescAutorizacao(String descAutorizacao);
}
