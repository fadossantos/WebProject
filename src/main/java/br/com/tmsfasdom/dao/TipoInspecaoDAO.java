package br.com.tmsfasdom.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import br.com.tmsfasdom.model.TipoInspecao;

@Transactional
public interface TipoInspecaoDAO extends CrudRepository<TipoInspecao, Long> {

	public TipoInspecao findByDescTipoInspecao(String descTipoInspecao);
}
