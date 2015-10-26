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
import br.unicesumar.time5.entity.MateriaPrima;
import br.unicesumar.time5.service.MateriaPrimaService;
import br.unicesumar.time5.service.RelatorioService;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRException;

@Controller
@RequestMapping("/materiaprima")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class MateriaPrimaController extends RelatorioService implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(MateriaPrimaController.class);

    @Autowired
    private MateriaPrimaService service;
    @Autowired
    private DataSource dataSource;

    @RequestMapping(value = {"/listar"}, method = RequestMethod.GET)
    @ResponseBody
    public DataPage<MateriaPrima> listar() {
        return service.getMateriaPrima(0);
    }

    @RequestMapping(value = {"/listar/pag/{pagina}"}, method = RequestMethod.GET)
    @ResponseBody
    public DataPage<MateriaPrima> listar(@PathVariable Integer pagina) {
        return service.getMateriaPrima(pagina);
    }

    @RequestMapping(value = "/remover", method = RequestMethod.DELETE)
    @ResponseBody
    public String remover(@RequestBody MateriaPrima materiaPrima) {
        service.remover(materiaPrima);
        return "OK";
    }

    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    @ResponseBody
    public String salvar(@RequestBody MateriaPrima materiaPrima) {
        service.salvar(materiaPrima);
        return "OK";
    }

    @RequestMapping(value = "/localiza", params = {"id"}, method = RequestMethod.GET)
    @ResponseBody
    public MateriaPrima localiza(@RequestParam Long id) {
        logger.debug("Id a localizar: {}", id);
        return service.recuperarPeloId(id);
    }

    @RequestMapping(value = {"/todos"}, method = RequestMethod.GET)
    @ResponseBody
    public List<MateriaPrima> todos() {
        return service.getTodasMateriaPrima();
    }

    @RequestMapping(value = "/carregar/{id}", method = RequestMethod.GET)
    @ResponseBody
    public MateriaPrima carregar(@PathVariable Long id) {
        return service.recuperarPeloId(id);
    }

    @RequestMapping(value = "/relatorio/listaRelatorio", method = RequestMethod.GET)
    public void listaRelatorio(HttpServletResponse response) throws IOException, JRException, SQLException {
        listaRelatorio(response, dataSource.getConnection(), "listaMateriasPrimas.jasper", MateriaPrima.class, "listaMateriaPrima.pdf");
    }

}
