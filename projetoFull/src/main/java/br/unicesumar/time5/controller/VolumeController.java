package br.unicesumar.time5.controller;

import br.unicesumar.time5.entity.Volume;
import br.unicesumar.time5.service.VolumeService;
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
@RequestMapping("/volume")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class VolumeController implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(VolumeController.class);
    
    @Autowired
    private VolumeService service;
    
    @RequestMapping(value = {"/listar/pag/{pagina}"}, method = RequestMethod.GET)
    @ResponseBody
    public DataPage<Volume> listar(@PathVariable Integer pagina) {
        return service.getVolumes(pagina);
    }
    
    @RequestMapping(value = {"/procurar/data/{de}/{ate}"}, method = RequestMethod.GET)
    @ResponseBody
    public List<Volume> procurarPorData(@PathVariable String de, @PathVariable String ate) {
        return service.procurarPorData(de, ate);
    }
    
    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    @ResponseBody
    public String salvar(@RequestBody Volume volume) {
        service.salvar(volume);
        return "OK";
    }
    
    @RequestMapping(value = "/carregar/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Volume carregar(@PathVariable Long id) {
        return service.recuperarPeloId(id);
    }
}
