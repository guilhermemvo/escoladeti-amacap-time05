

package br.unicesumar.time5.repository;

import br.unicesumar.time5.entity.AgendaEvento;
import br.unicesumar.time5.entity.Inscricao;
import br.unicesumar.time5.entity.Presenca;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PresencaRepository extends JpaRepository<Presenca, Long>{
    
    List<Presenca> findByAgendaEvento(AgendaEvento agendaEvento);

    Presenca findByAgendaEventoAndInscricao(AgendaEvento agenda, Inscricao inscricao);
}
