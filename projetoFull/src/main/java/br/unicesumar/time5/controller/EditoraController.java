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

import br.unicesumar.time5.entity.Editora;
import br.unicesumar.time5.service.EditoraService;
import br.unicesumar.time5.service.LivroService;
import br.unicesumar.time5.service.RelatorioService;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRException;

@Controller
@RequestMapping("/editora")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class EditoraController extends RelatorioService implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(EditoraController.class);

    @Autowired
    private EditoraService service;
    
    @Autowired
    private DataSource dataSource;

    @RequestMapping(value = {"/listar"}, method = RequestMethod.GET)
    @ResponseBody
    public DataPage<Editora> listar() {
        return service.getEditoras(0);
    }

    @RequestMapping(value = {"/listar/pag/{pagina}"}, method = RequestMethod.GET)
    @ResponseBody
    public DataPage<Editora> listar(@PathVariable Integer pagina) {
        return service.getEditoras(pagina);
    }

    @RequestMapping(value = "/remover", method = RequestMethod.DELETE)
    @ResponseBody
    public String remover(@RequestBody Editora editora) {
        service.remover(editora);
        return "OK";
    }

    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    @ResponseBody
    public String salvar(@RequestBody Editora editora) {
        service.salvar(editora);
        return "OK";
    }

    @RequestMapping(value = "/localiza", params = {"id"}, method = RequestMethod.GET)
    @ResponseBody
    public Editora localiza(@RequestParam Long id) {
        logger.debug("Id a localizar: {}", id);
        return service.recuperarPeloId(id);
    }

    @RequestMapping(value = {"/todas"}, method = RequestMethod.GET)
    @ResponseBody
    public List<Editora> todas() {
        return service.getTodosEditoras();
    }

    @RequestMapping(value = "/carregar/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Editora carregar(@PathVariable Long id) {
        return service.recuperarPeloId(id);
    }

    @RequestMapping(value = "/consulta/{cnpj}", method = RequestMethod.GET)
    @ResponseBody
    public Editora BuscaEditoraPorCNPJ(@PathVariable String cnpj) {
        return service.BuscaEditoraPorCNPJ(cnpj);
    }
    
    @RequestMapping(value = "/procurarPorNome/{nome}", method = RequestMethod.GET)
    @ResponseBody
    public DataPage<Editora> procurarPorNome(@PathVariable String nome) {
        return service.procurarPorNome(0, nome);
    }    
    
    @RequestMapping(value = "/relatorio/listaRelatorio", method = RequestMethod.GET)
    public void listaRelatorio(HttpServletResponse response) throws IOException, JRException, SQLException {
        listaRelatorio(response, dataSource.getConnection(), "listaEditoras.jasper", LivroService.class, "listaEditoras.pdf");
    }
}
