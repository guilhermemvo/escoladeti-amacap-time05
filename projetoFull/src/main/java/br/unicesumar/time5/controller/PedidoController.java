package br.unicesumar.time5.controller;

import br.unicesumar.time5.entity.Pedido;
import br.unicesumar.time5.service.PedidoService;
import br.unicesumar.time5.types.Status;

import java.io.Serializable;
import java.util.Date;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping("/pedido")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class PedidoController implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(PedidoController.class);
    @Autowired
    private PedidoService service;

    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    @ResponseBody
    public String salvar(@RequestBody Pedido pedido) {
        if (pedido.getDataPedido() == null) {
            Date data = new Date();
            pedido.setDataPedido(data);
        } else {
            Date data = pedido.getDataPedido();
            data.setDate(data.getDate() + 1);
            pedido.setDataPedido(data);
        }
        service.salvar(pedido);
        return "OK";
    }
         
    @RequestMapping(value = "/cancelar", method = RequestMethod.POST)
    @ResponseBody
    public String cancelar(@RequestBody Long id) {       
    	 String status = service.cancelar(id);        
         return status;
    }
    
    @RequestMapping(value = {"/listar"}, method = RequestMethod.GET)
    @ResponseBody
    public List<Pedido> listar() {
        return service.todos();
    }

    @RequestMapping(value = {"/mudarstatusproducao"}, method = RequestMethod.POST)
    @ResponseBody
    public String mudarStatusParaProducao(@RequestBody Long id) {
        service.mudarStatusParaProducao(id);
        return "OK";
    }

    @RequestMapping(value = {"/listar/pag/{pagina}"}, method = RequestMethod.GET)
    @ResponseBody
    public DataPage<Pedido> listar(@PathVariable Integer pagina) {
        return service.getPedidos(pagina);
    }

    @RequestMapping(value = "/remover", method = RequestMethod.DELETE)
    @ResponseBody
    public String remover(@RequestBody Long id) {
        service.remover(id);
        return "OK";
    }

    @RequestMapping(value = "/carregar/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Pedido carregar(@PathVariable Long id) {
        return service.recuperarPedidoPeloId(id);
    }

    @RequestMapping(value = {"/procurar/data/{de}/{ate}"}, method = RequestMethod.GET)
    @ResponseBody
    public List<Pedido> procurarPorData(@PathVariable String de, @PathVariable String ate) {

        logger.info("Pedido.......:");
        logger.info("Pedido:" + de);
        logger.info("Pedido.......:");
        logger.info("Pedido:" + ate);

        return service.procurarPorData(de, ate);
    }

    @RequestMapping(value = {"/pendente/total/"}, method = RequestMethod.GET)
    @ResponseBody
    public Integer getCountPendentes() {
        return service.getCountPendentes();
    }

}
