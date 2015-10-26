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
import br.unicesumar.time5.entity.AssociadoPJMovimentacao;
import br.unicesumar.time5.repository.AssociadoPJMovimentacaoRepository;
import br.unicesumar.time5.repository.AssociadoPJRepository;

@Service
@Transactional
public class AssociadoPJMovimentacaoService extends AbstractService<AssociadoPJMovimentacao> {

	private static final Logger logger = LoggerFactory.getLogger(AssociadoPFMovimentacaoService.class);

    @Autowired
    private AssociadoPJMovimentacaoRepository repository;
    private AssociadoPJRepository repositoryAssociadoPJ;
    

    public DataPage<AssociadoPJMovimentacao> getAssociadoPJMovimentacao(Integer pagina) {
        return findAll(pagina);
    }

    public void salvar(AssociadoPJMovimentacao associadoPJMovimentacao) {
    	repository.save(associadoPJMovimentacao);
    }

    public AssociadoPJMovimentacao recuperarPeloId(Long id) {
        return repository.findOne(id);
    }
    
    public List<AssociadoPJMovimentacao> recuperarMovimentacaoPorAssociado(Long id) {
        return repository.findByAssociadoPJ(repositoryAssociadoPJ.findOne(id));
    }

    public List<AssociadoPJMovimentacao> getTodasMovimentacoes(Long id) {
    	return repository.findByAssociadoPJ(id);
    }

    public List<AssociadoPJMovimentacao> getTodosAssociadoPJMovimentacao() {
        return repository.findAll();
    }

    public AssociadoPJMovimentacaoRepository getAssociadoPJMovimentacaoRepository() {
        return repository;
    }

        @Override
    public Page<AssociadoPJMovimentacao> executeQueryEncontrarPorNome(int pagina, int totalPorPagina, String nome) {
        return null;
    }

    @Override   
    public Page<AssociadoPJMovimentacao> executeQueryEncontrarTodos(int pagina, int totalPorPagina) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, ordenarPorChave());

        return repository.findAll(pageRequest);
    }

	
}
