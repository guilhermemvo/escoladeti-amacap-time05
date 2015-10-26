package br.unicesumar.time5.service;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.unicesumar.time5.controller.DataPage;
import br.unicesumar.time5.entity.Funcao;
import br.unicesumar.time5.repository.FuncaoRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import static org.springframework.data.domain.Sort.Direction.ASC;

@Service
@Transactional
public class FuncaoService extends AbstractService<Funcao> {

    private static final Logger logger = LoggerFactory.getLogger(FuncaoService.class);

    @Autowired
    private FuncaoRepository funcaoRepository;

    public DataPage<Funcao> getFuncoes(Integer pagina) {
        return findAll(pagina);
    }

    public void salvar(Funcao funcao) {
        funcaoRepository.save(funcao);
    }

    public Funcao recuperarPeloId(Long id) {
        return funcaoRepository.findOne(id);
    }

    public void remover(Funcao funcao) {
        funcaoRepository.delete(funcao);
    }

    public List<Funcao> getTodosFuncoes() {
        return funcaoRepository.findAll(new Sort(new Sort.Order(ASC, "descricao")));
    }

    public Funcao carregar(Long id) {
        return funcaoRepository.findOne(id);
    }

    public FuncaoRepository getFuncaoRepository() {
        return funcaoRepository;
    }

    @Override
    public Page<Funcao> executeQueryEncontrarPorNome(int pagina, int totalPorPagina, String descricao) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, ordenarPorChave());

        return funcaoRepository.findByDescricaoLike(pageRequest, "%" + descricao.toUpperCase() + "%");
    }

    @Override
    public Page<Funcao> executeQueryEncontrarTodos(int pagina, int totalPorPagina) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, new Sort(Sort.Direction.ASC, "descricao"));

        return funcaoRepository.findAll(pageRequest);
    }
    
    public DataPage<Funcao> procurarPorNome(int pagina, String nome){        
        return findByNameLike(pagina, nome);
    }    

}
