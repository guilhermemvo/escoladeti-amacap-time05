package br.unicesumar.time5.controller;

import br.unicesumar.time5.entity.PedidoMaterial;
import br.unicesumar.time5.service.PedidoMaterialService;
import java.io.Serializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping("/pedidomaterial")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class PedidoMaterialController implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(PedidoMaterialController.class);

    @Autowired
    private PedidoMaterialService service;

    @RequestMapping(value = {"/listar/pag/{pagina}"}, method = RequestMethod.GET)
    @ResponseBody
    public DataPage<PedidoMaterial> listar(@PathVariable Integer pagina) {
        return service.todosRecebidos(pagina);
    }

    @RequestMapping(value = "/salvarunidadeproducao/{id}/{unidadeproducao}", method = RequestMethod.POST)
    @ResponseBody
    public String salvarUnidadeProducao(@PathVariable Long id, @PathVariable Long unidadeproducao) {
        service.salvarUnidadeProducao(id, unidadeproducao);
        return "OK";
    }

    @RequestMapping(value = "/salvarid/{id}/{recebido}", method = RequestMethod.POST)
    @ResponseBody
    public String salvarid(@PathVariable Long id, @PathVariable Boolean recebido) {
        service.salvarid(id, recebido);
        return "OK";
    }

}
