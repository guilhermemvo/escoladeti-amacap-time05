package br.unicesumar.time5.service;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.unicesumar.time5.controller.DataPage;
import static br.unicesumar.time5.controller.DataPage.pageRequestForAsc;
import br.unicesumar.time5.entity.Pais;
import br.unicesumar.time5.repository.PaisRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import static org.springframework.data.domain.Sort.Direction.ASC;

@Service
@Transactional
public class PaisService extends AbstractService<Pais> {

    private static final Logger logger = LoggerFactory.getLogger(PaisService.class);

    @Autowired
    private PaisRepository paisRepository;

    public DataPage<Pais> getPaises(Integer pagina) {
        
        return findAll(pagina);
    }

    public void salvar(Pais pais) {
        paisRepository.save(pais);
    }

    public Pais recuperarPeloId(Long id) {
        return paisRepository.findOne(id);
    }

    public void remover(Pais pais) {
        paisRepository.delete(pais);
    }

    public List<Pais> getTodosPaises() {
        return paisRepository.findAll(new Sort(new Sort.Order(ASC, "nome")));
    }

    public Pais carregar(Long id) {
        return paisRepository.findOne(id);
    }

    public PaisRepository getPaisRepository() {
        return paisRepository;
    }
    
    public Pais getCodeLocalization(long codigoIBGE) {
        return getPaisRepository().findByCodigoIBGE(codigoIBGE);
    }

    @Override
    public Page<Pais> executeQueryEncontrarPorNome(int pagina, int totalPorPagina, String nome) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, ordenarPorChave());        
        return paisRepository.findByNomeLike(pageRequest, "%" + nome.toUpperCase() + "%");
    }

    @Override
    public Page<Pais> executeQueryEncontrarTodos(int pagina, int totalPorPagina) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, ordenarPorChave());
        return paisRepository.findAll(pageRequest);
    }
    
    public DataPage<Pais> procurarPorNome(int pagina, String nome){        
        return findByNameLike(pagina, nome);
    }

}
