package br.unicesumar.time5.service;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.unicesumar.time5.controller.DataPage;
import br.unicesumar.time5.entity.AssociadoPJ;
import br.unicesumar.time5.repository.AssociadoPJRepository;


@Service
@Transactional
public class AssociadoPJService extends AbstractService<AssociadoPJ>{
	
	
	private static final Logger logger = LoggerFactory.getLogger(AssociadoPJService.class);

    @Autowired
    private AssociadoPJRepository repository;
    

    public DataPage<AssociadoPJ> getAssociados(Integer pagina) {
        return findAll(pagina);
    }

    public void salvar(AssociadoPJ associado) {
    	repository.save(associado);
    }

    public AssociadoPJ recuperarPeloId(Long id) {
        return repository.findOne(id);
    }

    public void remover(AssociadoPJ associado) {
    	repository.delete(associado);
    }

    public List<AssociadoPJ> getTodosAssociados() {
        return repository.findAll();
    }

    public AssociadoPJ carregar(Long id) {
        return repository.findOne(id);
    }

    public AssociadoPJRepository getAssociadoRepository() {
        return repository;
    }

        @Override
    public Page<AssociadoPJ> executeQueryEncontrarPorNome(int pagina, int totalPorPagina, String nome) {
        return null;
    }

    @Override   
    public Page<AssociadoPJ> executeQueryEncontrarTodos(int pagina, int totalPorPagina) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, ordenarPorChave());

        return repository.findAll(pageRequest);
    }

}

