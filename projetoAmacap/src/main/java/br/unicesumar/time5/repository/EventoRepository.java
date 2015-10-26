package br.unicesumar.time5.repository;
import br.unicesumar.time5.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento,Long>{
    
}
