

package br.unicesumar.time5.repository;

import br.unicesumar.time5.entity.MateriaPrima;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MateriaPrimaRepository extends JpaRepository<MateriaPrima,Long> {
    
    Page<MateriaPrima> findByNomeLike(Pageable pageAble, String nome);
}
