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

import br.com.tmsfasdom.dao.CustoInspecaoDAO;
import br.com.tmsfasdom.model.CustoInspecao;

@RestController
@RequestMapping(value = "/api/custoInspecao")
public class ApiCustoInspecaoController {

	@Autowired
	CustoInspecaoDAO custoInspecaoDAO;

	@RequestMapping(value = "/salvar", method = { RequestMethod.POST, RequestMethod.PUT }, produces = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> criar(@RequestBody CustoInspecao custoInspecao) {
		CustoInspecao custoInspecaoSalvo = null;
		try {

			custoInspecaoSalvo = custoInspecaoDAO.findOne(custoInspecao.getIdCustoInspecao());
			if (custoInspecaoSalvo == null) {
				custoInspecaoSalvo = custoInspecaoDAO.save(custoInspecao);
			} else {
				custoInspecaoSalvo.setCustoAprovado(custoInspecao.getCustoAprovado());
				custoInspecaoSalvo.setInspecao(custoInspecao.getInspecao());
				custoInspecaoSalvo.setKmEfetivo(custoInspecao.getKmEfetivo());
				custoInspecaoSalvo.setValorDeslocamento(custoInspecao.getValorDeslocamento());				
				custoInspecaoSalvo = custoInspecaoDAO.save(custoInspecaoSalvo);
			}
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Object>(custoInspecaoSalvo, HttpStatus.OK);
	}

	@RequestMapping(value = "/deletar/{id}", method = RequestMethod.DELETE, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		custoInspecaoDAO.delete(id);
		return new ResponseEntity<Object>("Deletado com sucesso", HttpStatus.OK);
	}

	@RequestMapping(value = "/buscar/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getById(@PathVariable("id") long id) {
		return new ResponseEntity<Object>(custoInspecaoDAO.findOne(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/obtertodos", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getAll() {

		return new ResponseEntity<Object>(custoInspecaoDAO.findAll(), HttpStatus.OK);
	}

}