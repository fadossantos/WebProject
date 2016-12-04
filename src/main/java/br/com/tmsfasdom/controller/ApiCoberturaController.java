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

import br.com.tmsfasdom.dao.CoberturaDAO;
import br.com.tmsfasdom.model.Cobertura;

@RestController
@RequestMapping(value = "/api/cobertura")
public class ApiCoberturaController {

	@Autowired
	CoberturaDAO coberturaDAO;

	@RequestMapping(value = "/salvar", method = { RequestMethod.POST, RequestMethod.PUT }, produces = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> criar(@RequestBody Cobertura cobertura) {
		Cobertura coberturaSalvo = null;
		try {

			coberturaSalvo = coberturaDAO.findByDescCobertura(cobertura.getDescCobertura());
			if (coberturaSalvo == null) {
				coberturaSalvo = coberturaDAO.save(cobertura);
			} else {
				coberturaSalvo.setDescCobertura(cobertura.getDescCobertura());
				coberturaSalvo.setInspecaoCoberturas(cobertura.getInspecaoCoberturas());
				coberturaSalvo = coberturaDAO.save(coberturaSalvo);
			}
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Object>(coberturaSalvo, HttpStatus.OK);
	}

	@RequestMapping(value = "/deletar/{id}", method = RequestMethod.DELETE, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		coberturaDAO.delete(id);
		return new ResponseEntity<Object>("Deletado com sucesso", HttpStatus.OK);
	}

	@RequestMapping(value = "/buscar/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getById(@PathVariable("id") long id) {
		return new ResponseEntity<Object>(coberturaDAO.findOne(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/buscarpornome/{nome}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getBySigla(@PathVariable("nome") String nome) {
		return new ResponseEntity<Object>(coberturaDAO.findByDescCobertura(nome), HttpStatus.OK);
	}

	@RequestMapping(value = "/obtertodos", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getAll() {

		return new ResponseEntity<Object>(coberturaDAO.findAll(), HttpStatus.OK);
	}

}