package br.unicesumar.time5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.unicesumar.time5.entity.Pais;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PaisRepository extends JpaRepository<Pais, Long> {

    Pais findByCodigoIBGE(long codigoIBGE);	
    Page<Pais> findByNomeLike(Pageable pageAble, String nome);
}
