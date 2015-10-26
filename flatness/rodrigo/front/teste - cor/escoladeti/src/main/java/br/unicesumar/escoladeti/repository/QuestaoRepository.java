package br.unicesumar.escoladeti.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.unicesumar.escoladeti.entity.Disciplina;
import br.unicesumar.escoladeti.entity.Questao;
import br.unicesumar.escoladeti.entity.TipoQuestao;

public interface QuestaoRepository extends JpaRepository<Questao, Long>{

	Page<Questao> findByEnunciadoContainingAndNivelDificuldadeBetweenAndDisciplinasIn(String enunciado, int nivelMinimo, int nivelMaximo, List<Disciplina> disciplinasPesquisa, Pageable pageable);

	Page<Questao> findByEnunciadoContainingAndNivelDificuldadeBetweenAndDisciplinasInAndTipoEquals(String enunciado, int nivelMinimo, int nivelMaximo, List<Disciplina> disciplinas, TipoQuestao tipo, Pageable pageable);

	List<Questao> findByEnunciadoContainingOrderByEnunciadoAsc(String enunciado);

}
