package br.unicesumar.time5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.unicesumar.time5.entity.TipoLogradouro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TipoLogradouroRepository extends JpaRepository<TipoLogradouro, Long> {

    Page<TipoLogradouro> findByDescricaoLike(Pageable pageAble, String descricao);

}
