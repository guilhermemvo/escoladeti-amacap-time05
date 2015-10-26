
package br.unicesumar.time5.repository;

import br.unicesumar.time5.entity.UnidadeProducao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnidadeProducaoRepository extends JpaRepository<UnidadeProducao,Long>{

    Page<UnidadeProducao> findByNomeLike(Pageable pageAble, String nome);
}
