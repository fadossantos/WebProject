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

import br.com.tmsfasdom.dao.Inspecao_CoberturaDAO;
import br.com.tmsfasdom.model.Inspecao_Cobertura;

@RestController
@RequestMapping(value = "/api/inspecao_Cobertura")
public class ApiInspecaoCoberturaController {

	@Autowired
	Inspecao_CoberturaDAO inspecao_CoberturaDAO;

	@RequestMapping(value = "/salvar", method = { RequestMethod.POST, RequestMethod.PUT }, produces = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> criar(@RequestBody Inspecao_Cobertura inspecao_Cobertura) {
		Inspecao_Cobertura inspecao_CoberturaSalvo = null;
		try {

			inspecao_CoberturaSalvo = inspecao_CoberturaDAO.findOne(inspecao_Cobertura.getIdInspecaoCobertura());
			if (inspecao_CoberturaSalvo == null) {
				inspecao_CoberturaSalvo = inspecao_CoberturaDAO.save(inspecao_Cobertura);
			} else {
				inspecao_CoberturaSalvo.setCobertura(inspecao_Cobertura.getCobertura());
				inspecao_CoberturaSalvo.setFranquia(inspecao_Cobertura.getFranquia());
				inspecao_CoberturaSalvo.setInspecao(inspecao_Cobertura.getInspecao());
				inspecao_CoberturaSalvo.setLimiteMaxRisco(inspecao_Cobertura.getLimiteMaxRisco());
				inspecao_CoberturaSalvo.setPos(inspecao_Cobertura.getPos());
				inspecao_CoberturaSalvo.setVr(inspecao_Cobertura.getVr());
				inspecao_CoberturaSalvo = inspecao_CoberturaDAO.save(inspecao_CoberturaSalvo);
			}
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Object>(inspecao_CoberturaSalvo, HttpStatus.OK);
	}

	@RequestMapping(value = "/deletar/{id}", method = RequestMethod.DELETE, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		inspecao_CoberturaDAO.delete(id);
		return new ResponseEntity<Object>("Deletado com sucesso", HttpStatus.OK);
	}

	@RequestMapping(value = "/buscar/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getById(@PathVariable("id") long id) {
		return new ResponseEntity<Object>(inspecao_CoberturaDAO.findOne(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/obtertodos", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getAll() {

		return new ResponseEntity<Object>(inspecao_CoberturaDAO.findAll(), HttpStatus.OK);
	}

}