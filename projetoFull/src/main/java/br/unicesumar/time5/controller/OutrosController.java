package br.unicesumar.time5.controller;

import br.unicesumar.time5.entity.Outros;
import br.unicesumar.time5.service.OutrosService;
import br.unicesumar.time5.service.RelatorioService;
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

/**
 *
 * @author johnLima
 */

@Controller
@RequestMapping("/outros")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class OutrosController extends RelatorioService implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(OutrosController.class);

    @Autowired
    private OutrosService service;
    
    @Autowired
    private DataSource dataSource;
    
    @RequestMapping(value = {"/listar"}, method = RequestMethod.GET)
    @ResponseBody
    public DataPage<Outros> listar() {
        return service.getOutross(0);
    }

    @RequestMapping(value = {"/listar/pag/{pagina}"}, method = RequestMethod.GET)
    @ResponseBody
    public DataPage<Outros> listar(@PathVariable Integer pagina) {
        return service.findAll(pagina);
    }

    
    @RequestMapping(value = "/remover", method = RequestMethod.DELETE)
    @ResponseBody
    public String remover(@RequestBody Outros outros) {
        service.remover(outros);
        return "OK";
    }

    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    @ResponseBody
    public Outros salvar(@RequestBody Outros outros) {
        return service.salvar(outros);
    }
    
    @RequestMapping(value = "/localiza", params = {"id"}, method = RequestMethod.GET)
    @ResponseBody
    public Outros localiza(@RequestParam Long id) {
        logger.debug("Id a localizar: {}", id);
        return service.recuperarPeloId(id);
    }

    @RequestMapping(value = {"/todos"}, method = RequestMethod.GET)
    @ResponseBody
    public List<Outros> todos() {
        return service.getTodosOutross();
    }

    @RequestMapping(value = "/carregar/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Outros carregar(@PathVariable Long id) {
        return service.recuperarPeloId(id);
    }
    
    @RequestMapping(value = "/relatorio/listaOutros", method = RequestMethod.GET)
    public void listaRelatorio(HttpServletResponse response) throws IOException, JRException, SQLException {
        listaRelatorio(response, dataSource.getConnection(), "listaOutros.jasper", Outros.class, "listaOutros.pdf");
    }

}
