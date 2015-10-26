package br.unicesumar.time5.service;

import br.unicesumar.time5.controller.DataPage;
import static br.unicesumar.time5.controller.DataPage.pageRequestForAsc;
import br.unicesumar.time5.entity.Evento;
import br.unicesumar.time5.repository.EventoRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import static org.springframework.data.domain.Sort.Direction.ASC;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EventoService {

    private static final Logger logger = LoggerFactory.getLogger(EventoService.class);

    @Autowired
    private EventoRepository eventoRepository;

    public DataPage<Evento> getEvento(Integer pagina) {
        return new DataPage<>(eventoRepository.findAll(pageRequestForAsc(pagina, "nome")));
    }

    public void salvar(Evento evento) {
        eventoRepository.save(evento);
    }

    public Evento recuperarPeloId(Long id) {
        return eventoRepository.findOne(id);
    }

    public void remover(Evento evento) {
        eventoRepository.delete(evento);
    }

    public List<Evento> getTodosEventos() {
        return eventoRepository.findAll(new Sort(new Sort.Order(ASC, "nome")));
    }

    public Evento carregar(Long id) {
        return eventoRepository.findOne(id);
    }

    public EventoRepository getEventoRepository() {
        return eventoRepository;
    }

}
