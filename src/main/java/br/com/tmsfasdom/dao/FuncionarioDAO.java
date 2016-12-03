package br.com.tmsfasdom.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import br.com.tmsfasdom.model.Funcionario;

@Transactional
public interface FuncionarioDAO extends CrudRepository<Funcionario, Long> {
	public Funcionario findByCpf(String cpf);
}
