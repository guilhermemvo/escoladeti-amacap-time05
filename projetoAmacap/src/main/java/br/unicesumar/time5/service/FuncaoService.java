/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unicesumar.time5.service;

import br.unicesumar.time5.controller.DataPage;
import static br.unicesumar.time5.controller.DataPage.pageRequestForAsc;
import br.unicesumar.time5.entity.Funcao;
import br.unicesumar.time5.repository.FuncaoRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import static org.springframework.data.domain.Sort.Direction.ASC;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author johnLima
 */
@Service
@Transactional
public class FuncaoService {

    private static final Logger logger = LoggerFactory.getLogger(FuncaoService.class);

    @Autowired
    private FuncaoRepository funcaoRepository;

    public DataPage<Funcao> getFuncao(Integer pagina) {
        return new DataPage<>(funcaoRepository.findAll(pageRequestForAsc(pagina, "nome")));
    }

    public void salvar(Funcao funcao) {
        funcaoRepository.save(funcao);
    }

    public Funcao recuperarPeloId(Long id) {
        return funcaoRepository.findOne(id);
    }

    public void remover(Funcao funcao) {
        funcaoRepository.delete(funcao);
    }

    public List<Funcao> getTodosFuncao() {
        return funcaoRepository.findAll(new Sort(new Sort.Order(ASC, "nome")));
    }

    public Funcao carregar(Long id) {
        return funcaoRepository.findOne(id);
    }

    public FuncaoRepository getFuncaoRepository() {
        return funcaoRepository;
    }

}
