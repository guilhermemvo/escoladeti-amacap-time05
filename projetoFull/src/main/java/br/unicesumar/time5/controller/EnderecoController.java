/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unicesumar.time5.controller;

import br.unicesumar.time5.entity.Endereco;
import br.unicesumar.time5.service.EnderecoService;
import java.io.Serializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author felipesabadinifacina
 */
@Controller
@RequestMapping("/end")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class EnderecoController implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(EnderecoController.class);    
    
    private EnderecoService service;
    
    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    @ResponseBody
    public String salvar(@RequestBody Endereco end) {
        service.salvar(end);
        return "OK";
    }
}
