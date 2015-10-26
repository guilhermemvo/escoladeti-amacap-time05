package br.unicesumar.time5.controller;

import br.unicesumar.time5.entity.Nota;
import br.unicesumar.time5.service.NotaService;
import java.io.Serializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping("/nota")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class NotaController implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(NotaController.class);
    
    @Autowired
    private NotaService service;
    
    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    @ResponseBody
    public String salvar(@RequestBody Nota nota) {
        service.salvar(nota);
        return "OK";
    }
}
