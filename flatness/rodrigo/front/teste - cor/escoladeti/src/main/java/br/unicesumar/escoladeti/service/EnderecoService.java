package br.unicesumar.escoladeti.service;

import br.unicesumar.escoladeti.controller.DataPage;
import static br.unicesumar.escoladeti.controller.DataPage.pageRequestForAsc;
import br.unicesumar.escoladeti.entity.Endereco;
import br.unicesumar.escoladeti.repository.EnderecoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import static org.springframework.data.domain.Sort.Direction.ASC;
import org.springframework.stereotype.Service;

/**
 *
 * @author RodrigoCezar
 */
@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository repo;

    public DataPage<Endereco> getEndereco(Integer pagina) {
        return new DataPage<>(repo.findAll(pageRequestForAsc(pagina, "cep")));
    }

    public void salvar(Endereco endereco) {
        repo.save(endereco);
    }

    public Endereco recuperarPeloId(Long id) {
        return repo.findOne(id);
    }

    public void remover(Endereco endereco) {
        repo.delete(endereco);
    }

    public List<Endereco> getTodosEnderecos() {
        return repo.findAll(new Sort(new Sort.Order(ASC, "cep")));
    }

    public Endereco carregar(Long id) {
        return repo.findOne(id);
    }
}
