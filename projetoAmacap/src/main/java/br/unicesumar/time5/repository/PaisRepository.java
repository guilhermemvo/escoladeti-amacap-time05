package br.unicesumar.time5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.unicesumar.time5.entity.Pais;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PaisRepository extends JpaRepository<Pais, Long> {

	//Pais findByCodigoIBGE(Integer codigoIBGE);
    @Query(value = "SELECT c.codigo_ibge FROM tbcad_pais c WHERE codigo_ibge = ?1", nativeQuery = true)
    Pais findByCodigoIBGE(int codigoIBGE);

    public Page<Pais> findByNomeLike(Pageable pageAble, String nome);

}
