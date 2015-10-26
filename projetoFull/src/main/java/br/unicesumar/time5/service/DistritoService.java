package br.unicesumar.time5.service;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.unicesumar.time5.controller.DataPage;
import br.unicesumar.time5.entity.Distrito;
import br.unicesumar.time5.repository.DistritoRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import static org.springframework.data.domain.Sort.Direction.ASC;

@Service
@Transactional
public class DistritoService extends AbstractService<Distrito>{

    private static final Logger logger = LoggerFactory.getLogger(DistritoService.class);

    @Autowired
    private DistritoRepository distritoRepository;

    public DataPage<Distrito> getDistritos(Integer pagina) {
        return findAll(pagina);
    }

    public void salvar(Distrito distrito) {
        distritoRepository.save(distrito);
    }

    public Distrito recuperarPeloId(Long id) {
        return distritoRepository.findOne(id);
    }

    public void remover(Distrito distrito) {
        distritoRepository.delete(distrito);
    }

    public List<Distrito> getTodosDistritos() {
        return distritoRepository.findAll(new Sort(new Sort.Order(ASC, "nome")));
    }

    public Distrito carregar(Long id) {
        return distritoRepository.findOne(id);
    }

    public DistritoRepository getDistritoRepository() {
        return distritoRepository;
    }

    @Override
    public Page<Distrito> executeQueryEncontrarPorNome(int pagina, int totalPorPagina, String nome) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, ordenarPorChave());

        return distritoRepository.findByNomeLike(pageRequest, "%" + nome.toUpperCase() + "%");
    }

    @Override
    public Page<Distrito> executeQueryEncontrarTodos(int pagina, int totalPorPagina) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, ordenarPorChave());

        return distritoRepository.findAll(pageRequest);
    }
    
    public DataPage<Distrito> procurarPorNome(int pagina, String nome){        
        return findByNameLike(pagina, nome);
    }    

}
