package br.unicesumar.time5.repository;

import br.unicesumar.time5.entity.UnidadeFederativa;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UnidadeFederativaRepository extends JpaRepository<UnidadeFederativa, Long> {

    UnidadeFederativa findByCodigoIBGE(long codigoIBGE);

    @Query(value = "SELECT c.codigo_ibge FROM tbcad_unidade_federativa c WHERE codigo_ibge = ?1", nativeQuery = true)
    UnidadeFederativa findByCodigoIBGE(int codigoIBGE);
    Page<UnidadeFederativa> findByNomeLike(Pageable pageAble, String nome);
    
    @Query(value = "select * from tbcad_unidade_federativa  where id_pais = ?1", nativeQuery = true)
    List<UnidadeFederativa> procurarUnidadeFederativaPeloIdDoPais(Long id);    
}
