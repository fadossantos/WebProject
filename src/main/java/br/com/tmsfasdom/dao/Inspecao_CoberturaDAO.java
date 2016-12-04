package br.com.tmsfasdom.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import br.com.tmsfasdom.model.Honorario;
import br.com.tmsfasdom.model.InsercaoSistema;
import br.com.tmsfasdom.model.Inspecao_Cobertura;

@Transactional
public interface Inspecao_CoberturaDAO extends CrudRepository<Inspecao_Cobertura, Long> {
}
