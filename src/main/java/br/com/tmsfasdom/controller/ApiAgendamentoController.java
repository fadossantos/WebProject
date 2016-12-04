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

import br.com.tmsfasdom.dao.AgendamentoDAO;
import br.com.tmsfasdom.model.Agendamento;

@RestController
@RequestMapping(value = "/api/agendamento")
public class ApiAgendamentoController {

	@Autowired
	AgendamentoDAO agendamentoDAO;

	@RequestMapping(value = "/salvar", method = { RequestMethod.POST, RequestMethod.PUT }, produces = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> criar(@RequestBody Agendamento agendamento) {
		Agendamento agendamentoSalvo = null;
		try {
			agendamentoSalvo = agendamentoDAO.findOne(agendamento.getIdAgendamento());
			if (agendamentoSalvo == null) {
				agendamentoSalvo = agendamentoDAO.save(agendamento);
			} else {
				agendamentoSalvo.setConfirmacao(agendamento.getConfirmacao());
				agendamentoSalvo.setCpfAgendador(agendamento.getCpfAgendador());
				agendamentoSalvo.setDtAgendada(agendamento.getDtAgendada());
				agendamentoSalvo.setDtAgendamento(agendamento.getDtAgendamento());
				agendamentoSalvo.setDtConfirmacao(agendamento.getDtConfirmacao());
				agendamentoSalvo.setInspecao(agendamento.getInspecao());
				agendamentoSalvo = agendamentoDAO.save(agendamentoSalvo);
			}
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Object>(agendamentoSalvo, HttpStatus.OK);
	}

	@RequestMapping(value = "/deletar/{id}", method = RequestMethod.DELETE, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		agendamentoDAO.delete(id);
		return new ResponseEntity<Object>("Deletado com sucesso", HttpStatus.OK);
	}

	@RequestMapping(value = "/buscar/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getById(@PathVariable("id") long id) {
		return new ResponseEntity<Object>(agendamentoDAO.findOne(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/obtertodos", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getAll() {

		return new ResponseEntity<Object>(agendamentoDAO.findAll(), HttpStatus.OK);
	}

}