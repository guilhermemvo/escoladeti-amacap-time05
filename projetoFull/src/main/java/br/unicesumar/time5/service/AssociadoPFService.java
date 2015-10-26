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
import br.unicesumar.time5.entity.AssociadoPF;
import br.unicesumar.time5.entity.PessoaFisica;
import br.unicesumar.time5.repository.AssociadoPFRepository;
import br.unicesumar.time5.repository.PessoaFisicaRepository;
import br.unicesumar.time5.vo.AssociadoPfVO;

@Service
@Transactional
public class AssociadoPFService extends AbstractService<AssociadoPF> {

    private static final Logger logger = LoggerFactory.getLogger(AssociadoPFService.class);

    @Autowired
    private AssociadoPFRepository repository;

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepository;

    public DataPage<AssociadoPF> getAssociados(Integer pagina) {
        return findAll(pagina);
    }

    public void salvar(AssociadoPF associado) {
        repository.save(associado);
    }

    public AssociadoPF recuperarPeloId(Long id) {
        return repository.findOne(id);
    }

    public void remover(AssociadoPF associado) {
        repository.delete(associado);
    }

    public List<AssociadoPF> getTodosAssociados() {
        return repository.findAll();
    }

    public AssociadoPF carregar(Long id) {
        return repository.findOne(id);
    }

    public AssociadoPFRepository getAssociadoRepository() {
        return repository;
    }

    @Override
    public Page<AssociadoPF> executeQueryEncontrarPorNome(int pagina, int totalPorPagina, String nome) {
        return null;
    }

    @Override
    public Page<AssociadoPF> executeQueryEncontrarTodos(int pagina, int totalPorPagina) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, ordenarPorChave());

        return repository.findAll(pageRequest);
    }

    public void salvarPorVO(AssociadoPfVO associado) {
        PessoaFisica pessoaFisica = pessoaFisicaRepository.findOne(associado.getIdPessoaFisica());
        AssociadoPF associadoPF = new AssociadoPF();

        associadoPF.setId(associado.getId());
        associadoPF.setAtivo(associado.isAtivo());
        associadoPF.setPessoaFisica(pessoaFisica);
        associadoPF.setValorContribuicao(associado.getValorContribuicao());

        salvar(associadoPF);
    }

}
