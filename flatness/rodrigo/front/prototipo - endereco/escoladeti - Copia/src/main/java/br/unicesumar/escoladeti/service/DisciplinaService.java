package br.unicesumar.escoladeti.service;

import static br.unicesumar.escoladeti.controller.DataPage.pageRequestForAsc;
import static org.springframework.data.domain.Sort.Direction.ASC;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.unicesumar.escoladeti.controller.DataPage;
import br.unicesumar.escoladeti.entity.Disciplina;
import br.unicesumar.escoladeti.repository.DisciplinaRepository;

@Service
public class DisciplinaService {
	@Autowired
	private DisciplinaRepository repo;
	
	public DataPage<Disciplina> getDisciplinas(Integer pagina) {
		return new DataPage<>(repo.findAll(pageRequestForAsc(pagina, "nome")));
	}

	public void salvar(Disciplina disciplina) {
		repo.save(disciplina);
	}

	public Disciplina recuperarPeloId(Long id) {
		return repo.findOne(id);
	}

	public void remover(Disciplina disciplina) {
		repo.delete(disciplina);		
	}

	public List<Disciplina> getTodasDisciplinas() {
		return repo.findAll(new Sort(new Sort.Order(ASC, "nome")));
	}

        public Disciplina carregar(Long id) {
                return repo.findOne(id);
        }

}
