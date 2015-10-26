/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unicesumar.time5.service;

import br.unicesumar.time5.controller.DataPage;
import static br.unicesumar.time5.controller.DataPage.pageRequestForAsc;
import br.unicesumar.time5.entity.UnidadeProducao;
import br.unicesumar.time5.repository.UnidadeProducaoRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
public class UnidadeProducaoService extends AbstractService<UnidadeProducao>{

    private static final Logger logger = LoggerFactory.getLogger(UnidadeProducaoService.class);

    @Autowired
    private UnidadeProducaoRepository unidadeProducaoRepository;

    public DataPage<UnidadeProducao> getUnidadeProducao(Integer pagina) {
        return findAll(pagina);
    }

    public void salvar(UnidadeProducao unidadeProducao) {
        unidadeProducaoRepository.save(unidadeProducao);
    }

    public UnidadeProducao recuperarPeloId(Long id) {
        return unidadeProducaoRepository.findOne(id);
    }

    public void remover(UnidadeProducao unidadeProducao) {
        unidadeProducaoRepository.delete(unidadeProducao);
    }

    public List<UnidadeProducao> getTodosUnidadeProducao() {
        return unidadeProducaoRepository.findAll(new Sort(new Sort.Order(ASC, "nome")));
    }

    public UnidadeProducao carregar(Long id) {
        return unidadeProducaoRepository.findOne(id);
    }

    public UnidadeProducaoRepository getUnidadeProducaoRepository() {
        return unidadeProducaoRepository;
    }

    @Override
    public Page<UnidadeProducao> executeQueryEncontrarPorNome(int pagina, int totalPorPagina, String nome) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, ordenarPorChave());

        return unidadeProducaoRepository.findByNomeLike(pageRequest, "%" + nome + "%");
    }

    @Override
    public Page<UnidadeProducao> executeQueryEncontrarTodos(int nome, int totalPorPagina) {
        final PageRequest pageRequest = new PageRequest(nome, totalPorPagina, ordenarPorChave());

        return unidadeProducaoRepository.findAll(pageRequest);
    }

}
