package br.unicesumar.time5.service;

import br.unicesumar.time5.controller.DataPage;
import br.unicesumar.time5.entity.DAOInscricao;
import br.unicesumar.time5.entity.Inscricao;
import br.unicesumar.time5.repository.AgendaEventoRepository;
import br.unicesumar.time5.repository.EventoRepository;
import br.unicesumar.time5.repository.InscricaoRepository;
import br.unicesumar.time5.repository.PessoaRepository;
import br.unicesumar.time5.repository.PresencaRepository;
import br.unicesumar.time5.vo.InscricaoVO;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import static org.springframework.data.domain.Sort.Direction.DESC;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InscricaoService extends AbstractService<Inscricao> {

    private static final Logger logger = LoggerFactory.getLogger(InscricaoService.class);

    @Autowired
    private InscricaoRepository repository;

    @Autowired
    private EventoRepository repEvento;

    @Autowired
    private PessoaRepository repPessoa;
    
    @Autowired
    private PresencaRepository presencaRepository;

    public DataPage<Inscricao> getInscritos(Integer pagina) {
        return findAll(pagina);
    }

    public void salvar(DAOInscricao daoInscricao) {
        Inscricao inscricao = new Inscricao();
        inscricao.setPessoa(repPessoa.findOne(daoInscricao.getIdPessoa()));
        inscricao.setEvento(repEvento.findOne(daoInscricao.getIdEvento()));
        inscricao.setPago(Boolean.FALSE);
        logger.info("Inscrição salva.");
        repository.save(inscricao);
        
        AtualizaPresencaParticipanteService atualizaPresencaParticipante = new AtualizaPresencaParticipanteService();
        atualizaPresencaParticipante.atualizaPorInscricao(inscricao,presencaRepository);
    }

    public Inscricao recuperarPeloId(Long id) {
        return repository.findOne(id);
    }

    public void remover(Long idInscricao) {
        Inscricao inscricao = carregar(idInscricao);
        
        AtualizaPresencaParticipanteService atualizaPresencaParticipante = new AtualizaPresencaParticipanteService();
        atualizaPresencaParticipante.removerPorInscricao(inscricao, presencaRepository);
        repository.delete(inscricao);
    }

    public List<Inscricao> getTodosInscritos() {
        return repository.findAll(new Sort(new Sort.Order(DESC, "id")));
    }

    public Inscricao carregar(Long id) {
        return repository.findOne(id);
    }

    public InscricaoRepository getInscricaoRepository() {
        return repository;
    }

    @Override
    public Page<Inscricao> executeQueryEncontrarPorNome(int pagina, int totalPorPagina, String nome) {
        return null;
    }

    @Override
    public Page<Inscricao> executeQueryEncontrarTodos(int pagina, int totalPorPagina) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, ordenarPorChave());

        return repository.findAll(pageRequest);
    }

    public List<Inscricao> recuperarParticipantesPorIdEvento(Long id) {
        return repository.findByEvento(repEvento.findOne(id));
    }

    public void atualizarInscricoes(List<InscricaoVO> inscricoes) {
        for (InscricaoVO inscricaoVo : inscricoes) {
            Inscricao inscricao = carregar(inscricaoVo.getId());
            inscricao.setPago(inscricaoVo.isPago());
            repository.save(inscricao);
        }        
    }

}
