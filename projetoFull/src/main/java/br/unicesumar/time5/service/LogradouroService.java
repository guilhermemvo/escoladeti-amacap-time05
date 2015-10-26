package br.unicesumar.time5.service;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.unicesumar.time5.controller.DataPage;
import br.unicesumar.time5.entity.Logradouro;
import br.unicesumar.time5.repository.LogradouroRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import static org.springframework.data.domain.Sort.Direction.ASC;

@Service
@Transactional
public class LogradouroService extends AbstractService<Logradouro> {

    private static final Logger logger = LoggerFactory.getLogger(LogradouroService.class);

    @Autowired
    private LogradouroRepository logradouroRepository;

    public DataPage<Logradouro> getLogradouros(Integer pagina) {
        return findAll(pagina);
    }

    public void salvar(Logradouro logradouro) {
        logradouroRepository.save(logradouro);
    }

    public Logradouro recuperarPeloId(Long id) {
        return logradouroRepository.findOne(id);
    }

    public void remover(Logradouro logradouro) {
        logradouroRepository.delete(logradouro);
    }

    public List<Logradouro> getTodosLogradouros() {
        return logradouroRepository.findAll(new Sort(new Sort.Order(ASC, "nome")));
    }

    public Logradouro carregar(Long id) {
        return logradouroRepository.findOne(id);
    }

    public LogradouroRepository getLogradouroRepository() {
        return logradouroRepository;
    }

    @Override
    public Page<Logradouro> executeQueryEncontrarPorNome(int pagina, int totalPorPagina, String nome) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, ordenarPorChave());

        return logradouroRepository.findByNomeLike(pageRequest, "%" + nome.toUpperCase() + "%");
    }

    @Override
    public Page<Logradouro> executeQueryEncontrarTodos(int pagina, int totalPorPagina) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, ordenarPorChave());

        return logradouroRepository.findAll(pageRequest);
    }

    public List<Logradouro> procurarLogradouroPorIdBairro(Long id) {
        return logradouroRepository.procurarLogradouroPeloIdDoBairro(id);
        /*
         List<Logradouro> logradouros = new ArrayList<>();
         for(Logradouro logradouro: getTodosLogradouros()){
         if(logradouro.getBairro().getId().equals(id)){
         logradouros.add(logradouro);
         }
         }        
         return logradouros;
         */
    }

    public Logradouro recuperarPeloCep(String cep) {
        return logradouroRepository.findByCepLike(cep);
    }

    public DataPage<Logradouro> procurarPorNome(int pagina, String nome) {
        return findByNameLike(pagina, nome);
    }
}
