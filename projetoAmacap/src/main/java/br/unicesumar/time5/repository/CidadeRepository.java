package br.unicesumar.time5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.unicesumar.time5.entity.Cidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
    
    @Query(value = "SELECT c.codigo_ibge FROM tbcad_cidade c WHERE codigo_ibge = ?1", nativeQuery = true)
    Cidade findByCodigoIBGE(int codigoIBGE);

    Page<Cidade> findByNomeLike(Pageable pageAble, String nome);

}
