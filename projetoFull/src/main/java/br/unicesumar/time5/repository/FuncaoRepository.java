package br.unicesumar.time5.repository;

import br.unicesumar.time5.entity.Funcao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Paulo Henrique Machado Dias
 */
public interface FuncaoRepository extends JpaRepository<Funcao, Long> {

    public Page<Funcao> findByDescricaoLike(Pageable pageAble, String descricao);
}
