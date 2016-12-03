 package br.com.tmsfasdom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.tmsfasdom.dao.FuncionarioDAO;
import br.com.tmsfasdom.model.Funcionario;

@RestController
@RequestMapping(value = "/api/funcionario")
public class ApiFuncionarioController {

	@Autowired
	FuncionarioDAO funcionarioDAO;

	@RequestMapping(value = "/salvar", method = { RequestMethod.POST, RequestMethod.PUT }, produces = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> criar(@RequestBody Funcionario funcionario) {
		Funcionario funcionarioSalvo = null;
		try {

			funcionarioSalvo = funcionarioDAO.findByCpf(funcionario.getCpf());
			if (funcionarioSalvo == null) {
				funcionarioSalvo = funcionarioDAO.save(funcionario);
			} else {
				funcionarioSalvo.setNomeFuncionario(funcionario.getNomeFuncionario());
				funcionarioSalvo.setCpf(funcionario.getCpf());
				funcionarioSalvo.setAtivo(funcionario.getAtivo());
				funcionarioSalvo.setEmail(funcionario.getEmail());
				funcionarioSalvo.setEscritorio(funcionario.getEscritorio());
				funcionarioSalvo.setNumeroMatricula(funcionario.getNumeroMatricula());
				funcionarioSalvo.setUsuario(funcionario.getUsuario());
				funcionarioSalvo = funcionarioDAO.save(funcionarioSalvo);
			}
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Object>(funcionarioSalvo, HttpStatus.OK);
	}

	@RequestMapping(value = "/deletar/{id}", method = RequestMethod.DELETE, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		funcionarioDAO.delete(id);
		return new ResponseEntity<Object>("Deletado com sucesso", HttpStatus.OK);
	}

	@RequestMapping(value = "/buscar/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getById(@PathVariable("id") long id) {
		return new ResponseEntity<Object>(funcionarioDAO.findOne(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/buscarpornome/{nome}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getBySigla(@PathVariable("nome") String nome) {
		return new ResponseEntity<Object>(funcionarioDAO.findByCpf(nome), HttpStatus.OK);
	}

	@RequestMapping(value = "/obtertodos", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getAll() {

		return new ResponseEntity<Object>(funcionarioDAO.findAll(), HttpStatus.OK);
	}

}