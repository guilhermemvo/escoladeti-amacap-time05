package br.unicesumar.time5.service;

import br.unicesumar.time5.controller.DataPage;
import br.unicesumar.time5.entity.Telefone;
import br.unicesumar.time5.repository.TelefoneRepository;
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
public class TelefoneService extends AbstractService<Telefone>{

    private static final Logger logger = LoggerFactory.getLogger(TelefoneService.class);

    @Autowired
    private TelefoneRepository repository;

    public DataPage<Telefone> getTiposLogradouro(Integer pagina) {
        return findAll(pagina);
    }

    public void salvar(Telefone telefone) {
        logger.info("Telefone salvo.");
        repository.save(telefone);
    }

    public Telefone recuperarPeloId(Long id) {
        return repository.findOne(id);
    }

    public void remover(Telefone telefone) {
        repository.delete(telefone);
    }

    public List<Telefone> getTodosTelefones() {
        return repository.findAll(new Sort(new Sort.Order(DESC, "numero")));
    }

    public Telefone carregar(Long id) {
        return repository.findOne(id);
    }

    public TelefoneRepository getTelefoneRepository() {
        return repository;
    }

    @Override
    public Page<Telefone> executeQueryEncontrarPorNome(int pagina, int totalPorPagina, String nome) {

        return null;
    }

    @Override
    public Page<Telefone> executeQueryEncontrarTodos(int pagina, int totalPorPagina) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, ordenarPorChave());

        return repository.findAll(pageRequest);
    }

}
