package br.unicesumar.time5.repository;

import br.unicesumar.time5.entity.Pedido;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query(value = "select * from tbcad_pedido where datapedido BETWEEN date(:de) AND date(:ate)", nativeQuery = true)
    public List<Pedido> encontrarDataPorPeriodo(@Param("de") String de, @Param("ate") String ate);

    //Ainda não implementado por falta de status do pedido no banco - Guilherme Matias
    @Query(value = "SELECT count(*) FROM tbcad_pedido", nativeQuery = true)
    public Integer getCountPendentes();

}



