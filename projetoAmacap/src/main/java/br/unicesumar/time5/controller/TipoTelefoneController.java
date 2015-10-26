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

import br.unicesumar.time5.entity.TipoTelefone;
import br.unicesumar.time5.service.TipoTelefoneService;

@Controller
@RequestMapping("/rest/tipotelefone")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class TipoTelefoneController implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(TipoTelefoneController.class);

    @Autowired
    private TipoTelefoneService service;

    @RequestMapping(value = {"/listar"}, method = RequestMethod.GET)
    @ResponseBody
    public DataPage<TipoTelefone> listar() {
        return service.getTiposTelefone(0);
    }

    @RequestMapping(value = {"/listar/pag/{pagina}"}, method = RequestMethod.GET)
    @ResponseBody
    public DataPage<TipoTelefone> listar(@PathVariable Integer pagina) {
        return service.getTiposTelefone(pagina);
    }

    @RequestMapping(value = "/remover", method = RequestMethod.DELETE)
    @ResponseBody
    public String remover(@RequestBody TipoTelefone tipotelefone) {
        service.remover(tipotelefone);
        return "OK";
    }

    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    @ResponseBody
    public String salvar(@RequestBody TipoTelefone tipotelefone) {
        service.salvar(tipotelefone);
        return "OK";
    }

    @RequestMapping(value = "/localiza", params = {"id"}, method = RequestMethod.GET)
    @ResponseBody
    public TipoTelefone localiza(@RequestParam Long id) {
        logger.debug("Id a localizar: {}", id);
        return service.recuperarPeloId(id);
    }

    @RequestMapping(value = {"/todos"}, method = RequestMethod.GET)
    @ResponseBody
    public List<TipoTelefone> todos() {
        return service.getTodosTiposTelefone();
    }

    @RequestMapping(value = "/carregar/{id}", method = RequestMethod.GET)
    @ResponseBody
    public TipoTelefone carregar(@PathVariable Long id) {
        return service.recuperarPeloId(id);
    }

}
