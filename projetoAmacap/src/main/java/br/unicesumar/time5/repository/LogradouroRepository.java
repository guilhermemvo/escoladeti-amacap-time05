package br.unicesumar.time5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.unicesumar.time5.entity.Logradouro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LogradouroRepository extends JpaRepository<Logradouro, Long> {

    Page<Logradouro> findByNomeLike(Pageable pageAble, String nome);

}
