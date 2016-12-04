package br.com.tmsfasdom.dao;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import br.com.tmsfasdom.model.Segurado;

@Transactional
public interface SeguradoDAO extends CrudRepository<Segurado, Long> {

	public Segurado findByCpfCnpj(BigDecimal cpfCnpj);
}
