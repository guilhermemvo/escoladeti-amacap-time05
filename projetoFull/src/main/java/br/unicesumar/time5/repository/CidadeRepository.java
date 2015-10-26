package br.unicesumar.time5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.unicesumar.time5.entity.Cidade;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
    
    Cidade findByCodigoIBGE(long codigoIBGE);
    Page<Cidade> findByNomeLike(Pageable pageAble, String nome);
    
    @Query(value = "select * from tbcad_cidade where id_unidade_federativa = ?1", nativeQuery = true)
    List<Cidade> procurarCidadePeloIdDaUnidadeFederativa(Long id);
}
