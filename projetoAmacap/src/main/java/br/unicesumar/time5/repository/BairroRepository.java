package br.unicesumar.time5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.unicesumar.time5.entity.Bairro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BairroRepository extends JpaRepository<Bairro, Long> {

    Page<Bairro> findByNomeLike(Pageable pageAble, String nome);

}
