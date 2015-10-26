package br.unicesumar.time5.controller;

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

import br.unicesumar.time5.entity.Cidade;
import br.unicesumar.time5.service.CidadeService;

@Controller
@RequestMapping("/cidade")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class CidadeController implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(CidadeController.class);

    @Autowired
    private CidadeService service;

    @RequestMapping(value = {"/listar"}, method = RequestMethod.GET)
    @ResponseBody
    public DataPage<Cidade> listar() {
        return service.getCidades(0);
    }

    @RequestMapping(value = {"/listar/pag/{pagina}"}, method = RequestMethod.GET)
    @ResponseBody
    public DataPage<Cidade> listar(@PathVariable Integer pagina) {
        return service.getCidades(pagina);
    }

    @RequestMapping(value = "/remover", method = RequestMethod.DELETE)
    @ResponseBody
    public String remover(@RequestBody Cidade cidade) {
        service.remover(cidade);
        return "OK";
    }


	    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
	    @ResponseBody
	    public String salvar(@RequestBody Cidade cidade) {
	    	Cidade cidadeRetorno;
	    	cidadeRetorno = service.getCodeLocalization(cidade.getCodigoIBGE());
	    	if(cidadeRetorno != null && cidadeRetorno.getId().equals(cidade.getId())){
	        		service.salvar(cidade);
	        		return "OK";
	        }
	        if(cidadeRetorno != null && !cidadeRetorno.getId().equals(cidade.getId())){
	        	return "ERROR";
	        }
	        
	        service.salvar(cidade);
			return "OK";
	    }


    @RequestMapping(value = "/localiza", params = {"id"}, method = RequestMethod.GET)
    @ResponseBody
    public Cidade localiza(@RequestParam Long id) {
        logger.debug("Id a localizar: {}", id);
        return service.recuperarPeloId(id);
    }

    @RequestMapping(value = {"/todas"}, method = RequestMethod.GET)
    @ResponseBody
    public List<Cidade> todas() {
        return service.getTodasCidades();
    }

    @RequestMapping(value = "/carregar/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Cidade carregar(@PathVariable Long id) {
        return service.recuperarPeloId(id);
    }
    
    @RequestMapping(value = {"/procurarCidadePorIdUf/{id}"}, method = RequestMethod.GET)
    @ResponseBody
    public List<Cidade> procurarCidadePorIdUf(@PathVariable Long id) {
        return service.procurarCidadePorIdUf(id);
    }
    
    @RequestMapping(value = "/procurarPorNome/{nome}", method = RequestMethod.GET)
    @ResponseBody
    public DataPage<Cidade> procurarPorNome(@PathVariable String nome) {
        return service.procurarPorNome(0, nome);
    }    
}