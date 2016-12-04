package br.com.tmsfasdom.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import br.com.tmsfasdom.model.Endereco;

@Transactional
public interface EnderecoDAO extends CrudRepository<Endereco, Long> {
}
