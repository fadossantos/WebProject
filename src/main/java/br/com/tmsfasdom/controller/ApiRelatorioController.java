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

import br.com.tmsfasdom.dao.RelatorioDAO;
import br.com.tmsfasdom.model.Relatorio;

@RestController
@RequestMapping(value = "/api/relatorio")
public class ApiRelatorioController {

	@Autowired
	RelatorioDAO relatorioDAO;

	@RequestMapping(value = "/salvar", method = { RequestMethod.POST, RequestMethod.PUT }, produces = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> criar(@RequestBody Relatorio relatorio) {
		Relatorio relatorioSalvo = null;
		try {

			relatorioSalvo = relatorioDAO.findOne(relatorio.getIdRelatorio());
			if (relatorioSalvo == null) {
				relatorioSalvo = relatorioDAO.save(relatorio);
			} else {
				relatorioSalvo.setDataRelatorio(relatorio.getDataRelatorio());
				relatorioSalvo.setFuncionario(relatorio.getFuncionario());
				relatorioSalvo.setInspecao(relatorio.getInspecao());
				relatorioSalvo.setNaoConformidades(relatorio.getNaoConformidades());
				relatorioSalvo = relatorioDAO.save(relatorioSalvo);
			}
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Object>(relatorioSalvo, HttpStatus.OK);
	}

	@RequestMapping(value = "/deletar/{id}", method = RequestMethod.DELETE, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		relatorioDAO.delete(id);
		return new ResponseEntity<Object>("Deletado com sucesso", HttpStatus.OK);
	}

	@RequestMapping(value = "/buscar/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getById(@PathVariable("id") long id) {
		return new ResponseEntity<Object>(relatorioDAO.findOne(id), HttpStatus.OK);
	}

	

	@RequestMapping(value = "/obtertodos", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getAll() {

		return new ResponseEntity<Object>(relatorioDAO.findAll(), HttpStatus.OK);
	}

}