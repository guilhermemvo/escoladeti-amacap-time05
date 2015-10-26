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

import br.unicesumar.time5.entity.Funcionario;
import br.unicesumar.time5.service.FuncionarioService;
import br.unicesumar.time5.service.RelatorioService;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRException;

@Controller
@RequestMapping("/funcionario")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class FuncionarioController extends RelatorioService implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(FuncionarioController.class);

    @Autowired
    private FuncionarioService service;
    @Autowired
    private DataSource dataSource;

    @RequestMapping(value = {"/listar"}, method = RequestMethod.GET)
    @ResponseBody
    public DataPage<Funcionario> listar() {
        return service.getFuncionarios(0);
    }

    @RequestMapping(value = {"/listar/pag/{pagina}"}, method = RequestMethod.GET)
    @ResponseBody
    public DataPage<Funcionario> listar(@PathVariable Integer pagina) {
        return service.getFuncionarios(pagina);
    }

    @RequestMapping(value = "/remover", method = RequestMethod.DELETE)
    @ResponseBody
    public String remover(@RequestBody Funcionario funcionario) {
        service.remover(funcionario);
        return "OK";
    }

    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    @ResponseBody
    public String salvar(@RequestBody Funcionario funcionario) {
        service.salvar(funcionario);
        return "OK";
    }

    @RequestMapping(value = "/localiza", params = {"id"}, method = RequestMethod.GET)
    @ResponseBody
    public Funcionario localiza(@RequestParam Long id) {
        logger.debug("Id a localizar: {}", id);
        return service.recuperarPeloId(id);
    }

    @RequestMapping(value = {"/todas"}, method = RequestMethod.GET)
    @ResponseBody
    public List<Funcionario> todas() {
        return service.getTodosFuncionarios();
    }

    @RequestMapping(value = "/carregar/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Funcionario carregar(@PathVariable Long id) {
        return service.recuperarPeloId(id);
    }

    @RequestMapping(value = "/consulta/{cpf}", method = RequestMethod.GET)
    @ResponseBody
    public Funcionario BuscaFuncionarioPorCPF(@PathVariable String cpf) {
        return service.BuscaFuncionarioPorCPF(cpf);
    }

    @RequestMapping(value = "/relatorio/listaRelatorio", method = RequestMethod.GET)
    public void listaRelatorio(HttpServletResponse response) throws IOException, JRException, SQLException {
        listaRelatorio(response, dataSource.getConnection(), "listaFuncionarios.jasper", Funcionario.class, "listaFunctionario.pdf");
    }
    
    @RequestMapping(value = "/procurarPorNome/{nome}", method = RequestMethod.GET)
    @ResponseBody
    public DataPage<Funcionario> procurarPorNome(@PathVariable String nome) {
        return service.procurarPorNome(0, nome);
    }    
}
