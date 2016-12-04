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

import br.com.tmsfasdom.dao.InsercaoSistemaDAO;
import br.com.tmsfasdom.model.InsercaoSistema;

@RestController
@RequestMapping(value = "/api/insercaoSistema")
public class ApiInsercaoSistemaController {

	@Autowired
	InsercaoSistemaDAO insercaoSistemaDAO;

	@RequestMapping(value = "/salvar", method = { RequestMethod.POST, RequestMethod.PUT }, produces = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> criar(@RequestBody InsercaoSistema insercaoSistema) {
		InsercaoSistema insercaoSistemaSalvo = null;
		try {

			insercaoSistemaSalvo = insercaoSistemaDAO.findOne(insercaoSistema.getIdInsercaoSistema());
			if (insercaoSistemaSalvo == null) {
				insercaoSistemaSalvo = insercaoSistemaDAO.save(insercaoSistema);
			} else {
				insercaoSistemaSalvo.setAprovacaoSemRessalvas(insercaoSistema.getAprovacaoSemRessalvas());
				insercaoSistemaSalvo.setDataHoraAprovacao(insercaoSistema.getDataHoraAprovacao());
				insercaoSistemaSalvo.setDataHoraInsercao(insercaoSistema.getDataHoraInsercao());
				insercaoSistemaSalvo.setFuncionarioResponsavelInsercao(insercaoSistema.getFuncionarioResponsavelInsercao());
				insercaoSistemaSalvo.setFuncionarioSupervisor(insercaoSistema.getFuncionarioSupervisor());
				insercaoSistemaSalvo.setInspecao(insercaoSistema.getInspecao());
				insercaoSistemaSalvo = insercaoSistemaDAO.save(insercaoSistemaSalvo);
			}
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Object>(insercaoSistemaSalvo, HttpStatus.OK);
	}

	@RequestMapping(value = "/deletar/{id}", method = RequestMethod.DELETE, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		insercaoSistemaDAO.delete(id);
		return new ResponseEntity<Object>("Deletado com sucesso", HttpStatus.OK);
	}

	@RequestMapping(value = "/buscar/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getById(@PathVariable("id") long id) {
		return new ResponseEntity<Object>(insercaoSistemaDAO.findOne(id), HttpStatus.OK);
	}

	
	@RequestMapping(value = "/obtertodos", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getAll() {

		return new ResponseEntity<Object>(insercaoSistemaDAO.findAll(), HttpStatus.OK);
	}

}