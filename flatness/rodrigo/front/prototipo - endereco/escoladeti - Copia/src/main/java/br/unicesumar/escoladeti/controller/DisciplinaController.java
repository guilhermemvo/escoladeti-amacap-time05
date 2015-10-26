package br.unicesumar.escoladeti.controller;

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

import br.unicesumar.escoladeti.entity.Disciplina;
import br.unicesumar.escoladeti.service.DisciplinaService;

@Controller
@RequestMapping("/rest/disciplina")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class DisciplinaController implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(DisciplinaController.class);

    @Autowired
    private DisciplinaService service;

    @RequestMapping(value = {"/listar"}, method = RequestMethod.GET)
    @ResponseBody
    public DataPage<Disciplina> listar() {   	
    	return service.getDisciplinas(0);
    }
    
    @RequestMapping(value = {"/listar/pag/{pagina}"}, method = RequestMethod.GET)
    @ResponseBody
    public DataPage<Disciplina> listar(@PathVariable Integer pagina) {   	
    	return service.getDisciplinas(pagina);
    }

    @RequestMapping(value = "/remover", method = RequestMethod.DELETE)
    @ResponseBody
    public String remover(@RequestBody Disciplina disciplina) {
    	service.remover(disciplina);
    	return "OK";
    }

    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    @ResponseBody
    public String salvar(@RequestBody Disciplina disciplina) {
    	service.salvar(disciplina);
    	return "OK";
    }

    @RequestMapping(value = "/localiza", params = {"id"}, method = RequestMethod.GET)
    @ResponseBody
    public Disciplina localiza(@RequestParam Long id) {
    	logger.debug("Id a localizar: {}", id);
    	return service.recuperarPeloId(id);
    }

    @RequestMapping(value = {"/todas"}, method = RequestMethod.GET)
    @ResponseBody
    public List<Disciplina> todas() {   	
    	return service.getTodasDisciplinas();
    }
    
    @RequestMapping(value = "/carregar/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Disciplina carregar(@PathVariable Long id) {
    	return service.carregar(id);
    }
    
}
