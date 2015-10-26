package br.unicesumar.time5.service;

import br.unicesumar.time5.entity.AgendaEvento;
import br.unicesumar.time5.entity.Evento;
import br.unicesumar.time5.entity.Inscricao;
import br.unicesumar.time5.entity.Presenca;
import br.unicesumar.time5.repository.InscricaoRepository;
import br.unicesumar.time5.repository.PresencaRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AtualizaPresencaParticipanteService {

    public void atualizaPorEvento(Evento evento, PresencaRepository presencaRepository, InscricaoRepository inscricaoRepositorio) {
        for (AgendaEvento agenda : evento.getAgendaEvento()) {
            if (presencaRepository.findByAgendaEvento(agenda).isEmpty()) {
                List<Inscricao> findByEvento = inscricaoRepositorio.findByEvento(evento);
                for (Inscricao inscricao : findByEvento) {
                    Presenca presenca = new Presenca();
                    presenca.setAgendaEvento(agenda);
                    presenca.setInscricao(inscricao);
                    presenca.setManha(Boolean.FALSE);
                    presenca.setTarde(Boolean.FALSE);
                    presenca.setNoite(Boolean.FALSE);
                    presencaRepository.save(presenca);
                }
            }
        }
    }
    
//    public void removerPorEvento(Evento evento, PresencaRepository presencaRepository) {
//
//        for (AgendaEvento agenda : evento.getAgendaEvento()) {
//            for (Presenca presenca : presencaRepository.findByAgendaEvento(agenda)) {
//                presencaRepository.delete(presenca);
//            }
//        }
//    }

    public void atualizaPorInscricao(Inscricao inscricao, PresencaRepository presencaRepository) {
        Evento evento = inscricao.getEvento();
        for (AgendaEvento agenda : evento.getAgendaEvento()) {
            if (presencaRepository.findByAgendaEventoAndInscricao(agenda, inscricao) == null) {
                Presenca presenca = new Presenca();
                presenca.setAgendaEvento(agenda);
                presenca.setInscricao(inscricao);
                presenca.setManha(Boolean.FALSE);
                presenca.setTarde(Boolean.FALSE);
                presenca.setNoite(Boolean.FALSE);
                presencaRepository.save(presenca);

            }
        }
    }

    public void removerPorInscricao(Inscricao inscricao, PresencaRepository presencaRepository) {
        Evento evento = inscricao.getEvento();
        for (AgendaEvento agenda : evento.getAgendaEvento()) {
            Presenca presenca = presencaRepository.findByAgendaEventoAndInscricao(agenda, inscricao);
            if (presenca != null) {
                presencaRepository.delete(presenca);
            }
        }
    }

}
