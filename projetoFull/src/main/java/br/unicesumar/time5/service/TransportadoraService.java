package br.unicesumar.time5.service;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.unicesumar.time5.controller.DataPage;
import br.unicesumar.time5.entity.Telefone;
import br.unicesumar.time5.entity.Transportadora;
import br.unicesumar.time5.repository.TransportadoraRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import static org.springframework.data.domain.Sort.Direction.ASC;

@Service
@Transactional
public class TransportadoraService extends AbstractService<Transportadora> {

    private static final Logger logger = LoggerFactory.getLogger(TransportadoraService.class);

    @Autowired
    private TransportadoraRepository transportadoraRepository;

    public DataPage<Transportadora> getTransportadoras(Integer pagina) {
        return findAll(pagina);
    }

    public void salvar(Transportadora transportadora) {
        Transportadora p1 = transportadoraRepository.findByCnpj(transportadora.getCnpj());
//        if (p1 == null) {
//            transportadoraRepository.save(transportadora);
//        }
        transportadoraRepository.save(transportadora);

    }

    public Transportadora recuperarPeloId(Long id) {
        return transportadoraRepository.findOne(id);
    }

    public void remover(Transportadora transportadora) {
        transportadoraRepository.delete(transportadora);
    }

    public List<Transportadora> getTodosTransportadoras() {
        return transportadoraRepository.findAll(new Sort(new Sort.Order(ASC, "razao")));
    }

    public Transportadora carregar(Long id) {
        return transportadoraRepository.findOne(id);
    }

     public Transportadora BuscaTransportadoraPorCNPJ(String cnpj) {
        return transportadoraRepository.findByCnpj(cnpj);
    }

    public TransportadoraRepository getTransportadoraRepository() {
        return transportadoraRepository;
    }

    @Override
    public Page<Transportadora> executeQueryEncontrarPorNome(int pagina, int totalPorPagina, String nome) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, ordenarPorChave());

        return transportadoraRepository.findByRazaoLike(pageRequest, "%" + nome.toUpperCase() + "%");
    }

    @Override
    public Page<Transportadora> executeQueryEncontrarTodos(int pagina, int totalPorPagina) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, new Sort(Sort.Direction.ASC, "fantasia"));

        return transportadoraRepository.findAll(pageRequest);
    }

    public DataPage<Transportadora> procurarPorNome(int pagina, String nome){        
        return findByNameLike(pagina, nome);
    }    
}
