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

import br.com.tmsfasdom.dao.DesignacaoDAO;
import br.com.tmsfasdom.model.Designacao;

@RestController
@RequestMapping(value = "/api/designacao")
public class ApiDesignacaoController {

	@Autowired
	DesignacaoDAO designacaoDAO;

	@RequestMapping(value = "/salvar", method = { RequestMethod.POST, RequestMethod.PUT }, produces = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> criar(@RequestBody Designacao designacao) {
		Designacao designacaoSalvo = null;
		try {

			designacaoSalvo = designacaoDAO.findOne(designacao.getIdDesignacao());
			if (designacaoSalvo == null) {
				designacaoSalvo = designacaoDAO.save(designacao);
			} else {
				designacaoSalvo.setDataDesignacao(designacao.getDataDesignacao());
				designacaoSalvo.setDataDivulgacao(designacao.getDataDivulgacao());
				designacaoSalvo.setFuncionario(designacao.getFuncionario());
				designacaoSalvo.setInspecao(designacao.getInspecao());
				designacaoSalvo = designacaoDAO.save(designacaoSalvo);
			}
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Object>(designacaoSalvo, HttpStatus.OK);
	}

	@RequestMapping(value = "/deletar/{id}", method = RequestMethod.DELETE, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		designacaoDAO.delete(id);
		return new ResponseEntity<Object>("Deletado com sucesso", HttpStatus.OK);
	}

	@RequestMapping(value = "/buscar/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getById(@PathVariable("id") long id) {
		return new ResponseEntity<Object>(designacaoDAO.findOne(id), HttpStatus.OK);
	}

	
	@RequestMapping(value = "/obtertodos", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getAll() {

		return new ResponseEntity<Object>(designacaoDAO.findAll(), HttpStatus.OK);
	}

}