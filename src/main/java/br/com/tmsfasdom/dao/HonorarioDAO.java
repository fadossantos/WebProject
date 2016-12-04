package br.com.tmsfasdom.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import br.com.tmsfasdom.model.Honorario;

@Transactional
public interface HonorarioDAO extends CrudRepository<Honorario, Long> {
}
