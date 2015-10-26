package br.unicesumar.time5.repository;

import br.unicesumar.time5.entity.AgendaEvento;
import br.unicesumar.time5.entity.Evento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Long> {

    Page<Evento> findByNomeLike(Pageable pageAble, String nome);
    
    AgendaEvento findByAgendaEvento(Long agendaEvento);
}
