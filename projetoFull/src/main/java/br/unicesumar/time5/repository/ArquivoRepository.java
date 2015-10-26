package br.unicesumar.time5.repository;

import br.unicesumar.time5.entity.Arquivo;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArquivoRepository extends JpaRepository<Arquivo, Long> {

    public Page<Arquivo> findByOrigemLike(Pageable pageAble, String origem);

}
