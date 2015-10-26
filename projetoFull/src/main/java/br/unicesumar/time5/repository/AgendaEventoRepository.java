package br.unicesumar.time5.repository;

import br.unicesumar.time5.entity.AgendaEvento;
import br.unicesumar.time5.entity.Evento;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AgendaEventoRepository extends JpaRepository<AgendaEvento, Long>  {
    
    List<AgendaEvento> findByEvento(Evento evento);
    
}
