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

import br.com.tmsfasdom.dao.HonorarioDAO;
import br.com.tmsfasdom.model.Honorario;

@RestController
@RequestMapping(value = "/api/honorario")
public class ApiHonorarioController {

	@Autowired
	HonorarioDAO honorarioDAO;

	@RequestMapping(value = "/salvar", method = { RequestMethod.POST, RequestMethod.PUT }, produces = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> criar(@RequestBody Honorario honorario) {
		Honorario honorarioSalvo = null;
		try {

			honorarioSalvo = honorarioDAO.findOne(honorario.getIdHonorario());
			if (honorarioSalvo == null) {
				honorarioSalvo = honorarioDAO.save(honorario);
			} else {
				honorarioSalvo.setCpfAlterador(honorario.getCpfAlterador());
				honorarioSalvo.setDescCondicoes(honorario.getDescCondicoes());
				honorarioSalvo.setFlagAlteracao(honorario.getFlagAlteracao());
				honorarioSalvo.setInspecao(honorario.getInspecao());
				honorarioSalvo.setMotivoAlteracao(honorario.getMotivoAlteracao());
				honorarioSalvo.setValorHonorarioCalculado(honorario.getValorHonorarioCalculado());
				honorarioSalvo = honorarioDAO.save(honorarioSalvo);
			}
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Object>(honorarioSalvo, HttpStatus.OK);
	}

	@RequestMapping(value = "/deletar/{id}", method = RequestMethod.DELETE, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		honorarioDAO.delete(id);
		return new ResponseEntity<Object>("Deletado com sucesso", HttpStatus.OK);
	}

	@RequestMapping(value = "/buscar/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getById(@PathVariable("id") long id) {
		return new ResponseEntity<Object>(honorarioDAO.findOne(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/obtertodos", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getAll() {

		return new ResponseEntity<Object>(honorarioDAO.findAll(), HttpStatus.OK);
	}

}