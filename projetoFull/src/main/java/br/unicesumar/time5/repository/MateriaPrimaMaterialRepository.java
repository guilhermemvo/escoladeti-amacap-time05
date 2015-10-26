package br.unicesumar.time5.repository;

import br.unicesumar.time5.entity.MateriaPrimaMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MateriaPrimaMaterialRepository extends JpaRepository<MateriaPrimaMaterial, Long> {

    public Page<MateriaPrimaMaterial> findByQuantidadeLike(Pageable pageAble, String origem);

}
