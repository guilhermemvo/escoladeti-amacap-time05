package br.unicesumar.time5.service;

import br.unicesumar.time5.controller.DataPage;
import br.unicesumar.time5.entity.Solicitante;
import br.unicesumar.time5.repository.SolicitanteRepository;
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

@Service
@Transactional
public class SolicitanteService extends AbstractService<Solicitante>{
    
    private static final Logger logger = LoggerFactory.getLogger(SolicitanteService.class);
    
    @Autowired
    private SolicitanteRepository repo;    
    
    public Solicitante salvar(Solicitante solicitante) {        
        return repo.saveAndFlush(solicitante);
    }
    
    public Solicitante findSolicitanteById(Long id){
        return repo.findOne(id);
    }
    
    public void remove(Solicitante solicitante){
        repo.delete(solicitante);
    }
    
    public DataPage<Solicitante> getPageSolicitante(Integer pagina){
        return findAll(pagina);
    }
    
    public List<Solicitante> getAllSolicitante(){
        return repo.findAll(new Sort(new Sort.Order(ASC, "nome")));
    }
    
    public Solicitante findSolicitanteByCpf(String cpf){
        return repo.findByCpf(cpf);
    }
    
    
    @Override
    public Page<Solicitante> executeQueryEncontrarPorNome(int pagina, int totalPorPagina, String nome) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, ordenarPorChave());
        return repo.findByNomeLike(pageRequest, "%" + nome.toUpperCase() + "%");        
    }

    @Override
    public Page<Solicitante> executeQueryEncontrarTodos(int pagina, int totalPorPagina) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, ordenarPorChave());

        return repo.findAll(pageRequest);
    }
    
     public DataPage<Solicitante> procurarPorNome(int pagina, String nome){        
        return findByNameLike(pagina, nome);
    }     

     
}
