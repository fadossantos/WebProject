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

import br.com.tmsfasdom.dao.VistoriaDAO;
import br.com.tmsfasdom.model.Vistoria;

@RestController
@RequestMapping(value = "/api/vistoria")
public class ApiVistoriaController {

	@Autowired
	VistoriaDAO vistoriaDAO;

	@RequestMapping(value = "/salvar", method = { RequestMethod.POST, RequestMethod.PUT }, produces = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> criar(@RequestBody Vistoria vistoria) {
		Vistoria vistoriaSalvo = null;
		try {

			vistoriaSalvo = vistoriaDAO.findOne(vistoria.getIdVistoria());
			if (vistoriaSalvo == null) {
				vistoriaSalvo = vistoriaDAO.save(vistoria);
			} else {
				vistoriaSalvo.setDataHoraChegadaLocal(vistoria.getDataHoraChegadaLocal());
				vistoriaSalvo.setDataHoraFinalInspecao(vistoria.getDataHoraFinalInspecao());
				vistoriaSalvo.setDataHoraInicioInspecao(vistoria.getDataHoraInicioInspecao());
				vistoriaSalvo.setFuncionario(vistoria.getFuncionario());
				vistoriaSalvo.setInspecao(vistoria.getInspecao());
				vistoriaSalvo = vistoriaDAO.save(vistoriaSalvo);
			}
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Object>(vistoriaSalvo, HttpStatus.OK);
	}

	@RequestMapping(value = "/deletar/{id}", method = RequestMethod.DELETE, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		vistoriaDAO.delete(id);
		return new ResponseEntity<Object>("Deletado com sucesso", HttpStatus.OK);
	}

	@RequestMapping(value = "/buscar/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getById(@PathVariable("id") long id) {
		return new ResponseEntity<Object>(vistoriaDAO.findOne(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/obtertodos", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getAll() {

		return new ResponseEntity<Object>(vistoriaDAO.findAll(), HttpStatus.OK);
	}

}