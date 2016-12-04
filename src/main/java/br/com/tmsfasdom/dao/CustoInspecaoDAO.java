package br.com.tmsfasdom.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import br.com.tmsfasdom.model.CustoInspecao;

@Transactional
public interface CustoInspecaoDAO extends CrudRepository<CustoInspecao, Long> {
}
