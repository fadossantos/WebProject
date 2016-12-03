package br.com.tmsfasdom.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import br.com.tmsfasdom.model.Cidade;
import br.com.tmsfasdom.model.Estado;

@Transactional
public interface CidadeDAO extends CrudRepository<Cidade, Long> {
	public Cidade findByNomeCidade(String cpf);
	public List<Cidade> findByEstado(Estado estado);
}
