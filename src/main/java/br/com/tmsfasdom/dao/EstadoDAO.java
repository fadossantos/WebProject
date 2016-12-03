package br.com.tmsfasdom.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import br.com.tmsfasdom.model.Estado;

@Transactional
public interface EstadoDAO extends CrudRepository<Estado, Long> {
	public Estado findBySiglaEstado(String sigleEstado);
}
