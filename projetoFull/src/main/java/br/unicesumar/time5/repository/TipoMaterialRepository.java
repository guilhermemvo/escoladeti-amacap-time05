package br.unicesumar.time5.repository;

import br.unicesumar.time5.entity.TipoMaterial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoMaterialRepository extends JpaRepository<TipoMaterial, Long> {

    Page<TipoMaterial> findByDescricaoLike(Pageable pageAble, String descricao);

}
