package br.unicesumar.time5.repository;

import br.unicesumar.time5.entity.Professor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    public Page<Professor> findByNomeLike(Pageable pageAble, String nome);
    public Professor findByCpf(String cpf);
    
     public List<Professor> findByCpfLike(String cpf);

    public List<Professor> findByRgLike(String value);
    
    public List<Professor> findByNomeLike(String value);
    
     public List<Professor> findByCpfLikeOrNomeLikeIgnoreCaseOrderByNomeAsc(String Cpf, String Nome);
}
