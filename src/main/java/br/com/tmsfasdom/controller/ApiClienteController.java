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

import br.com.tmsfasdom.dao.ClienteDAO;
import br.com.tmsfasdom.model.Cliente;

@RestController
@RequestMapping(value = "/api/cliente")
public class ApiClienteController {

	@Autowired
	ClienteDAO clienteDAO;

	@RequestMapping(value = "/salvar", method = { RequestMethod.POST, RequestMethod.PUT }, produces = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> criar(@RequestBody Cliente cliente) {
		Cliente clienteSalvo = null;
		try {

			clienteSalvo = clienteDAO.findOne(cliente.getIdCliente());
			if (clienteSalvo == null) {
				clienteSalvo = clienteDAO.save(cliente);
			} else {
				clienteSalvo.setNomeCliente(cliente.getNomeCliente());
				clienteSalvo.setPrazoCliente(cliente.getPrazoCliente());
				clienteSalvo.setInspecaos(cliente.getInspecaos());
				clienteSalvo.setRamos(cliente.getRamos());
				clienteSalvo.setTipoInspecaos(cliente.getTipoInspecaos());
				
				clienteSalvo = clienteDAO.save(clienteSalvo);
			}
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Object>(clienteSalvo, HttpStatus.OK);
	}

	@RequestMapping(value = "/deletar/{id}", method = RequestMethod.DELETE, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		clienteDAO.delete(id);
		return new ResponseEntity<Object>("Deletado com sucesso", HttpStatus.OK);
	}

	@RequestMapping(value = "/buscar/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getById(@PathVariable("id") long id) {
		return new ResponseEntity<Object>(clienteDAO.findOne(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/buscarpornome/{nome}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getBySigla(@PathVariable("nome") String nome) {
		return new ResponseEntity<Object>(clienteDAO.findByNomeCliente(nome), HttpStatus.OK);
	}

	@RequestMapping(value = "/obtertodos", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getAll() {

		return new ResponseEntity<Object>(clienteDAO.findAll(), HttpStatus.OK);
	}

}