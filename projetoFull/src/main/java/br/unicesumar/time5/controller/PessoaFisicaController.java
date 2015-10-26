package br.unicesumar.time5.controller;

import br.unicesumar.time5.entity.PessoaFisica;
import br.unicesumar.time5.service.PessoaFisicaService;
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

@Controller
@RequestMapping("/pessoafisica")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class PessoaFisicaController implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(PessoaFisicaController.class);

    @Autowired
    private PessoaFisicaService service;

    @RequestMapping(value = {"/listar"}, method = RequestMethod.GET)
    @ResponseBody
    public DataPage<PessoaFisica> listar() {
        return service.getPessoaFisicas(0);
    }

    @RequestMapping(value = {"/listar/pag/{pagina}"}, method = RequestMethod.GET)
    @ResponseBody
    public DataPage<PessoaFisica> listar(@PathVariable Integer pagina) {
        return service.getPessoaFisicas(pagina);
    }

    @RequestMapping(value = "/remover", method = RequestMethod.DELETE)
    @ResponseBody
    public String remover(@RequestBody PessoaFisica pessoafisica) {
        service.remover(pessoafisica);
        return "OK";
    }

    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    @ResponseBody
    public String salvar(@RequestBody PessoaFisica pessoafisica) {
        service.salvar(pessoafisica);
        return "OK";
    }

    @RequestMapping(value = "/localiza", params = {"id"}, method = RequestMethod.GET)
    @ResponseBody
    public PessoaFisica localiza(@RequestParam Long id) {
        logger.debug("Id a localizar: {}", id);
        return service.recuperarPeloId(id);
    }

    @RequestMapping(value = {"/todas"}, method = RequestMethod.GET)
    @ResponseBody
    public List<PessoaFisica> todas() {
        return service.getTodosPessoaFisicas();
    }

    @RequestMapping(value = "/carregar/{id}", method = RequestMethod.GET)
    @ResponseBody
    public PessoaFisica carregar(@PathVariable Long id) {
        return service.recuperarPeloId(id);
    }

    /* @RequestMapping(value = "/carregar/cpf/{cpf}", method = RequestMethod.GET)
     @ResponseBody
     public PessoaFisica cpf(@PathVariable String cpf) {
     List<PessoaFisica> pessoas = service.getPessoaCPF(cpf);
        
     PessoaFisica pessoa = new PessoaFisica();
     if (pessoas.size() > 0) {
     pessoa = pessoas.get(0);
     } 
     PessoaFisica pessoa2 = new PessoaFisica();
     pessoa2.setId(pessoa.getId());
     pessoa2.setCpf(pessoa.getCpf());
     pessoa2.setDataNascimento(pessoa.getDataNascimento());
     pessoa2.setDataCadastro(pessoa.getDataCadastro());
     pessoa2.setEmails(pessoa.getEmails());
     pessoa2.setEnderecos(pessoa.getEnderecos());
     pessoa2.setObservacao(pessoa.getObservacao());
     pessoa2.setNome(pessoa.getNome());
     pessoa2.setRg(pessoa.getRg());
     pessoa2.setSexo(pessoa.getSexo());
     pessoa2.setTelefones(pessoa.getTelefones());
        
     return pessoa2;
     }*/
    @RequestMapping(value = "/consulta/nomecpf/{value}", method = RequestMethod.GET)
    @ResponseBody
    public List<PessoaFisica> consultaNomeCpf(@PathVariable String value) {
        return service.carregarNomeCpf(value);
    }

    @RequestMapping(value = "/carregar/cpf/{cpf}", method = RequestMethod.GET)
    @ResponseBody
    public PessoaFisica cpf(@PathVariable String cpf) {
        return service.carregarCPF(cpf);
    }
}
