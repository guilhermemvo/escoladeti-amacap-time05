package br.unicesumar.time5.service;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.unicesumar.time5.controller.DataPage;
import br.unicesumar.time5.entity.PessoaJuridica;
import br.unicesumar.time5.repository.PessoaJuridicaRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@Transactional
public class PessoaJuridicaService extends AbstractService<PessoaJuridica> {

    private static final Logger logger = LoggerFactory.getLogger(PessoaJuridicaService.class);

    @Autowired
    private PessoaJuridicaRepository pessoaJuridicaRepository;

    public DataPage<PessoaJuridica> getPessoaJuridicas(Integer pagina) {
        return findAll(pagina);
    }

    public void salvar(PessoaJuridica pessoaJuridica) {
        pessoaJuridicaRepository.save(pessoaJuridica);
    }

    public PessoaJuridica recuperarPeloId(Long id) {
        return pessoaJuridicaRepository.findOne(id);
    }

    public void remover(PessoaJuridica pessoaJuridica) {
        pessoaJuridicaRepository.delete(pessoaJuridica);
    }

    public List<PessoaJuridica> getTodosPessoaJuridicas() {
        return pessoaJuridicaRepository.findAll(new Sort(new Sort.Order(ASC, "id")));
    }

    public PessoaJuridica carregar(Long id) {
        return pessoaJuridicaRepository.findOne(id);
    }

    public PessoaJuridica carregarCNPJ(String cnpj) {
        return pessoaJuridicaRepository.findByCnpj(cnpj);
    }

    public PessoaJuridicaRepository getPessoaJuridicaRepository() {
        return pessoaJuridicaRepository;
    }

    @Override
    public Page<PessoaJuridica> executeQueryEncontrarPorNome(int pagina, int totalPorPagina, String nome) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, ordenarPorChave());

        return pessoaJuridicaRepository.findByIdLike(pageRequest, "%" + id + "%");
    }

    @Override
    public Page<PessoaJuridica> executeQueryEncontrarTodos(int pagina, int totalPorPagina) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, ordenarPorChave());

        return pessoaJuridicaRepository.findAll(pageRequest);
    }
}
