package br.unicesumar.time5.repository;


import br.unicesumar.time5.entity.PedidoMaterial;
import java.io.Serializable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoMaterialRepository extends JpaRepository<PedidoMaterial, Serializable>{
    Page<PedidoMaterial> findByRecebidoTrue(Pageable pageAble);
}
