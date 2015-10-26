package br.unicesumar.time5.controller;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;




import br.unicesumar.time5.entity.Pais;
import br.unicesumar.time5.entity.UnidadeMedida;
import br.unicesumar.time5.service.UnidadeMedidaService;

/**
 *
 * @author johnLima
 */

@Controller
@RequestMapping("/unidadeMedida")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class UnidadeMedidaController implements Serializable{

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(UnidadeMedidaController.class);

	@Autowired
	private UnidadeMedidaService service;
	
	

	@RequestMapping(value = { "/todos" }, method = RequestMethod.GET)
	@ResponseBody
	public List<UnidadeMedida> todos() {
		return service.getTodosUnidadeMedida();
	}

	@RequestMapping(value = "/carregar/{id}", method = RequestMethod.GET)
	@ResponseBody
	public UnidadeMedida carregar(@PathVariable Long id) {
		return service.carregar(id);
	}
	
	
}
