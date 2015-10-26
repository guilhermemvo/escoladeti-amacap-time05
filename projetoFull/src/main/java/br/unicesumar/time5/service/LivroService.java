/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unicesumar.time5.service;

import br.unicesumar.time5.controller.DataPage;
import br.unicesumar.time5.entity.Arquivo;
import br.unicesumar.time5.entity.Livro;
import br.unicesumar.time5.repository.LivroRepository;
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
public class LivroService extends AbstractService<Livro>{
        private static final Logger logger = LoggerFactory.getLogger(LivroService.class);

    @Autowired
    private LivroRepository livroRepository;

    public DataPage<Livro> getLivros(Integer pagina) {
        return findAll(pagina);
    }

    public Livro salvar(Livro livro) {  
         return livroRepository.saveAndFlush(livro);
    }

    public Livro recuperarPeloId(Long id) {
        return livroRepository.findOne(id);
    }

    public void remover(Livro livro) {
        livroRepository.delete(livro);
    }

    public List<Livro> getTodosLivros() {
        return livroRepository.findAll(new Sort(new Sort.Order(ASC, "nome")));
    }

    public Livro carregar(Long id) {
        return livroRepository.findOne(id);
    }

    public LivroRepository getLivroRepository() {
        return livroRepository;
    }

    @Override
    public Page<Livro> executeQueryEncontrarPorNome(int pagina, int totalPorPagina, String nome) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, ordenarPorChave());

        return livroRepository.findByNomeLike(pageRequest, "%" + nome + "%");
    }

    @Override
    public Page<Livro> executeQueryEncontrarTodos(int pagina, int totalPorPagina) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, ordenarPorChave());

        return livroRepository.findAll(pageRequest);
    }

    
}
