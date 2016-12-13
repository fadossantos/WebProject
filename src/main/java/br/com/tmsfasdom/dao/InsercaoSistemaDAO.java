package br.com.tmsfasdom.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import br.com.tmsfasdom.model.InsercaoSistema;

@Transactional
public interface InsercaoSistemaDAO extends CrudRepository<InsercaoSistema, Long> {
}
