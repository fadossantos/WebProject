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

import br.com.tmsfasdom.dao.CidadeDAO;
import br.com.tmsfasdom.dao.EstadoDAO;
import br.com.tmsfasdom.model.Cidade;
import br.com.tmsfasdom.model.Estado;

@RestController
@RequestMapping(value = "/api/cidade")
public class ApiCidadeController {

	@Autowired
	CidadeDAO cidadeDAO;
	@Autowired 
	EstadoDAO estadoDAO;

	@RequestMapping(value = "/salvar", method = { RequestMethod.POST, RequestMethod.PUT }, produces = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> criar(@RequestBody Cidade cidade) {
		Cidade cidadeSalvo = null;
		try {

			cidadeSalvo = cidadeDAO.findByNomeCidade(cidade.getNomeCidade());
			if (cidadeSalvo == null) {
				cidadeSalvo = cidadeDAO.save(cidade);
			} else {
				cidadeSalvo.setNomeCidade(cidade.getNomeCidade());
				cidadeSalvo.setEstado(cidade.getEstado());
				cidadeSalvo = cidadeDAO.save(cidadeSalvo);
			}
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Object>(cidadeSalvo, HttpStatus.OK);
	}

	@RequestMapping(value = "/deletar/{id}", method = RequestMethod.DELETE, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		cidadeDAO.delete(id);
		return new ResponseEntity<Object>("Deletado com sucesso", HttpStatus.OK);
	}

	@RequestMapping(value = "/buscar/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getById(@PathVariable("id") long id) {
		return new ResponseEntity<Object>(cidadeDAO.findOne(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/buscarpornome/{nome}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getByNome(@PathVariable("nome") String nome) {
		return new ResponseEntity<Object>(cidadeDAO.findByNomeCidade(nome), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/buscarporidestado/{idEstado}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getByEstado(@PathVariable("idEstado") long idEstado) {
		Estado estado = estadoDAO.findOne(idEstado);		
		return new ResponseEntity<Object>(cidadeDAO.findByEstado(estado), HttpStatus.OK);
	}

	@RequestMapping(value = "/obtertodos", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getAll() {

		return new ResponseEntity<Object>(cidadeDAO.findAll(), HttpStatus.OK);
	}

}