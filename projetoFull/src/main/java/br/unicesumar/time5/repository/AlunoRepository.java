package br.unicesumar.time5.repository;

import br.unicesumar.time5.entity.Aluno;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    public Page<Aluno> findByNomeLike(Pageable pageAble, String nome);

    public Aluno findByCpf(String cpf);

    public List<Aluno> findByCpfLike(String cpf);

    public List<Aluno> findByRgLike(String value);

    public List<Aluno> findByNomeLike(String value);

    public List<Aluno> findByCpfLikeOrNomeLikeIgnoreCaseOrderByNomeAsc(String Cpf, String Nome);
}
