package br.unicesumar.time5.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import br.unicesumar.time5.entity.AssociadoPFMovimentacao;
import br.unicesumar.time5.service.AssociadoPFMovimentacaoService;

@Controller
@RequestMapping("/associadopfmovimentacao")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class AssociadoPFMovimentacaoController {

	private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(AssociadoPFMovimentacaoController.class);

    @Autowired
    private AssociadoPFMovimentacaoService service;

    @RequestMapping(value = {"/listar"}, method = RequestMethod.GET)
    @ResponseBody
    public DataPage<AssociadoPFMovimentacao> listar() {
        return service.getAssociadoPFMovimentacao(0);
    }

    @RequestMapping(value = {"/listar/pag/{pagina}"}, method = RequestMethod.GET)
    @ResponseBody
    public DataPage<AssociadoPFMovimentacao> listar(@PathVariable Integer pagina) {
        return service.getAssociadoPFMovimentacao(pagina);
    }

    

    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    @ResponseBody
    public String salvar(@RequestBody AssociadoPFMovimentacao associadoPFMovimentacao) {
        service.salvar(associadoPFMovimentacao);
        return "OK";
    }

    @RequestMapping(value = "/associados", method = RequestMethod.POST)
    @ResponseBody
    public List<AssociadoPFMovimentacao> associadoPFMovimentacao(@RequestBody Long id) {
        return service.recuperarMovimentacaoPorAssociado(id);
    }
    

    @RequestMapping(value = {"/todas"}, method = RequestMethod.GET)
    @ResponseBody
    public List<AssociadoPFMovimentacao> todas() {
        return service.getTodosAssociadoPFMovimentacao(); 
    }
    
    @RequestMapping(value = {"/todasmovimentacoes"}, method = RequestMethod.GET)
    @ResponseBody
    public List<AssociadoPFMovimentacao> todasMovimentacaoes(@RequestBody Long id) {
        return service.getTodasMovimentacoes(id); 
    }
   

}

