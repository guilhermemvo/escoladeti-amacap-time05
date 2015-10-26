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
import br.unicesumar.time5.entity.AssociadoPFMovimentacao;
import br.unicesumar.time5.repository.AssociadoPFMovimentacaoRepository;
import br.unicesumar.time5.repository.AssociadoPFRepository;

@Service
@Transactional
public class AssociadoPFMovimentacaoService extends AbstractService<AssociadoPFMovimentacao> {

	private static final Logger logger = LoggerFactory.getLogger(AssociadoPFMovimentacaoService.class);

    @Autowired
    private AssociadoPFMovimentacaoRepository repository;
    private AssociadoPFRepository repositoryAssociadoPF;
    

    public DataPage<AssociadoPFMovimentacao> getAssociadoPFMovimentacao(Integer pagina) {
        return findAll(pagina);
    }

    public void salvar(AssociadoPFMovimentacao associadoPFMovimentacao) {
    	repository.save(associadoPFMovimentacao);
    }

    public AssociadoPFMovimentacao recuperarPeloId(Long id) {
        return repository.findOne(id);
    }
    
    public List<AssociadoPFMovimentacao> recuperarMovimentacaoPorAssociado(Long id) {
        return repository.findByAssociadoPF(repositoryAssociadoPF.findOne(id));
    }

    public List<AssociadoPFMovimentacao> getTodasMovimentacoes(Long id) {
    	return repository.findByAssociadoPF(id);
    }

    public List<AssociadoPFMovimentacao> getTodosAssociadoPFMovimentacao() {
        return repository.findAll();
    }

    public AssociadoPFMovimentacaoRepository getAssociadoPFMovimentacaoRepository() {
        return repository;
    }

        @Override
    public Page<AssociadoPFMovimentacao> executeQueryEncontrarPorNome(int pagina, int totalPorPagina, String nome) {
        return null;
    }

    @Override   
    public Page<AssociadoPFMovimentacao> executeQueryEncontrarTodos(int pagina, int totalPorPagina) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, ordenarPorChave());

        return repository.findAll(pageRequest);
    }

	
}
