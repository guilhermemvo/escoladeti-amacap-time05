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

import br.unicesumar.time5.entity.PessoaJuridica;
import br.unicesumar.time5.service.PessoaJuridicaService;

@Controller
@RequestMapping("/pessoajuridica")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class PessoaJuridicaController implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(PessoaJuridicaController.class);

    @Autowired
    private PessoaJuridicaService service;

    @RequestMapping(value = {"/listar"}, method = RequestMethod.GET)
    @ResponseBody
    public DataPage<PessoaJuridica> listar() {
        return service.getPessoaJuridicas(0);
    }

    @RequestMapping(value = {"/listar/pag/{pagina}"}, method = RequestMethod.GET)
    @ResponseBody
    public DataPage<PessoaJuridica> listar(@PathVariable Integer pagina) {
        return service.getPessoaJuridicas(pagina);
    }

    @RequestMapping(value = "/remover", method = RequestMethod.DELETE)
    @ResponseBody
    public String remover(@RequestBody PessoaJuridica pessoajuridica) {
        service.remover(pessoajuridica);
        return "OK";
    }

    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    @ResponseBody
    public String salvar(@RequestBody PessoaJuridica pessoajuridica) {
        service.salvar(pessoajuridica);
        return "OK";
    }

    @RequestMapping(value = "/localiza", params = {"id"}, method = RequestMethod.GET)
    @ResponseBody
    public PessoaJuridica localiza(@RequestParam Long id) {
        logger.debug("Id a localizar: {}", id);
        return service.recuperarPeloId(id);
    }

    @RequestMapping(value = {"/todas"}, method = RequestMethod.GET)
    @ResponseBody
    public List<PessoaJuridica> todas() {
        return service.getTodosPessoaJuridicas();
    }

    @RequestMapping(value = "/carregar/{id}", method = RequestMethod.GET)
    @ResponseBody
    public PessoaJuridica carregar(@PathVariable Long id) {
        return service.recuperarPeloId(id);
    }

   @RequestMapping(value = "/carregar/cnpj/{cnpj}", method = RequestMethod.GET)
    @ResponseBody
    public PessoaJuridica carregarCNPJ(@PathVariable String cnpj) {
        return service.carregarCNPJ(cnpj);
    }
}
