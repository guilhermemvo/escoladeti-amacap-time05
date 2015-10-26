package br.unicesumar.time5.service;

import br.unicesumar.time5.controller.DataPage;
import br.unicesumar.time5.entity.TipoLogradouro;
import br.unicesumar.time5.repository.TipoLogradouroRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.domain.Sort.Direction.DESC;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TipoLogradouroService extends AbstractService<TipoLogradouro>{

    private static final Logger logger = LoggerFactory.getLogger(TipoLogradouroService.class);

    @Autowired
    private TipoLogradouroRepository tipoLogradouroRepository;

    public DataPage<TipoLogradouro> getTiposLogradouro(Integer pagina) {
        return findAll(pagina);
    }

    public void salvar(TipoLogradouro tipologradouro) {
        tipoLogradouroRepository.save(tipologradouro);
    }

    public TipoLogradouro recuperarPeloId(Long id) {
        return tipoLogradouroRepository.findOne(id);
    }

    public void remover(TipoLogradouro tipologradouro) {
        tipoLogradouroRepository.delete(tipologradouro);
    }

    public List<TipoLogradouro> getTodosTiposLogradouro() {
        return tipoLogradouroRepository.findAll(new Sort(new Sort.Order(DESC, "descricao")));
    }

    public TipoLogradouro carregar(Long id) {
        return tipoLogradouroRepository.findOne(id);
    }

    public TipoLogradouroRepository getTipoLogradouroRepository() {
        return tipoLogradouroRepository;
    }

    @Override
    public Page<TipoLogradouro> executeQueryEncontrarPorNome(int pagina, int totalPorPagina, String nome) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, ordenarPorChave());

        return tipoLogradouroRepository.findByDescricaoLike(pageRequest, "%" + nome.toUpperCase() + "%");
    }

    @Override
    public Page<TipoLogradouro> executeQueryEncontrarTodos(int pagina, int totalPorPagina) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, new Sort(Sort.Direction.ASC, "descricao"));

        return tipoLogradouroRepository.findAll(pageRequest);
    }

    public DataPage<TipoLogradouro> procurarPorNome(int pagina, String nome){        
        return findByNameLike(pagina, nome);
    }    
}
