package br.unicesumar.time5.service;

import br.unicesumar.time5.controller.DataPage;
import br.unicesumar.time5.entity.AgendaEvento;
import br.unicesumar.time5.entity.Evento;
import br.unicesumar.time5.entity.Presenca;
import br.unicesumar.time5.repository.AgendaEventoRepository;
import br.unicesumar.time5.repository.EventoRepository;
import br.unicesumar.time5.repository.InscricaoRepository;
import br.unicesumar.time5.repository.PresencaRepository;
import br.unicesumar.time5.vo.PresencaVO;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import static org.springframework.data.domain.Sort.Direction.ASC;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author NuriellyCaroline
 */
@Service
@Transactional
public class EventoService extends AbstractService<Evento> {

    private static final Logger logger = LoggerFactory.getLogger(EventoService.class);

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private PresencaRepository presencasRepository;

    @Autowired
    private AgendaEventoRepository agendaEventoRepository;

    @Autowired
    private InscricaoRepository inscricaoRepositorio;

    public DataPage<Evento> getEventos(Integer pagina) {
        return findAll(pagina);
    }

    public void salvar(Evento evento) {
        eventoRepository.save(evento);

        AtualizaPresencaParticipanteService atualizaPresencaParticipante = new AtualizaPresencaParticipanteService();
        atualizaPresencaParticipante.atualizaPorEvento(evento, presencasRepository, inscricaoRepositorio);
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

    @Override
    public Page<Evento> executeQueryEncontrarPorNome(int pagina, int totalPorPagina, String nome) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, ordenarPorChave());

        return eventoRepository.findByNomeLike(pageRequest, "%" + nome.toUpperCase() + "%");
    }

    @Override
    public Page<Evento> executeQueryEncontrarTodos(int pagina, int totalPorPagina) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, ordenarPorChave());

        return eventoRepository.findAll(pageRequest);
    }

    public DataPage<Evento> procurarPorNome(int pagina, String nome) {
        return findByNameLike(pagina, nome);
    }

    public List<Presenca> buscaPresencas(Long idAgendaEvento) {
        AgendaEvento agendaEvento = agendaEventoRepository.findOne(idAgendaEvento);
        List<Presenca> presencaParticipante = presencasRepository.findByAgendaEvento(agendaEvento);
        return presencaParticipante;
    }

    public void salvarPresencas(List<PresencaVO> presencas) {
        for (PresencaVO salvarPresenca : presencas) {

            Presenca novaPresenca = presencasRepository.findOne(salvarPresenca.getId());
            novaPresenca.setManha(salvarPresenca.isManha());
            novaPresenca.setTarde(salvarPresenca.isTarde());
            novaPresenca.setNoite(salvarPresenca.isNoite());

            presencasRepository.save(novaPresenca);
        }
    }

    public List<AgendaEvento> buscaAgenda(Long idEvento) {
        return eventoRepository.findOne(idEvento).getAgendaEvento();
    }
}
