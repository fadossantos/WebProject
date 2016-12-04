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

import br.com.tmsfasdom.dao.TipoInspecaoDAO;
import br.com.tmsfasdom.model.TipoInspecao;

@RestController
@RequestMapping(value = "/api/tipoInspecao")
public class ApiTipoInspecaoController {

	@Autowired
	TipoInspecaoDAO tipoInspecaoDAO;

	@RequestMapping(value = "/salvar", method = { RequestMethod.POST, RequestMethod.PUT }, produces = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> criar(@RequestBody TipoInspecao tipoInspecao) {
		TipoInspecao tipoInspecaoSalvo = null;
		try {

			tipoInspecaoSalvo = tipoInspecaoDAO.findByDescTipoInspecao(tipoInspecao.getDescTipoInspecao());
			if (tipoInspecaoSalvo == null) {
				tipoInspecaoSalvo = tipoInspecaoDAO.save(tipoInspecao);
			} else {
				tipoInspecaoSalvo.setClientes(tipoInspecao.getClientes());
				tipoInspecaoSalvo.setDescTipoInspecao(tipoInspecao.getDescTipoInspecao());
				tipoInspecaoSalvo.setInspecaos(tipoInspecao.getInspecaos());
				tipoInspecaoSalvo = tipoInspecaoDAO.save(tipoInspecaoSalvo);
			}
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Object>(tipoInspecaoSalvo, HttpStatus.OK);
	}

	@RequestMapping(value = "/deletar/{id}", method = RequestMethod.DELETE, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		tipoInspecaoDAO.delete(id);
		return new ResponseEntity<Object>("Deletado com sucesso", HttpStatus.OK);
	}

	@RequestMapping(value = "/buscar/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getById(@PathVariable("id") long id) {
		return new ResponseEntity<Object>(tipoInspecaoDAO.findOne(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/buscarpornome/{nome}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getBySigla(@PathVariable("nome") String nome) {
		return new ResponseEntity<Object>(tipoInspecaoDAO.findByDescTipoInspecao(nome), HttpStatus.OK);
	}

	@RequestMapping(value = "/obtertodos", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getAll() {

		return new ResponseEntity<Object>(tipoInspecaoDAO.findAll(), HttpStatus.OK);
	}

}