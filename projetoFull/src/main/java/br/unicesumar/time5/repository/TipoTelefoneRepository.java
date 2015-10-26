package br.unicesumar.time5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.unicesumar.time5.entity.TipoTelefone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TipoTelefoneRepository extends JpaRepository<TipoTelefone, Long> {

    Page<TipoTelefone> findByDescricaoLike(Pageable pageAble, String descricao);

}
