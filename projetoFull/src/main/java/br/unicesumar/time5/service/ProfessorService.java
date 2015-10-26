package br.unicesumar.time5.service;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.unicesumar.time5.controller.DataPage;
import br.unicesumar.time5.entity.Professor;
import br.unicesumar.time5.repository.ProfessorRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import static org.springframework.data.domain.Sort.Direction.ASC;

@Service
@Transactional
public class ProfessorService extends AbstractService<Professor> {

    private static final Logger logger = LoggerFactory.getLogger(ProfessorService.class);

    @Autowired
    private ProfessorRepository professorRepository;

    public DataPage<Professor> getProfessors(Integer pagina) {
        return findAll(pagina);
    }

    public Professor salvar(Professor professor) {
//        Professor p1 = professorRepository.findByCpf(professor.getCpf());
//        if (p1 == null) {
//            professorRepository.save(professor);
//        }
        return professorRepository.saveAndFlush(professor);
//        professorRepository.save(professor);
    }

    public Professor recuperarPeloId(Long id) {
        return professorRepository.findOne(id);
    }

    public void remover(Professor professor) {
        professorRepository.delete(professor);
    }

    public List<Professor> getTodosProfessors() {
        return professorRepository.findAll(new Sort(new Sort.Order(ASC, "nome")));
    }

    public Professor carregar(Long id) {
        return professorRepository.findOne(id);
    }

    public ProfessorRepository getProfessorRepository() {
        return professorRepository;
    }

    public Professor carregarCPF(String cpf) {
        return professorRepository.findByCpf(cpf);
    }

    @Override
    public Page<Professor> executeQueryEncontrarPorNome(int pagina, int totalPorPagina, String nome) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, ordenarPorChave());

        return professorRepository.findByNomeLike(pageRequest, "%" + nome.toUpperCase() + "%");
    }

    @Override
    public Page<Professor> executeQueryEncontrarTodos(int pagina, int totalPorPagina) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, ordenarPorChave());

        return professorRepository.findAll(pageRequest);
    }

    public DataPage<Professor> procurarPorNome(int pagina, String nome) {
        return findByNameLike(pagina, nome);
    }

    public List<Professor> carregarNomeCpf(String value) {
        if (value.length() >= 3) {
            return professorRepository.findByCpfLikeOrNomeLikeIgnoreCaseOrderByNomeAsc("%" + value + "%", "%" + value + "%");
        } else {
            return null;
        }

    }

}
