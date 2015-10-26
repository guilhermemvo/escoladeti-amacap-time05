package br.unicesumar.time5.service;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.unicesumar.time5.controller.DataPage;
import static br.unicesumar.time5.controller.DataPage.pageRequestForAsc;
import br.unicesumar.time5.entity.Pais;
import br.unicesumar.time5.entity.UnidadeMedida;
import br.unicesumar.time5.repository.PaisRepository;
import br.unicesumar.time5.repository.UnidadeMedidaRepository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static org.springframework.data.domain.Sort.Direction.ASC;

@Service
@Transactional
public class UnidadeMedidaService extends AbstractService<UnidadeMedida> {

    private static final Logger logger = LoggerFactory.getLogger(UnidadeMedidaService.class);

    @Autowired
    private UnidadeMedidaRepository unidadeMedidaRepository;

    public DataPage<UnidadeMedida> getUnidadeMedida(Integer pagina) {
        
        return findAll(pagina);
    }

    public void salvar(UnidadeMedida unidadeMedida) {
    	unidadeMedidaRepository.save(unidadeMedida);
    }

    public UnidadeMedida recuperarPeloId(Long id) {
        return unidadeMedidaRepository.findOne(id);
    }

    public void remover(UnidadeMedida unidadeMedida) {
    	unidadeMedidaRepository.delete(unidadeMedida);
    }

    public List<UnidadeMedida> getTodosUnidadeMedida() {
        return unidadeMedidaRepository.findAll(new Sort(new Sort.Order(ASC, "descricao")));
    }

    public UnidadeMedida carregar(Long id) {
        return unidadeMedidaRepository.findOne(id);
    }

    public UnidadeMedidaRepository getUnidadeMedidaRepository() {
        return unidadeMedidaRepository;
    }
    
    
    @Override
    public Page<UnidadeMedida> executeQueryEncontrarPorNome(int pagina, int totalPorPagina, String descricao) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, ordenarPorChave());        
        return unidadeMedidaRepository.findByDescricaoLike(pageRequest, "%" + descricao.toUpperCase() + "%");
    }

    @Override
    public Page<UnidadeMedida> executeQueryEncontrarTodos(int pagina, int totalPorPagina) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, ordenarPorChave());
        return unidadeMedidaRepository.findAll(pageRequest);
    }
    
    public DataPage<UnidadeMedida> procurarPorNome(int pagina, String nome){        
        return findByNameLike(pagina, nome);
    }

}
