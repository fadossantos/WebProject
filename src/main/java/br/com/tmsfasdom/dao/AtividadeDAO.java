package br.com.tmsfasdom.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import br.com.tmsfasdom.model.Agendamento;
import br.com.tmsfasdom.model.Atividade;
import br.com.tmsfasdom.model.Estado;

@Transactional
public interface AtividadeDAO extends CrudRepository<Atividade, Long> {
	public Atividade findByDescAtividade(String descAtividade);
}
