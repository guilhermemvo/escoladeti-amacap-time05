package br.unicesumar.time5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.unicesumar.time5.entity.Logradouro;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

public interface LogradouroRepository extends JpaRepository<Logradouro, Long> {

    Page<Logradouro> findByNomeLike(Pageable pageAble, String nome);

    @Query(value = "select * from tbcad_logradouro where cep = ?1", nativeQuery = true)
    Logradouro findByCepLike(String cep);

    @Query(value = "select * from tbcad_logradouro  where id_bairro = ?1", nativeQuery = true)
    List<Logradouro> procurarLogradouroPeloIdDoBairro(Long id);
}
