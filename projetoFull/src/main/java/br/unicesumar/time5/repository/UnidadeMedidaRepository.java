package br.unicesumar.time5.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.unicesumar.time5.entity.Pais;
import br.unicesumar.time5.entity.UnidadeMedida;

public interface UnidadeMedidaRepository extends JpaRepository<UnidadeMedida, Long> {

	    Page<UnidadeMedida> findByDescricaoLike(Pageable pageAble, String descricao);
}
