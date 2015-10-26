package br.unicesumar.time5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.unicesumar.time5.entity.Cidade;
import br.unicesumar.time5.entity.UnidadeFederativa;

public interface UnidadeFederativaRepository extends JpaRepository<UnidadeFederativa, Long> {

	//UnidadeFederativa findByCodigo(Integer codigoIBGE);
	
	@Query(value = "SELECT c.codigo_ibge FROM tbcad_unidade_federativa c WHERE codigo_ibge = ?1", nativeQuery = true)
    UnidadeFederativa findByCodigoIBGE(int codigoIBGE);

}
