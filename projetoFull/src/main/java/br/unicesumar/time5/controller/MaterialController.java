package br.unicesumar.time5.controller;

import br.unicesumar.time5.entity.Material;
import br.unicesumar.time5.service.MaterialService;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping("/material")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class MaterialController implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(MaterialController.class);

    @Autowired
    private MaterialService service;

    @RequestMapping(value = "/localizarPorNomePndl/{valor}", method = RequestMethod.GET)
    @ResponseBody
    public List<Material> localiza(@PathVariable String valor) {
        return service.procurarMaterial(valor);
    }

    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    @ResponseBody
    public List<Material> salvar(@RequestBody List<Material> material) {
        return service.salvar(material);
    }

    @RequestMapping(value = {"/listar/pag/{pagina}"}, method = RequestMethod.GET)
    @ResponseBody
    public DataPage<Material> listar(@PathVariable Integer pagina) {
        return service.findAll(pagina);
    }

}
