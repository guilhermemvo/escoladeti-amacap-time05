package br.unicesumar.time5.service;

import br.unicesumar.time5.controller.DataPage;
import br.unicesumar.time5.entity.TipoTelefone;
import br.unicesumar.time5.repository.TipoTelefoneRepository;
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
public class TipoTelefoneService extends AbstractService<TipoTelefone>{

    private static final Logger logger = LoggerFactory.getLogger(TipoTelefoneService.class);

    @Autowired
    private TipoTelefoneRepository repository;

    public DataPage<TipoTelefone> getTiposLogradouro(Integer pagina) {
        return findAll(pagina);
    }

    public void salvar(TipoTelefone tipotelefone) {
        repository.save(tipotelefone);
    }

    public TipoTelefone recuperarPeloId(Long id) {
        return repository.findOne(id);
    }

    public void remover(TipoTelefone tipotelefone) {
        repository.delete(tipotelefone);
    }

    public List<TipoTelefone> getTodosTiposTelefone() {
        return repository.findAll(new Sort(new Sort.Order(DESC, "descricao")));
    }

    public TipoTelefone carregar(Long id) {
        return repository.findOne(id);
    }

    public TipoTelefoneRepository getTipoTelefoneRepository() {
        return repository;
    }

    @Override
    public Page<TipoTelefone> executeQueryEncontrarPorNome(int pagina, int totalPorPagina, String nome) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, ordenarPorChave());

        return repository.findByDescricaoLike(pageRequest, "%" + nome + "%");
    }

    @Override
    public Page<TipoTelefone> executeQueryEncontrarTodos(int pagina, int totalPorPagina) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, ordenarPorChave());

        return repository.findAll(pageRequest);
    }

}
