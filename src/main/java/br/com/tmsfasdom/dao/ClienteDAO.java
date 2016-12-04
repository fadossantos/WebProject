package br.com.tmsfasdom.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import br.com.tmsfasdom.model.Agendamento;
import br.com.tmsfasdom.model.Cliente;

@Transactional
public interface ClienteDAO extends CrudRepository<Cliente, Long> {
	public Cliente findByNomeCliente(String nomeCliente);
}
