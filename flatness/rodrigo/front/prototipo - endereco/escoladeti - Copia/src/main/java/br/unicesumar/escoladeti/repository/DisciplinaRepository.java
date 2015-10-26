package br.unicesumar.escoladeti.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.unicesumar.escoladeti.entity.Disciplina;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {

	List<Disciplina> findByNomeContainingOrderByNomeAsc(String value);

	Page<Disciplina> findByNomeContainingOrderByNomeAsc(String nome, Pageable sortedPage);

}
