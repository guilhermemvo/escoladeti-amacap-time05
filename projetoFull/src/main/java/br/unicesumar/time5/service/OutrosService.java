/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unicesumar.time5.service;

import br.unicesumar.time5.controller.DataPage;
import br.unicesumar.time5.entity.Outros;
import br.unicesumar.time5.repository.OutrosRepository;
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
public class OutrosService extends AbstractService<Outros>{
        private static final Logger logger = LoggerFactory.getLogger(OutrosService.class);

    @Autowired
    private OutrosRepository outrosRepository;

    public DataPage<Outros> getOutross(Integer pagina) {
        return findAll(pagina);
    }

    public Outros salvar(Outros outros) {
        return outrosRepository.saveAndFlush(outros);
    }

    public Outros recuperarPeloId(Long id) {
        return outrosRepository.findOne(id);
    }

    public void remover(Outros outros) {
        outrosRepository.delete(outros);
    }

    public List<Outros> getTodosOutross() {
        return outrosRepository.findAll(new Sort(new Sort.Order(ASC, "nome")));
    }

    public Outros carregar(Long id) {
        return outrosRepository.findOne(id);
    }

    public OutrosRepository getOutrosRepository() {
        return outrosRepository;
    }

    @Override
    public Page<Outros> executeQueryEncontrarPorNome(int pagina, int totalPorPagina, String nome) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, ordenarPorChave());

        return outrosRepository.findByNomeLike(pageRequest, "%" + nome + "%");
    }

    @Override
    public Page<Outros> executeQueryEncontrarTodos(int pagina, int totalPorPagina) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, ordenarPorChave());

        return outrosRepository.findAll(pageRequest);
    }
    

    }
