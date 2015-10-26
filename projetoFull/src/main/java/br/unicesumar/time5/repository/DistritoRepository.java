package br.unicesumar.time5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.unicesumar.time5.entity.Distrito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DistritoRepository extends JpaRepository<Distrito, Long> {

    Page<Distrito> findByNomeLike(Pageable pageAble, String nome);
}
