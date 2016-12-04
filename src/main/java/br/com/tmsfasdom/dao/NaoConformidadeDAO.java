package br.com.tmsfasdom.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import br.com.tmsfasdom.model.NaoConformidade;

@Transactional
public interface NaoConformidadeDAO extends CrudRepository<NaoConformidade, Long> {

	public NaoConformidade findByDescNaoConformidade(String descNaoConformidade);
}
