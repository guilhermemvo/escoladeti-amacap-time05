/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unicesumar.time5.controller;

import br.unicesumar.time5.entity.Pais;
import br.unicesumar.time5.entity.UnidadeProducao;
import br.unicesumar.time5.service.PaisService;
import br.unicesumar.time5.service.UnidadeProducaoService;
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
 * @author johnLima
 */
@Controller
@RequestMapping("/unidadeProducao")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class UnidadeProducaoController implements Serializable {
      private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(UnidadeProducaoController.class);

    @Autowired
    private UnidadeProducaoService service;

    @RequestMapping(value = {"/listar"}, method = RequestMethod.GET)
    @ResponseBody
    public DataPage<UnidadeProducao> listar() {
        return service.getUnidadeProducao(0);
    }

    @RequestMapping(value = {"/listar/pag/{pagina}"}, method = RequestMethod.GET)
    @ResponseBody
    public DataPage<UnidadeProducao> listar(@PathVariable Integer pagina) {
        return service.getUnidadeProducao(pagina);
    }

    @RequestMapping(value = "/remover", method = RequestMethod.DELETE)
    @ResponseBody
    public String remover(@RequestBody UnidadeProducao unidadeProducao) {
        service.remover(unidadeProducao);
        return "OK";
    }

    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    @ResponseBody
    public String salvar(@RequestBody UnidadeProducao unidadeProducao) {
        System.out.println(unidadeProducao.getNome());
        service.salvar(unidadeProducao);
        return "OK";
    }

    @RequestMapping(value = "/localiza", params = {"id"}, method = RequestMethod.GET)
    @ResponseBody
    public UnidadeProducao localiza(@RequestParam Long id) {
        logger.debug("Id a localizar: {}", id);
        return service.recuperarPeloId(id);
    }

    @RequestMapping(value = {"/todos"}, method = RequestMethod.GET)
    @ResponseBody
    public List<UnidadeProducao> todos() {
        return service.getTodosUnidadeProducao();
    }

    @RequestMapping(value = "/carregar/{id}", method = RequestMethod.GET)
    @ResponseBody
    public UnidadeProducao carregar(@PathVariable Long id) {
        return service.recuperarPeloId(id);
    }
}
