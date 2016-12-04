package br.com.tmsfasdom.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import br.com.tmsfasdom.model.Honorario;
import br.com.tmsfasdom.model.InsercaoSistema;
import br.com.tmsfasdom.model.Inspecao;

@Transactional
public interface InspecaoDAO extends CrudRepository<Inspecao, Long> {
}
