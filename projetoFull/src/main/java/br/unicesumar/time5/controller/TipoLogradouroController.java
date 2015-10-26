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

import br.unicesumar.time5.entity.TipoLogradouro;
import br.unicesumar.time5.service.TipoLogradouroService;

@Controller
@RequestMapping("/tipologradouro")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class TipoLogradouroController implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(TipoLogradouroController.class);

    @Autowired
    private TipoLogradouroService service;

    @RequestMapping(value = {"/listar"}, method = RequestMethod.GET)
    @ResponseBody
    public DataPage<TipoLogradouro> listar() {
        return service.getTiposLogradouro(0);
    }

    @RequestMapping(value = {"/listar/pag/{pagina}"}, method = RequestMethod.GET)
    @ResponseBody
    public DataPage<TipoLogradouro> listar(@PathVariable Integer pagina) {
        return service.getTiposLogradouro(pagina);
    }

    @RequestMapping(value = "/remover", method = RequestMethod.DELETE)
    @ResponseBody
    public String remover(@RequestBody TipoLogradouro tipologradouro) {
        service.remover(tipologradouro);
        return "OK";
    }

    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    @ResponseBody
    public String salvar(@RequestBody TipoLogradouro tipologradouro) {
        service.salvar(tipologradouro);
        return "OK";
    }

    @RequestMapping(value = "/localiza", params = {"id"}, method = RequestMethod.GET)
    @ResponseBody
    public TipoLogradouro localiza(@RequestParam Long id) {
        logger.debug("Id a localizar: {}", id);
        return service.recuperarPeloId(id);
    }

    @RequestMapping(value = {"/todos"}, method = RequestMethod.GET)
    @ResponseBody
    public List<TipoLogradouro> todos() {
        return service.getTodosTiposLogradouro();
    }

    @RequestMapping(value = "/carregar/{id}", method = RequestMethod.GET)
    @ResponseBody
    public TipoLogradouro carregar(@PathVariable Long id) {
        return service.recuperarPeloId(id);
    }
    
    @RequestMapping(value = "/procurarPorNome/{nome}", method = RequestMethod.GET)
    @ResponseBody
    public DataPage<TipoLogradouro> procurarPorNome(@PathVariable String nome) {
        return service.procurarPorNome(0, nome);
    }         

}
