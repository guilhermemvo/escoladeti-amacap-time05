package br.unicesumar.time5.controller;

import br.unicesumar.time5.entity.Pais;
import br.unicesumar.time5.entity.UnidadeFederativa;
import br.unicesumar.time5.service.UnidadeFederativaService;
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
@RequestMapping("/unidadefederativa")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class UnidadeFederativaController implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(UnidadeFederativaController.class);

    @Autowired
    private UnidadeFederativaService service;

    @RequestMapping(value = {"/listar"}, method = RequestMethod.GET)
    @ResponseBody
    public DataPage<UnidadeFederativa> listar() {
        return service.getUnidadesFederativas(0);
    }

    @RequestMapping(value = {"/listar/pag/{pagina}"}, method = RequestMethod.GET)
    @ResponseBody
    public DataPage<UnidadeFederativa> listar(@PathVariable Integer pagina) {        
        return service.getUnidadesFederativas(pagina);
    }

    @RequestMapping(value = "/remover", method = RequestMethod.DELETE)
    @ResponseBody
    public String remover(@RequestBody UnidadeFederativa unidadefederativa) {
        service.remover(unidadefederativa);
        return "OK";
    }


    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    @ResponseBody
    public String salvar(@RequestBody UnidadeFederativa unidadefederativa) {
        UnidadeFederativa unidadeRetorno;
        unidadeRetorno = service.getCodeLocalization(unidadefederativa.getCodigoIBGE());
        if(unidadeRetorno != null && unidadeRetorno.getId().equals(unidadefederativa.getId())){
                        service.salvar(unidadefederativa);
                        return "OK";
        }
        if(unidadeRetorno != null && !unidadeRetorno.getId().equals(unidadefederativa.getId())){
                return "ERROR";
        }

        service.salvar(unidadefederativa);
                return "OK";
    }


    @RequestMapping(value = "/localiza", params = {"id"}, method = RequestMethod.GET)
    @ResponseBody
    public UnidadeFederativa localiza(@RequestParam Long id) {
        logger.debug("Id a localizar: {}", id);
        return service.recuperarPeloId(id);
    }

    @RequestMapping(value = {"/todos"}, method = RequestMethod.GET)
    @ResponseBody
    public List<UnidadeFederativa> todos() {
        return service.getTodasUnidadesFederativas();
    }

    @RequestMapping(value = "/carregar/{id}", method = RequestMethod.GET)
    @ResponseBody
    public UnidadeFederativa carregar(@PathVariable Long id) {
        return service.recuperarPeloId(id);
    }
    
    @RequestMapping(value = "/findbyCodigoPais/{id}",  method = RequestMethod.GET)
    @ResponseBody
    public List<UnidadeFederativa> findbyCodigoPais(@PathVariable Long id) {        
        return service.findbyCodigoPais(id);
    }   
    
    @RequestMapping(value = "/procurarPorNome/{nome}", method = RequestMethod.GET)
    @ResponseBody
    public DataPage<UnidadeFederativa> procurarPorNome(@PathVariable String nome) {
        return service.procurarPorNome(0, nome);
    }     

}
