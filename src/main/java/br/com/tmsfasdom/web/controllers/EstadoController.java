package br.com.tmsfasdom.web.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import br.com.tmsfasdom.config.HeaderHttpInterceptor;
import br.com.tmsfasdom.model.Estado;

@Controller
@RequestMapping(value = "/data")
public class EstadoController {
	public static final String host = "https://inspecaomv.tmsfasdom.com.br";

	@GetMapping("/estado")
	public ModelAndView retornaEstado(HttpServletRequest request) {

		RestTemplate restTemplate = setaInterceptor(request);
		if (restTemplate == null) {
			return new ModelAndView("redirect:/login");
		}
		try {

			ResponseEntity<Object> response = restTemplate.getForEntity(host + "/api/estado/obtertodos", Object.class);
			List<Estado> estados = (List<Estado>) response.getBody();
			ModelAndView mav = new ModelAndView();
			mav.setViewName("estado");
			mav.addObject("formEstado", new Estado());

			mav.addObject("listEstados", estados);
			return mav;
		} catch (Exception ex) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("erro");
			mav.addObject("message", ex.getMessage());
			return mav;

		}

	}

	@GetMapping("/estado/{idEstado}")
	public ModelAndView retornaEstadoPorId(HttpServletRequest request, @PathVariable("idEstado") long idEstado) {
		RestTemplate restTemplate = setaInterceptor(request);
		if (restTemplate == null) {
			return new ModelAndView("redirect:/login");
		}
		try {

			ResponseEntity<Estado> response = restTemplate.getForEntity(host + "/api/estado/buscar/{idEstado}",
					Estado.class, idEstado);
			ModelAndView mav = new ModelAndView();
			mav.setViewName("estado_formEstado");
			mav.addObject("formEstado", response.getBody());
			return mav;
		} catch (Exception ex) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("erro");
			mav.addObject("message", ex.getMessage());
			return mav;

		}

	}

	@PostMapping("/estado")
	public ModelAndView adicionaEstado(HttpServletRequest request, @ModelAttribute("formEstado") Estado estado) {
		RestTemplate restTemplate = setaInterceptor(request);
		if (restTemplate == null) {
			return new ModelAndView("redirect:/login");
		}
		try {
			ResponseEntity<Estado> responseEstado = restTemplate.postForEntity(host + "/api/estado/salvar", estado,
					Estado.class);
			ModelAndView mav = retornaListaEstados("Salvo com sucesso",request);
			return mav;
		}

		catch (Exception ex) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("erro");
			mav.addObject("message", ex.getMessage());
			return mav;

		}
	}

	@GetMapping("/estado/remover/{idEstado}")
	public ModelAndView removeEstado(HttpServletRequest request, @PathVariable("idEstado") int idEstado) {
		RestTemplate restTemplate = setaInterceptor(request);
		if (restTemplate == null) {
			return new ModelAndView("redirect:/login");
		}
		try {
			ResponseEntity<Estado> responseEstado = restTemplate.getForEntity(host + "/api/estado/buscar/{idEstado}",
					Estado.class, idEstado);
			restTemplate.delete(host + "/api/estado/deletar/{id}", idEstado);
			ModelAndView mav = retornaListaEstados("Excluido com sucesso",request);
			return mav;

		} catch (Exception ex) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("erro");
			mav.addObject("message", ex.getMessage());
			return mav;

		}

	}
	
	@GetMapping("/estado/obtertodos")
	public ModelAndView retornaCidadePorEstado(HttpServletRequest request) {
		RestTemplate restTemplate = setaInterceptor(request);
		if (restTemplate == null) {
			return new ModelAndView("redirect:/login");
		}
		try{		
					
			return retornaListaEstados("",request);					
		}
		catch(Exception ex)
		{
			ModelAndView mav = new ModelAndView();
			mav.setViewName("erro");
			mav.addObject("message", ex.getMessage());
			return mav;
		
		}
		
	
	}

	private ModelAndView retornaListaEstados(String mensagem, HttpServletRequest request) {
		RestTemplate restTemplate = setaInterceptor(request);
		if (restTemplate == null) {
			return new ModelAndView("redirect:/login");
		}

		ResponseEntity<Object> response = restTemplate.getForEntity(host + "/api/estado/obtertodos", Object.class);
		List<Estado> estados = (List<Estado>) response.getBody();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("estado_table");
		mav.addObject("listEstados", estados);
		mav.addObject("message", mensagem);
		return mav;
	}

	private RestTemplate setaInterceptor(HttpServletRequest request) {
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
		HttpSession secao = request.getSession();
		if (secao == null) {
			return null;
		}
		if (secao.getAttribute("X-AUTH-TOKEN") == null) {
			return null;
		}

		interceptors.add(new HeaderHttpInterceptor("X-AUTH-TOKEN",
				request.getSession().getAttribute("X-AUTH-TOKEN").toString()));

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setInterceptors(interceptors);
		return restTemplate;
	}

}
