package br.unicesumar.time5.controller;

import br.unicesumar.time5.entity.Instrutor;
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

import br.unicesumar.time5.entity.Professor;
import br.unicesumar.time5.service.LivroService;
import br.unicesumar.time5.service.ProfessorService;
import br.unicesumar.time5.service.RelatorioService;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRException;

@Controller
@RequestMapping("/professor")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class ProfessorController extends RelatorioService implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(ProfessorController.class);

    @Autowired
    private ProfessorService service;

    @Autowired
    private DataSource dataSource;

    @RequestMapping(value = {"/listar"}, method = RequestMethod.GET)
    @ResponseBody
    public DataPage<Professor> listar() {
        return service.getProfessors(0);
    }

    @RequestMapping(value = {"/listar/pag/{pagina}"}, method = RequestMethod.GET)
    @ResponseBody
    public DataPage<Professor> listar(@PathVariable Integer pagina) {
        return service.getProfessors(pagina);
    }

    @RequestMapping(value = "/remover", method = RequestMethod.DELETE)
    @ResponseBody
    public String remover(@RequestBody Professor professor) {
        service.remover(professor);
        return "OK";
    }

    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    @ResponseBody
    public Professor salvar(@RequestBody Professor professor) {
        return service.salvar(professor);
        //return "OK";
    }

    @RequestMapping(value = "/localiza", params = {"id"}, method = RequestMethod.GET)
    @ResponseBody
    public Professor localiza(@RequestParam Long id) {
        logger.debug("Id a localizar: {}", id);
        return service.recuperarPeloId(id);
    }

    @RequestMapping(value = {"/todas"}, method = RequestMethod.GET)
    @ResponseBody
    public List<Professor> todas() {
        return service.getTodosProfessors();
    }

    @RequestMapping(value = "/carregar/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Professor carregar(@PathVariable Long id) {
        return service.recuperarPeloId(id);
    }

    @RequestMapping(value = "/consulta/{cpf}", method = RequestMethod.GET)
    @ResponseBody
    public Professor carregarCPF(@PathVariable String cpf) {
        return service.carregarCPF(cpf);
    }

    @RequestMapping(value = "/procurarPorNome/{nome}", method = RequestMethod.GET)
    @ResponseBody
    public DataPage<Professor> procurarPorNome(@PathVariable String nome) {
        return service.procurarPorNome(0, nome);
    }

    @RequestMapping(value = "/relatorio/listaRelatorio", method = RequestMethod.GET)
    public void listaRelatorio(HttpServletResponse response) throws IOException, JRException, SQLException {
        listaRelatorio(response, dataSource.getConnection(), "listaInstrutores.jasper", LivroService.class, "listaInstrutores.pdf");
    }

    @RequestMapping(value = "/consulta/nomecpf/{value}", method = RequestMethod.GET)
    @ResponseBody
    public List<Professor> consultaNomeCpf(@PathVariable String value) {
        return service.carregarNomeCpf(value);
    }
}
