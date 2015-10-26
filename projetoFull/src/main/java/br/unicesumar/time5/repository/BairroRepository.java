package br.unicesumar.time5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.unicesumar.time5.entity.Bairro;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

public interface BairroRepository extends JpaRepository<Bairro, Long> {

    Page<Bairro> findByNomeLike(Pageable pageAble, String nome);
    
    @Query(value = "select * from tbcad_bairro where id_cidade = ?1", nativeQuery = true)
    List<Bairro> encontrarBairroPorIdCidade(Long id);
}
