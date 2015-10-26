package br.unicesumar.time5.controller;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import br.unicesumar.time5.entity.Instrutor;
import br.unicesumar.time5.service.InstrutorService;
import br.unicesumar.time5.service.LivroService;
import br.unicesumar.time5.service.RelatorioService;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRException;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/instrutor")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class InstrutorController extends RelatorioService implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(InstrutorController.class);

    @Autowired
    private InstrutorService service;
    
    @Autowired
    private DataSource dataSource;

    @RequestMapping(value = {"/listar"}, method = RequestMethod.GET)
    @ResponseBody
    public DataPage<Instrutor> listar() {
        return service.getInstrutors(0);
    }

    @RequestMapping(value = {"/listar/pag/{pagina}"}, method = RequestMethod.GET)
    @ResponseBody
    public DataPage<Instrutor> listar(@PathVariable Integer pagina) {
        return service.getInstrutors(pagina);
    }

    @RequestMapping(value = "/remover", method = RequestMethod.DELETE)
    @ResponseBody
    public String remover(@RequestBody Instrutor instrutor) {
        service.remover(instrutor);
        return "OK";
    }

    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    @ResponseBody
    public String salvar(@RequestBody Instrutor instrutor) {
        service.salvar(instrutor);
        return "OK";
    }

    @RequestMapping(value = "/localiza", params = {"id"}, method = RequestMethod.GET)
    @ResponseBody
    public Instrutor localiza(@RequestParam Long id) {
        logger.debug("Id a localizar: {}", id);
        return service.recuperarPeloId(id);
    }

    @RequestMapping(value = {"/todas"}, method = RequestMethod.GET)
    @ResponseBody
    public List<Instrutor> todas() {
        return service.getTodosInstrutors();
    }

    @RequestMapping(value = "/carregar/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Instrutor carregar(@PathVariable Long id) {
        return service.recuperarPeloId(id);
    }

    @RequestMapping(value = "/consulta/{cpf}", method = RequestMethod.GET)
    @ResponseBody
    public Instrutor BuscaInstrutorPorCPF(@PathVariable String cpf) {
        return service.BuscaInstrutorPorCPF(cpf);
    }

    @RequestMapping(value = "/procurarPorNome/{nome}", method = RequestMethod.GET)
    @ResponseBody
    public DataPage<Instrutor> procurarPorNome(@PathVariable String nome) {
        return service.procurarPorNome(0, nome);
    }
    
    @RequestMapping(value = "/relatorio/listaRelatorio", method = RequestMethod.GET)
    public void listaRelatorio(HttpServletResponse response) throws IOException, JRException, SQLException {
        listaRelatorio(response, dataSource.getConnection(), "listaInstrutores.jasper", LivroService.class, "listaInstrutores.pdf");
    }
}