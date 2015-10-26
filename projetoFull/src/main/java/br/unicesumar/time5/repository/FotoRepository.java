package br.unicesumar.time5.repository;

import br.unicesumar.time5.entity.Foto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FotoRepository extends JpaRepository<Foto, Long> {

    @Query(value = "select * from tbcad_foto_evento where id_evento = ?1", nativeQuery = true)
    List<Foto> findByEvento(Long id);

}
