package br.com.tmsfasdom.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import br.com.tmsfasdom.model.Usuario;

@Transactional
public interface UserDAO extends CrudRepository<Usuario, Long> {
	public Usuario findByUsername(String username);
}
