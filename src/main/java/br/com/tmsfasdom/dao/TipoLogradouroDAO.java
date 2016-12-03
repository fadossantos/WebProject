package br.com.tmsfasdom.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import br.com.tmsfasdom.model.TipoLogradouro;

@Transactional
public interface TipoLogradouroDAO extends CrudRepository<TipoLogradouro, Long> {
	public TipoLogradouro findByDescTipoLogradouro(String descTipoLogradouro);
}
