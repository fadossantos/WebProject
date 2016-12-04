package br.com.tmsfasdom.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import br.com.tmsfasdom.model.Relatorio;

@Transactional
public interface RelatorioDAO extends CrudRepository<Relatorio, Long> {
}
