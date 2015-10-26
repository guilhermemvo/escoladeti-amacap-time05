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

import br.unicesumar.time5.entity.MateriaPrimaMaterial;
import br.unicesumar.time5.service.MateriaPrimaMaterialService;

@Controller
@RequestMapping("/materiaprimamaterial")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class MateriaPrimaMaterialController implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(MateriaPrimaMaterialController.class);

    @Autowired
    private MateriaPrimaMaterialService service;

    @RequestMapping(value = {"/listar"}, method = RequestMethod.GET)
    @ResponseBody
    public DataPage<MateriaPrimaMaterial> listar() {
        return service.getMateriaPrimaMaterials(0);
    }

    @RequestMapping(value = {"/listar/pag/{pagina}"}, method = RequestMethod.GET)
    @ResponseBody
    public DataPage<MateriaPrimaMaterial> listar(@PathVariable Integer pagina) {
        return service.getMateriaPrimaMaterials(pagina);
    }

    @RequestMapping(value = "/remover", method = RequestMethod.DELETE)
    @ResponseBody
    public String remover(@RequestBody MateriaPrimaMaterial materiaprimamaterial) {
        service.remover(materiaprimamaterial);
        return "OK";
    }

    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    @ResponseBody
    public String salvar(@RequestBody MateriaPrimaMaterial materiaprimamaterial) {
        service.salvar(materiaprimamaterial);
        return "OK";
    }

    @RequestMapping(value = "/localiza", params = {"id"}, method = RequestMethod.GET)
    @ResponseBody
    public MateriaPrimaMaterial localiza(@RequestParam Long id) {
        logger.debug("Id a localizar: {}", id);
        return service.recuperarPeloId(id);
    }

    @RequestMapping(value = {"/todas"}, method = RequestMethod.GET)
    @ResponseBody
    public List<MateriaPrimaMaterial> todas() {
        return service.getTodosMateriaPrimaMaterials();
    }

    @RequestMapping(value = "/carregar/{id}", method = RequestMethod.GET)
    @ResponseBody
    public MateriaPrimaMaterial carregar(@PathVariable Long id) {
        return service.recuperarPeloId(id);
    }
}