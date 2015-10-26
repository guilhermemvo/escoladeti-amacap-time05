package br.unicesumar.time5.service;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.unicesumar.time5.controller.DataPage;
import static br.unicesumar.time5.controller.DataPage.pageRequestForAsc;
import br.unicesumar.time5.entity.Disciplina;
import br.unicesumar.time5.repository.DisciplinaRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import static org.springframework.data.domain.Sort.Direction.ASC;

@Service
@Transactional
public class DisciplinaService extends AbstractService<Disciplina> {

    private static final Logger logger = LoggerFactory.getLogger(DisciplinaService.class);

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    public DataPage<Disciplina> getDisciplinas(Integer pagina) {
        return findAll(pagina);
    }

    public void salvar(Disciplina disciplina) {
        disciplinaRepository.save(disciplina);
    }

    public Disciplina recuperarPeloId(Long id) {
        return disciplinaRepository.findOne(id);
    }

    public void remover(Disciplina disciplina) {
        disciplinaRepository.delete(disciplina);
    }

    public List<Disciplina> getTodasDisciplinas() {
        return disciplinaRepository.findAll(new Sort(new Sort.Order(ASC, "descricao")));
    }

    public Disciplina carregar(Long id) {
        return disciplinaRepository.findOne(id);
    }

    public DisciplinaRepository getDisciplinaRepository() {
        return disciplinaRepository;
    }

    @Override
    public Page<Disciplina> executeQueryEncontrarPorNome(int pagina, int totalPorPagina, String nome) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, ordenarPorChave());
        return disciplinaRepository.findByDescricaoLike(pageRequest, "%"+nome.toUpperCase()+"%");        
    }

    @Override
    public Page<Disciplina> executeQueryEncontrarTodos(int pagina, int totalPorPagina) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, new Sort(Sort.Direction.ASC, "descricao"));

        return disciplinaRepository.findAll(pageRequest);
    }
    
    public DataPage<Disciplina> procurarPorNome(int pagina, String nome){        
        return findByNameLike(pagina, nome);
    }    
}
