package br.com.tmsfasdom.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import br.com.tmsfasdom.model.Agendamento;

@Transactional
public interface AgendamentoDAO extends CrudRepository<Agendamento, Long> {
}
