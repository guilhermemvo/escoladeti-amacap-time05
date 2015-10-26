package br.unicesumar.time5.controller;

import br.unicesumar.time5.entity.AgendaEvento;
import br.unicesumar.time5.entity.Evento;
import br.unicesumar.time5.entity.Foto;
import br.unicesumar.time5.entity.Presenca;
import br.unicesumar.time5.service.FotoService;
import br.unicesumar.time5.service.EventoService;
import br.unicesumar.time5.service.RelatorioService;
import br.unicesumar.time5.vo.PresencaVO;
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
@RequestMapping("/evento")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class EventoController extends RelatorioService implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(EventoController.class);

    @Autowired
    private EventoService service;

    @Autowired
    private FotoService fotoService;

    @Autowired
    private DataSource dataSource;

    @RequestMapping(value = {"/listar"}, method = RequestMethod.GET)
    @ResponseBody
    public DataPage<Evento> listar() {
        return service.getEventos(0);
    }

    @RequestMapping(value = {"/listar/pag/{pagina}"}, method = RequestMethod.GET)
    @ResponseBody
    public DataPage<Evento> listar(@PathVariable Integer pagina) {
        return service.getEventos(pagina);
    }

    @RequestMapping(value = "/remover", method = RequestMethod.DELETE)
    @ResponseBody
    public String remover(@RequestBody Evento evento) {
        service.remover(evento);
        return "OK";
    }

    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    @ResponseBody
    public String salvar(@RequestBody Evento evento) {
        service.salvar(evento);
        return "OK";
    }

    @RequestMapping(value = "/localiza", params = {"id"}, method = RequestMethod.GET)
    @ResponseBody
    public Evento localiza(@RequestParam Long id) {
        logger.debug("Id a localizar: {}", id);
        return service.recuperarPeloId(id);
    }

    @RequestMapping(value = {"/todos"}, method = RequestMethod.GET)
    @ResponseBody
    public List<Evento> todos() {
        System.out.println("Aquiii: " + service.getTodosEventos().get(0) + " ]");
        return service.getTodosEventos();
    }

    @RequestMapping(value = "/carregar/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Evento carregar(@PathVariable Long id) {
        return service.recuperarPeloId(id);
    }

    @RequestMapping(value = "/galeria/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Evento listaFotosGaleria(@PathVariable Long id) {
        return service.recuperarPeloId(id);
    }

    @RequestMapping(value = "/galeria/foto/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Foto fotoGaleria(@PathVariable Long id) {
        return fotoService.recuperarPeloId(id);
    }

    @RequestMapping(value = "/galeria/foto/remover", method = RequestMethod.DELETE)
    @ResponseBody
    public String removerFotoGaleria(@RequestBody Foto foto) {
        fotoService.remover(foto);
        return "OK";
    }

    @RequestMapping(value = "/galeria/salvar", method = RequestMethod.POST)
    @ResponseBody
    public String galeriaSalvar(@RequestBody Foto galeria) {
        fotoService.salvar(galeria);
        return "OK";
    }

    @RequestMapping(value = "/procurarPorNome/{nome}", method = RequestMethod.GET)
    @ResponseBody
    public DataPage<Evento> procurarPorNome(@PathVariable String nome) {
        return service.procurarPorNome(0, nome);
    }

    @RequestMapping(value = "/relatorio/listaParticipante/{id}", method = RequestMethod.GET)
    public void listaParticipante(HttpServletResponse response, @PathVariable Long id) throws IOException, JRException, SQLException {
        listaID(response, dataSource.getConnection(), id, "listaParticipantes.jasper", Evento.class, "listaParticipantes.pdf", "idEvento");
    }

    @RequestMapping(value = "/relatorio/listaEvento", method = RequestMethod.GET)
    public void listaEvento(HttpServletResponse response) throws IOException, JRException, SQLException {
        listaRelatorio(response, dataSource.getConnection(), "listaEventos.jasper", Evento.class, "listaEventos.pdf");
    }

    @RequestMapping(value = "/carregaPresencas/{idAgendaEvento}", method = RequestMethod.GET)
    @ResponseBody
    public List<Presenca> carregaPresenca(@PathVariable Long idAgendaEvento) {
        return service.buscaPresencas(idAgendaEvento);
    }

    @RequestMapping(value = "/atualizarPresenca", method = RequestMethod.POST)
    @ResponseBody
    public String atualizarPresenca(@RequestBody List<PresencaVO> presencas) {
        service.salvarPresencas(presencas);
        return "OK";
    }
    
    @RequestMapping(value = "/carregaAgenda/{idEvento}", method = RequestMethod.GET)
    @ResponseBody
    public List<AgendaEvento> carregaAgenda(@PathVariable Long idEvento) {
        return service.buscaAgenda(idEvento);
    }
}
