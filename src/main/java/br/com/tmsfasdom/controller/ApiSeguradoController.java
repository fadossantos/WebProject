 package br.com.tmsfasdom.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.tmsfasdom.dao.SeguradoDAO;
import br.com.tmsfasdom.model.Segurado;

@RestController
@RequestMapping(value = "/api/segurado")
public class ApiSeguradoController {

	@Autowired
	SeguradoDAO seguradoDAO;

	@RequestMapping(value = "/salvar", method = { RequestMethod.POST, RequestMethod.PUT }, produces = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> criar(@RequestBody Segurado segurado) {
		Segurado seguradoSalvo = null;
		try {

			seguradoSalvo = seguradoDAO.findByCpfCnpj(segurado.getCpfCnpj());
			if (seguradoSalvo == null) {
				seguradoSalvo = seguradoDAO.save(segurado);
			} else {
				seguradoSalvo.setCpfCnpj(segurado.getCpfCnpj());
				seguradoSalvo.setInspecaos(segurado.getInspecaos());
				seguradoSalvo.setNomeSegurado(segurado.getNomeSegurado());
				seguradoSalvo = seguradoDAO.save(seguradoSalvo);
			}
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Object>(seguradoSalvo, HttpStatus.OK);
	}

	@RequestMapping(value = "/deletar/{id}", method = RequestMethod.DELETE, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		seguradoDAO.delete(id);
		return new ResponseEntity<Object>("Deletado com sucesso", HttpStatus.OK);
	}

	@RequestMapping(value = "/buscar/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getById(@PathVariable("id") long id) {
		return new ResponseEntity<Object>(seguradoDAO.findOne(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/buscarpornome/{cpfcnpj}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getBySigla(@PathVariable("cpfcnpj") BigDecimal cpfcnpj) {
		return new ResponseEntity<Object>(seguradoDAO.findByCpfCnpj(cpfcnpj), HttpStatus.OK);
	}

	@RequestMapping(value = "/obtertodos", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getAll() {

		return new ResponseEntity<Object>(seguradoDAO.findAll(), HttpStatus.OK);
	}

}