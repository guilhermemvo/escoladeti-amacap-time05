package br.unicesumar.escoladeti.controller;

import br.unicesumar.escoladeti.entity.Cor;
import br.unicesumar.escoladeti.entity.Endereco;
import br.unicesumar.escoladeti.service.EnderecoService;
import java.io.Serializable;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author RodrigoCezar
 */
@Controller
@RequestMapping("/rest/endereco")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class EnderecoController implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(EnderecoController.class);

    @Autowired
    private EnderecoService service;

    @RequestMapping(value = {"/listar"}, method = RequestMethod.GET)
    @ResponseBody
    public DataPage<Endereco> listar() {   	
    	return service.getEndereco(0);
    }
    
    @RequestMapping(value = {"/listar/pag/{pagina}"}, method = RequestMethod.GET)
    @ResponseBody
    public DataPage<Endereco> listar(@PathVariable Integer pagina) {   	
    	return service.getEndereco(pagina);
    }

    @RequestMapping(value = "/remover", method = RequestMethod.DELETE)
    @ResponseBody
    public String remover(@RequestBody Endereco endereco) {
    	service.remover(endereco);
    	return "OK";
    }

    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    @ResponseBody
    public String salvar(@RequestBody Endereco endereco) {
    	service.salvar(endereco);
    	return "OK";
    }

    @RequestMapping(value = "/localiza", params = {"id"}, method = RequestMethod.GET)
    @ResponseBody
    public Endereco localiza(@RequestParam Long id) {
    	logger.debug("Id a localizar: {}", id);
    	return service.recuperarPeloId(id);
    }

    @RequestMapping(value = {"/todas"}, method = RequestMethod.GET)
    @ResponseBody
    public List<Endereco> todas() {   	
    	return service.getTodosEnderecos();
    }
    
    @RequestMapping(value = "/carregar/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Endereco carregar(@PathVariable Long id) {
    	return service.carregar(id);
    }
}
