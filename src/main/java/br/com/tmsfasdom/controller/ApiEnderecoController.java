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

import br.com.tmsfasdom.dao.EnderecoDAO;
import br.com.tmsfasdom.model.Endereco;

@RestController
@RequestMapping(value = "/api/endereco")
public class ApiEnderecoController {

	@Autowired
	EnderecoDAO enderecoDAO;

	@RequestMapping(value = "/salvar", method = { RequestMethod.POST, RequestMethod.PUT }, produces = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> criar(@RequestBody Endereco endereco) {
		Endereco enderecoSalvo = null;
		try {

			enderecoSalvo = enderecoDAO.findOne(endereco.getIdEndereco());
			if (enderecoSalvo == null) {
				enderecoSalvo = enderecoDAO.save(endereco);
			} else {
				enderecoSalvo.setBairro(endereco.getBairro());
				enderecoSalvo.setCep(endereco.getCep());
				enderecoSalvo.setCidade(endereco.getCidade());
				enderecoSalvo.setComplemento(endereco.getComplemento());
				enderecoSalvo.setEstado(endereco.getEstado());
				enderecoSalvo.setLatitude(endereco.getLatitude());
				enderecoSalvo.setLongitude(endereco.getLongitude());
				enderecoSalvo.setNumero(endereco.getNumero());
				enderecoSalvo.setTipoLogradouro(endereco.getTipoLogradouro());
				enderecoSalvo.setLogradouro(endereco.getLogradouro());
				
				enderecoSalvo = enderecoDAO.save(enderecoSalvo);
			}
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Object>(enderecoSalvo, HttpStatus.OK);
	}

	@RequestMapping(value = "/deletar/{id}", method = RequestMethod.DELETE, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		enderecoDAO.delete(id);
		return new ResponseEntity<Object>("Deletado com sucesso", HttpStatus.OK);
	}

	@RequestMapping(value = "/buscar/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getById(@PathVariable("id") long id) {
		return new ResponseEntity<Object>(enderecoDAO.findOne(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/obtertodos", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getAll() {

		return new ResponseEntity<Object>(enderecoDAO.findAll(), HttpStatus.OK);
	}

}