package br.unicesumar.escoladeti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import br.unicesumar.escoladeti.entity.QuestaoObjetiva;
import br.unicesumar.escoladeti.service.QuestaoObjetivaService;

@Controller
@RequestMapping("/rest/questaoObjetiva")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class QuestaoObjetivaController {

	@Autowired
	private QuestaoObjetivaService service;
	
	public QuestaoObjetivaService getService() {
		return service;
	}

	@RequestMapping(value = {"/listar/pag/{pagina}"}, method = RequestMethod.GET)
    @ResponseBody
    public DataPage<QuestaoObjetiva> listar(@PathVariable Integer pagina) {   	
    	return getService().getQuestoes(pagina);
    }

    @RequestMapping(value = "/remover", method = RequestMethod.DELETE)
    @ResponseBody
    public String remover(@RequestBody QuestaoObjetiva questao) {
    	getService().remover(questao);
    	return "OK";
    }

    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    @ResponseBody
    public String salvar(@RequestBody QuestaoObjetiva questao) {
    	getService().salvar(questao);
    	return "OK";
    }
    
    @RequestMapping(value = "/carregar/{id}", method = RequestMethod.GET)
    @ResponseBody
    public QuestaoObjetiva carregar(@PathVariable Long id) {
    	return getService().carregar(id);
    }
}
