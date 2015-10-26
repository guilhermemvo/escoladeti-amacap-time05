
package br.unicesumar.time5.controller;

import br.unicesumar.time5.entity.Solicitante;
import br.unicesumar.time5.service.LivroService;
import br.unicesumar.time5.service.RelatorioService;
import br.unicesumar.time5.service.SolicitanteService;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRException;
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
@RequestMapping("/solicitante")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class SolicitanteController extends RelatorioService implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(SolicitanteController.class);

    @Autowired
    private SolicitanteService service;  
    
    @Autowired
    private DataSource dataSource;
    
    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    @ResponseBody
    public Solicitante salvar(@RequestBody Solicitante solicitante) {
        return service.salvar(solicitante);        
    }    
    
    @RequestMapping(value = {"/listar"}, method = RequestMethod.GET)
    @ResponseBody
    public DataPage<Solicitante> listar() {
        return service.findAll(0);
    }    
    
    @RequestMapping(value = "/remover", method = RequestMethod.DELETE)
    @ResponseBody
    public String remover(@RequestBody Solicitante solicitante) {
        service.remove(solicitante);
        return "OK";
    }    
    
    @RequestMapping(value = "/localiza", params = {"id"}, method = RequestMethod.GET)
    @ResponseBody
    public Solicitante localiza(@RequestParam Long id) {
        logger.debug("Id a localizar: {}", id);
        return service.findSolicitanteById(id);
    }
    
    @RequestMapping(value = {"/todos"}, method = RequestMethod.GET)
    @ResponseBody
    public List<Solicitante> todos() {
        return service.getAllSolicitante();
    }    
    
    @RequestMapping(value = "/carregar/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Solicitante carregar(@PathVariable Long id) {
        return service.findSolicitanteById(id);
    }

    @RequestMapping(value = "/consulta/{cpf}", method = RequestMethod.GET)
    @ResponseBody
    public Solicitante carregarCPF(@PathVariable String cpf) {
        return service.findSolicitanteByCpf(cpf);
    }

    @RequestMapping(value = "/procurarPorNome/{nome}", method = RequestMethod.GET)
    @ResponseBody
    public DataPage<Solicitante> procurarPorNome(@PathVariable String nome) {
        return service.procurarPorNome(0, nome);
    }    
    
    @RequestMapping(value = {"/listar/pag/{pagina}"}, method = RequestMethod.GET)
    @ResponseBody
    public DataPage<Solicitante> listar(@PathVariable Integer pagina) {
        return service.getPageSolicitante(pagina);
    }
    
    @RequestMapping(value = "/relatorio/listaRelatorio", method = RequestMethod.GET)
    public void listaRelatorio(HttpServletResponse response) throws IOException, JRException, SQLException {
        listaRelatorio(response, dataSource.getConnection(), "listaSolicitantes.jasper", LivroService.class, "listaSolicitantes.pdf");
    }
}
