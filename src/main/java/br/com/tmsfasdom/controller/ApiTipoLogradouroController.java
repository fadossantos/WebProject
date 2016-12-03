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

import br.com.tmsfasdom.dao.TipoLogradouroDAO;
import br.com.tmsfasdom.model.TipoLogradouro;

@RestController
@RequestMapping(value = "/api/tipologradouro")
public class ApiTipoLogradouroController {

	@Autowired
	TipoLogradouroDAO tipoLogradouroDAO;

	@RequestMapping(value = "/salvar", method = { RequestMethod.POST, RequestMethod.PUT }, produces = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> criar(@RequestBody TipoLogradouro tipoLogradouro) {
		TipoLogradouro tipoLogradouroSalvo = null;
		try {

			tipoLogradouroSalvo = tipoLogradouroDAO.findByDescTipoLogradouro(tipoLogradouro.getDescTipoLogradouro());
			if (tipoLogradouroSalvo == null) {
				tipoLogradouroSalvo = tipoLogradouroDAO.save(tipoLogradouro);
			} else {
				tipoLogradouroSalvo.setDescTipoLogradouro(tipoLogradouro.getDescTipoLogradouro());
				tipoLogradouroSalvo = tipoLogradouroDAO.save(tipoLogradouroSalvo);
			}
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Object>(tipoLogradouroSalvo, HttpStatus.OK);
	}

	@RequestMapping(value = "/deletar/{id}", method = RequestMethod.DELETE, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		tipoLogradouroDAO.delete(id);
		return new ResponseEntity<Object>("Deletado com sucesso", HttpStatus.OK);
	}

	@RequestMapping(value = "/buscar/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getById(@PathVariable("id") long id) {
		return new ResponseEntity<Object>(tipoLogradouroDAO.findOne(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/buscarpornome/{nome}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getByNome(@PathVariable("nome") String nome) {
		return new ResponseEntity<Object>(tipoLogradouroDAO.findByDescTipoLogradouro(nome), HttpStatus.OK);
	}

	@RequestMapping(value = "/obtertodos", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getAll() {

		return new ResponseEntity<Object>(tipoLogradouroDAO.findAll(), HttpStatus.OK);
	}

}