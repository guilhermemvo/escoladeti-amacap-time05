package br.unicesumar.time5.repository;

import br.unicesumar.time5.entity.Livro;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Serializable> {

    Page<Livro> findByNomeLike(Pageable pageAble, String nome);
    public List<Livro> findByPnldLike(String pnld);
}
