package br.unicesumar.time5.controller;

import br.unicesumar.time5.entity.DAOInscricao;
import br.unicesumar.time5.entity.Inscricao;
import br.unicesumar.time5.service.InscricaoService;
import br.unicesumar.time5.vo.InscricaoVO;
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
@RequestMapping("/inscricao")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class InscricaoController implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(InscricaoController.class);

    @Autowired
    private InscricaoService service;

    @RequestMapping(value = {"/listar"}, method = RequestMethod.GET)
    @ResponseBody
    public DataPage<Inscricao> listar() {
        return service.getInscritos(0);
    }

    @RequestMapping(value = {"/listar/pag/{pagina}"}, method = RequestMethod.GET)
    @ResponseBody
    public DataPage<Inscricao> listar(@PathVariable Integer pagina) {
        return service.getInscritos(pagina);
    }

    @RequestMapping(value = "/remover", method = RequestMethod.DELETE)
    @ResponseBody
    public String remover(@RequestBody Long i) {
        service.remover(i);
        return "OK";
    }

    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    @ResponseBody
    public String salvar(@RequestBody DAOInscricao i) {
        service.salvar(i);
        return "OK";
    }

    @RequestMapping(value = "/localiza", params = {"id"}, method = RequestMethod.GET)
    @ResponseBody
    public Inscricao localiza(@RequestParam Long id) {
        logger.debug("Id a localizar: {}", id);
        return service.recuperarPeloId(id);
    }

    @RequestMapping(value = {"/todos"}, method = RequestMethod.GET)
    @ResponseBody
    public List<Inscricao> todos() {
        return service.getTodosInscritos();
    }

    @RequestMapping(value = "/carregar/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Inscricao carregar(@PathVariable Long id) {
        return service.recuperarPeloId(id);
    }
    
    @RequestMapping(value = "/participantes", method = RequestMethod.POST)
    @ResponseBody
    public List<Inscricao> participantes(@RequestBody Long id) {
        return service.recuperarParticipantesPorIdEvento(id);
    }

    @RequestMapping(value = "/atualizarInscricao", method = RequestMethod.POST)
    @ResponseBody
    public String atualizarInscricoes(@RequestBody List<InscricaoVO> inscricoes) {
        service.atualizarInscricoes(inscricoes);
        return "OK";
    }
}
