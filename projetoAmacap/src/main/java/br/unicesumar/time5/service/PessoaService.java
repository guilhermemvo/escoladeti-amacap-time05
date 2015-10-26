package br.unicesumar.time5.service;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.unicesumar.time5.controller.DataPage;
import static br.unicesumar.time5.controller.DataPage.pageRequestForAsc;
import br.unicesumar.time5.entity.Pessoa;
import br.unicesumar.time5.repository.PessoaRepository;
import java.util.List;
import org.springframework.data.domain.Sort;
import static org.springframework.data.domain.Sort.Direction.ASC;

@Service
@Transactional
public class PessoaService {

    private static final Logger logger = LoggerFactory.getLogger(PessoaService.class);

    @Autowired
    private PessoaRepository pessoaRepository;

    public DataPage<Pessoa> getPessoas(Integer pagina) {
        return new DataPage<>(pessoaRepository.findAll(pageRequestForAsc(pagina, "nome")));
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

    public List<Pessoa> getTodasPessoas() {
        return pessoaRepository.findAll(new Sort(new Sort.Order(ASC, "nome")));
    }

    public Pessoa carregar(Long id) {
        return pessoaRepository.findOne(id);
    }

    public PessoaRepository getPessoaRepository() {
        return pessoaRepository;
    }

}
