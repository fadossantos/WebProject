package br.com.tmsfasdom.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import br.com.tmsfasdom.model.Revisao;

@Transactional
public interface RevisaoDAO extends CrudRepository<Revisao, Long> {
}
