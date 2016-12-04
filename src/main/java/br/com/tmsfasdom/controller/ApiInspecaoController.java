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

import br.com.tmsfasdom.dao.InspecaoDAO;
import br.com.tmsfasdom.model.Inspecao;

@RestController
@RequestMapping(value = "/api/inspecao")
public class ApiInspecaoController {

	@Autowired
	InspecaoDAO inspecaoDAO;

	@RequestMapping(value = "/salvar", method = { RequestMethod.POST, RequestMethod.PUT }, produces = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> criar(@RequestBody Inspecao inspecao) {
		Inspecao inspecaoSalvo = null;
		try {

			inspecaoSalvo = inspecaoDAO.findOne(inspecao.getIdInspecao());
			if (inspecaoSalvo == null) {
				inspecaoSalvo = inspecaoDAO.save(inspecao);
			} else {
				inspecaoSalvo.setAgendamentos(inspecao.getAgendamentos());
				inspecaoSalvo.setCliente(inspecao.getCliente());
				inspecaoSalvo.setCustoInspecaos(inspecao.getCustoInspecaos());
				inspecaoSalvo.setDesignacaos(inspecao.getDesignacaos());
				inspecaoSalvo.setDtSolicitacaoInspecao(inspecao.getDtSolicitacaoInspecao());
				inspecaoSalvo.setEndereco(inspecao.getEndereco());
				inspecaoSalvo.setHonorario(inspecao.getHonorario());
				inspecaoSalvo.setInsercaoSistemas(inspecao.getInsercaoSistemas());
				inspecaoSalvo.setInspecao_Atividade_Apurada(inspecao.getInspecao_Atividade_Apurada());
				inspecaoSalvo.setInspecao_Atividade_Informada(inspecao.getInspecao_Atividade_Informada());
				inspecaoSalvo.setInspecaoCoberturas(inspecao.getInspecaoCoberturas());
				inspecaoSalvo.setNomeContato(inspecao.getNomeContato());
				inspecaoSalvo.setNomeCorretor(inspecao.getNomeCorretor());
				inspecaoSalvo.setNumInspecaoCliente(inspecao.getNumInspecaoCliente());
				inspecaoSalvo.setNumPropostaCliente(inspecao.getNumPropostaCliente());
				inspecaoSalvo.setObservacao(inspecao.getObservacao());
				inspecaoSalvo.setQtdBlocos(inspecao.getQtdBlocos());
				inspecaoSalvo.setRamo(inspecao.getRamo());
				inspecaoSalvo.setRelatorios(inspecao.getRelatorios());
				inspecaoSalvo.setRevisaos(inspecao.getRevisaos());
				inspecaoSalvo.setRoubo(inspecao.getRoubo());
				inspecaoSalvo.setSegurado(inspecao.getSegurado());
				inspecaoSalvo.setStatus(inspecao.getStatus());
				inspecaoSalvo.setTelefoneContato(inspecao.getTelefoneContato());
				inspecaoSalvo.setTelefoneCorretor(inspecao.getTelefoneCorretor());
				inspecaoSalvo.setTipoInspecao(inspecao.getTipoInspecao());
				inspecaoSalvo.setValorTotalRisco(inspecao.getValorTotalRisco());
				inspecaoSalvo.setVistorias(inspecao.getVistorias());
				inspecaoSalvo = inspecaoDAO.save(inspecaoSalvo);
			}
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Object>(inspecaoSalvo, HttpStatus.OK);
	}

	@RequestMapping(value = "/deletar/{id}", method = RequestMethod.DELETE, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		inspecaoDAO.delete(id);
		return new ResponseEntity<Object>("Deletado com sucesso", HttpStatus.OK);
	}

	@RequestMapping(value = "/buscar/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getById(@PathVariable("id") long id) {
		return new ResponseEntity<Object>(inspecaoDAO.findOne(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/obtertodos", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getAll() {

		return new ResponseEntity<Object>(inspecaoDAO.findAll(), HttpStatus.OK);
	}

}