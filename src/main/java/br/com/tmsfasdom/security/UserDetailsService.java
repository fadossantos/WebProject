package br.com.tmsfasdom.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.tmsfasdom.dao.FuncionarioDAO;
import br.com.tmsfasdom.dao.UserDAO;
import br.com.tmsfasdom.model.Funcionario;
import br.com.tmsfasdom.model.Usuario;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	@Autowired
	private FuncionarioDAO funcionarioRepo;
	
	@Autowired
	private UserDAO usuarioRepo;
	
	private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();

	@Override
	public final Usuario loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario user = usuarioRepo.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("user not found");
		}
		detailsChecker.check(user);
		return user;
	}
}
