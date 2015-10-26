package br.unicesumar.time5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.unicesumar.time5.entity.Disciplina;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
    
    Page<Disciplina> findByDescricaoLike(Pageable pageAble, String nome);
}
