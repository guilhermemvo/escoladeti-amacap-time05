package br.unicesumar.escoladeti.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import br.unicesumar.escoladeti.entity.Alternativa;
import br.unicesumar.escoladeti.entity.QuestaoObjetiva;

public interface AlternativaRepository extends JpaRepository<Alternativa, Long>{

	List<Alternativa> findAllByQuestao(QuestaoObjetiva questao, Sort sort);

	List<Alternativa> findAllByQuestao(QuestaoObjetiva questao);

}
