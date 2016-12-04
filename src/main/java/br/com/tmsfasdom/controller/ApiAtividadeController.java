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

import br.com.tmsfasdom.dao.AtividadeDAO;
import br.com.tmsfasdom.model.Atividade;

@RestController
@RequestMapping(value = "/api/atividade")
public class ApiAtividadeController {

	@Autowired
	AtividadeDAO atividadeDAO;

	@RequestMapping(value = "/salvar", method = { RequestMethod.POST, RequestMethod.PUT }, produces = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> criar(@RequestBody Atividade atividade) {
		Atividade atividadeSalvo = null;
		try {
			atividadeSalvo = atividadeDAO.findByDescAtividade(atividade.getDescAtividade());
			if (atividadeSalvo == null) {
				atividadeSalvo = atividadeDAO.save(atividade);
			} else {
				atividadeSalvo.setDescAtividade(atividade.getDescAtividade());
				atividadeSalvo = atividadeDAO.save(atividadeSalvo);
			}
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Object>(atividadeSalvo, HttpStatus.OK);
	}

	@RequestMapping(value = "/deletar/{id}", method = RequestMethod.DELETE, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		atividadeDAO.delete(id);
		return new ResponseEntity<Object>("Deletado com sucesso", HttpStatus.OK);
	}

	@RequestMapping(value = "/buscar/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getById(@PathVariable("id") long id) {
		return new ResponseEntity<Object>(atividadeDAO.findOne(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/buscarpornome/{nome}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getBySigla(@PathVariable("nome") String nome) {
		return new ResponseEntity<Object>(atividadeDAO.findByDescAtividade(nome), HttpStatus.OK);
	}

	@RequestMapping(value = "/obtertodos", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getAll() {

		return new ResponseEntity<Object>(atividadeDAO.findAll(), HttpStatus.OK);
	}

}