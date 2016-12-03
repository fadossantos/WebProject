package br.com.tmsfasdom.web.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//import org.apache.jasper.tagplugins.jstl.core.Url;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import br.com.tmsfasdom.config.HeaderHttpInterceptor;
import br.com.tmsfasdom.model.Cidade;
import br.com.tmsfasdom.model.Estado;
import br.com.tmsfasdom.model.UserRequest;

@Controller
@RequestMapping(value = "/data")
public class CidadeController {
	public static final String host = "https://inspecaomv.tmsfasdom.com.br";

	@GetMapping("/cidade")
	public ModelAndView retornaCidade(HttpServletRequest request) {
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
		HttpSession secao = request.getSession();
		if (secao == null) {
			return new ModelAndView("redirect:/login");
		}
		if (secao.getAttribute("X-AUTH-TOKEN") == null) {
			return new ModelAndView("redirect:/login");
		}

		interceptors.add(new HeaderHttpInterceptor("X-AUTH-TOKEN",
				request.getSession().getAttribute("X-AUTH-TOKEN").toString()));
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setInterceptors(interceptors);
		try{		
		//ResponseEntity<Object> response = restTemplate.getForEntity(host + "/api/cidade/obtertodos",
		//		Object.class);
			//List<Cidade> cidades = (List<Cidade>)response.getBody();
			ResponseEntity<Object> response = restTemplate.getForEntity(host + "/api/estado/obtertodos",
					Object.class);
			List<Estado> estados = (List<Estado>)response.getBody();
			ModelAndView mav = new ModelAndView();
			mav.setViewName("cidade");
			mav.addObject("formCidade", new Cidade());
			//mav.addObject("listCidades", cidades);
			mav.addObject("listEstados", estados);
			return mav;					
		}
		catch(Exception ex)
		{
			ModelAndView mav = new ModelAndView();
			mav.setViewName("login");
			mav.addObject("formlogin", new UserRequest());
			mav.addObject("message", ex.getMessage());
			return mav;
		
		}
		
	
	}
	
	@GetMapping("/cidade/{idCidade}")
	public ModelAndView retornaCidadePorId(HttpServletRequest request, @PathVariable("idCidade") long idCidade) {
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
		HttpSession secao = request.getSession();
		if (secao == null) {
			return new ModelAndView("redirect:/login");
		}
		if (secao.getAttribute("X-AUTH-TOKEN") == null) {
			return new ModelAndView("redirect:/login");
		}

		interceptors.add(new HeaderHttpInterceptor("X-AUTH-TOKEN",
				request.getSession().getAttribute("X-AUTH-TOKEN").toString()));
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setInterceptors(interceptors);
		try{		
		ResponseEntity<Object> response1 = restTemplate.getForEntity(host + "/api/estado/obtertodos",
				Object.class);
			List<Estado> estados = (List<Estado>)response1.getBody();
			ResponseEntity<Cidade> response = restTemplate.getForEntity(host + "/api/cidade/buscar/{idCidade}",
					Cidade.class, idCidade);
			ModelAndView mav = new ModelAndView();
			mav.setViewName("cidade_formCidade");
			mav.addObject("formCidade", response.getBody());
			mav.addObject("listEstados", estados);
			return mav;					
		}
		catch(Exception ex)
		{
			ModelAndView mav = new ModelAndView();
			mav.setViewName("login");
			mav.addObject("formLogin", new UserRequest());
			mav.addObject("message", ex.getMessage());
			return mav;
		
		}
		
	
	}

	@PostMapping("/cidade")
	public ModelAndView adicionaCidade(HttpServletRequest request, @ModelAttribute("formCidade") Cidade cidade) {
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
		HttpSession secao = request.getSession();
		if (secao == null) {
			return new ModelAndView("redirect:/login");
		}
		if (secao.getAttribute("X-AUTH-TOKEN") == null) {
			return new ModelAndView("redirect:/login");
		}

		interceptors.add(new HeaderHttpInterceptor("X-AUTH-TOKEN",
				request.getSession().getAttribute("X-AUTH-TOKEN").toString()));
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setInterceptors(interceptors);
		try{		
			ResponseEntity<Cidade> responseCidade = restTemplate.postForEntity(host + "/api/cidade/salvar",
				cidade, Cidade.class);
		    ResponseEntity<Object> response = restTemplate.getForEntity(host + "/api/cidade/buscarporidestado/{idEstado}",
				Object.class, responseCidade.getBody().getEstado().getIdEstado());
			List<Cidade> cidades = (List<Cidade>)response.getBody();
			ModelAndView mav = new ModelAndView();
			mav.setViewName("cidade_table");
			mav.addObject("listCidades", cidades);
			mav.addObject("message", "Salvo com Sucesso!");
			return mav;						}
		
		catch(Exception ex)
		{
			ModelAndView mav = new ModelAndView();
			mav.setViewName("login");
			mav.addObject("formlogin", new UserRequest());
			mav.addObject("message", ex.getMessage());
			return mav;
		
		}
	}
	
	@GetMapping("/cidadeporidestado/{idEstado}")
	public ModelAndView retornaCidadePorEstado(HttpServletRequest request, @PathVariable("idEstado") long idEstado) {
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
		HttpSession secao = request.getSession();
		if (secao == null) {
			return new ModelAndView("redirect:/login");
		}
		if (secao.getAttribute("X-AUTH-TOKEN") == null) {
			return new ModelAndView("redirect:/login");
		}

		interceptors.add(new HeaderHttpInterceptor("X-AUTH-TOKEN",
				request.getSession().getAttribute("X-AUTH-TOKEN").toString()));
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setInterceptors(interceptors);
		try{		
		ResponseEntity<Object> response = restTemplate.getForEntity(host + "/api/cidade/buscarporidestado/{idEstado}",
				Object.class, idEstado);
			List<Cidade> cidades = (List<Cidade>)response.getBody();
			ModelAndView mav = new ModelAndView();
			mav.setViewName("cidade_table");
			mav.addObject("listCidades", cidades);			
			return mav;					
		}
		catch(Exception ex)
		{
			ModelAndView mav = new ModelAndView();
			mav.setViewName("login");
			mav.addObject("formLogin", new UserRequest());
			mav.addObject("message", ex.getMessage());
			return mav;
		
		}
		
	
	}
	
	@GetMapping("/cidade/remover/{idCidade}")
	public ModelAndView removeCidade(HttpServletRequest request, @PathVariable("idCidade") int idCidade ) {
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
		HttpSession secao = request.getSession();
		if (secao == null) {
			return new ModelAndView("redirect:/login");
		}
		if (secao.getAttribute("X-AUTH-TOKEN") == null) {
			return new ModelAndView("redirect:/login");
		}

		interceptors.add(new HeaderHttpInterceptor("X-AUTH-TOKEN",
				request.getSession().getAttribute("X-AUTH-TOKEN").toString()));
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setInterceptors(interceptors);
		try{	ResponseEntity<Cidade> responseCidade = restTemplate.getForEntity(host + "/api/cidade/buscar/{idCidade}",Cidade.class, idCidade); 	
		    restTemplate.delete(host + "/api/cidade/deletar/{id}", idCidade);
		    ResponseEntity<Object> response = restTemplate.getForEntity(host + "/api/cidade/buscarporidestado/{idEstado}",
					Object.class, responseCidade.getBody().getEstado().getIdEstado());
				List<Cidade> cidades = (List<Cidade>)response.getBody();
				ModelAndView mav = new ModelAndView();
				mav.setViewName("cidade_table");
				mav.addObject("listCidades", cidades);
				mav.addObject("message", "Excluído com sucesso!");
				return mav;				
		}
		catch(Exception ex)
		{
			ModelAndView mav = new ModelAndView();
			mav.setViewName("login");
			mav.addObject("formlogin", new UserRequest());
			mav.addObject("message", ex.getMessage());
			return mav;
		
		}
		
	
	}
	@GetMapping("/cidade/editar/{idCidade}")
	public ModelAndView editarCidade(HttpServletRequest request, @PathVariable("idCidade") int idCidade ) {
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
		HttpSession secao = request.getSession();
		if (secao == null) {
			return new ModelAndView("redirect:/login");
		}
		if (secao.getAttribute("X-AUTH-TOKEN") == null) {
			return new ModelAndView("redirect:/login");
		}

		interceptors.add(new HeaderHttpInterceptor("X-AUTH-TOKEN",
				request.getSession().getAttribute("X-AUTH-TOKEN").toString()));
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setInterceptors(interceptors);
		try{		
		    ResponseEntity<Cidade> response = restTemplate.getForEntity(host + "/api/cidade/buscar/{0}", Cidade.class, idCidade);
			Cidade cidade = (Cidade) response.getBody();
		    ResponseEntity<Object> response1 = restTemplate.getForEntity(host + "/api/cidade/obtertodos", Object.class);	
		    List<Cidade> cidades = (List<Cidade>)response1.getBody();
			response1 = restTemplate.getForEntity(host + "/api/estado/obtertodos",
					Object.class);
			List<Estado> estados = (List<Estado>)response1.getBody();
			ModelAndView mav = new ModelAndView();
			mav.setViewName("cidade");
			mav.addObject("formCidade", cidade);
			mav.addObject("listCidades", cidades);
			mav.addObject("listEstados", estados);
			return mav;					
		}
		catch(Exception ex)
		{
			ModelAndView mav = new ModelAndView();
			mav.setViewName("login");
			mav.addObject("formLogin", new UserRequest());
			mav.addObject("message", ex.getMessage());
			return mav;
		
		}
		
	
	}
	
	
}
