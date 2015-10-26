package br.unicesumar.time5.service;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.unicesumar.time5.controller.DataPage;
import br.unicesumar.time5.entity.Pessoa;
import br.unicesumar.time5.entity.Pessoa;
import br.unicesumar.time5.repository.PessoaRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@Transactional
public class PessoaService extends AbstractService<Pessoa> {

    private static final Logger logger = LoggerFactory.getLogger(PessoaService.class);

    @Autowired
    private PessoaRepository pessoaRepository;

    public DataPage<Pessoa> getPessoas(Integer pagina) {
        return findAll(pagina);
    }

    public void salvar(Pessoa pessoa) {
        pessoaRepository.save(pessoa);
    }

    public Pessoa recuperarPeloId(Long id) {
        return pessoaRepository.findOne(id);
    }

    public void remover(Pessoa pessoa) {
        pessoaRepository.delete(pessoa);
    }

    public List<Pessoa> getTodosPessoas() {
        return pessoaRepository.findAll(new Sort(new Sort.Order(ASC, "id")));
    }

    public Pessoa carregar(Long id) {
        return pessoaRepository.findOne(id);
    }

    public PessoaRepository getPessoaRepository() {
        return pessoaRepository;
    }

    @Override
    public Page<Pessoa> executeQueryEncontrarPorNome(int pagina, int totalPorPagina, String nome) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, ordenarPorChave());

        return pessoaRepository.findByIdLike(pageRequest, "%" + id + "%");
    }

    @Override
    public Page<Pessoa> executeQueryEncontrarTodos(int pagina, int totalPorPagina) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, ordenarPorChave());

        return pessoaRepository.findAll(pageRequest);
    }}
