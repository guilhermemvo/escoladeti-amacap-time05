package br.unicesumar.time5.service;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.unicesumar.time5.controller.DataPage;
import br.unicesumar.time5.entity.Aluno;
import br.unicesumar.time5.repository.AlunoRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import static org.springframework.data.domain.Sort.Direction.ASC;

@Service
@Transactional
public class AlunoService extends AbstractService<Aluno> {

    private static final Logger logger = LoggerFactory.getLogger(AlunoService.class);

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private TelefoneService telefoneService;

    public DataPage<Aluno> getAlunos(Integer pagina) {
        return findAll(pagina);
    }

    public Aluno salvar(Aluno aluno) {
        return alunoRepository.saveAndFlush(aluno);
        //alunoRepository.save(aluno);
    }

    public Aluno recuperarPeloId(Long id) {
        return alunoRepository.findOne(id);
    }

    public void remover(Aluno aluno) {
        alunoRepository.delete(aluno);
    }

    public List<Aluno> getTodosAlunos() {
        return alunoRepository.findAll(new Sort(new Sort.Order(ASC, "nome")));
    }

    public Aluno carregar(Long id) {
        return alunoRepository.findOne(id);
    }

    public Aluno carregarCPF(String cpf) {
        return alunoRepository.findByCpf(cpf);
    }

    public AlunoRepository getAlunoRepository() {
        return alunoRepository;
    }

    @Override
    public Page<Aluno> executeQueryEncontrarPorNome(int pagina, int totalPorPagina, String nome) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, ordenarPorChave());

        return alunoRepository.findByNomeLike(pageRequest, "%" + nome.toUpperCase() + "%");
    }

    @Override
    public Page<Aluno> executeQueryEncontrarTodos(int pagina, int totalPorPagina) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, ordenarPorChave());

        return alunoRepository.findAll(pageRequest);
    }

    public DataPage<Aluno> procurarPorNome(int pagina, String nome) {
        return findByNameLike(pagina, nome);
    }

    public List<Aluno> carregarNomeCpf(String value) {
        if (value.length() >= 3) {
            return alunoRepository.findByCpfLikeOrNomeLikeIgnoreCaseOrderByNomeAsc("%" + value + "%", "%" + value + "%");
        } else {
            return null;
        }
    }
}
