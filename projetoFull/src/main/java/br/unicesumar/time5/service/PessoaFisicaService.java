package br.unicesumar.time5.service;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.unicesumar.time5.controller.DataPage;
import br.unicesumar.time5.entity.PessoaFisica;
import br.unicesumar.time5.repository.PessoaFisicaRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@Transactional
public class PessoaFisicaService extends AbstractService<PessoaFisica> {

    private static final Logger logger = LoggerFactory.getLogger(PessoaFisicaService.class);

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepository;

    public DataPage<PessoaFisica> getPessoaFisicas(Integer pagina) {
        return findAll(pagina);
    }

    public void salvar(PessoaFisica pessoaFisica) {
        pessoaFisicaRepository.save(pessoaFisica);
    }

    public PessoaFisica recuperarPeloId(Long id) {
        return pessoaFisicaRepository.findOne(id);
    }

    public void remover(PessoaFisica pessoaFisica) {
        pessoaFisicaRepository.delete(pessoaFisica);
    }

    public List<PessoaFisica> getTodosPessoaFisicas() {
        return pessoaFisicaRepository.findAll(new Sort(new Sort.Order(ASC, "id")));
    }

    public List<PessoaFisica> getPessoaCPF(String cpf) {
        return pessoaFisicaRepository.findByCpfLike(cpf);
    }

    public PessoaFisica carregar(Long id) {
        return pessoaFisicaRepository.findOne(id);
    }

    public PessoaFisica carregarCPF(String cpf) {
        return pessoaFisicaRepository.findByCpf(cpf);
    }

    public List<PessoaFisica> carregarNomeCpf(String value) {
        if (value.length() >= 3) {
            return pessoaFisicaRepository.findByCpfLikeOrNomeLikeIgnoreCaseOrderByNomeAsc("%" + value + "%", "%" + value + "%");
        } else {
            return null;
        }
    }

    public PessoaFisicaRepository getPessoaFisicaRepository() {
        return pessoaFisicaRepository;
    }

    @Override
    public Page<PessoaFisica> executeQueryEncontrarPorNome(int pagina, int totalPorPagina, String nome) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, ordenarPorChave());

        return pessoaFisicaRepository.findByIdLike(pageRequest, "%" + id + "%");
    }

    @Override
    public Page<PessoaFisica> executeQueryEncontrarTodos(int pagina, int totalPorPagina) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, ordenarPorChave());

        return pessoaFisicaRepository.findAll(pageRequest);
    }
}
