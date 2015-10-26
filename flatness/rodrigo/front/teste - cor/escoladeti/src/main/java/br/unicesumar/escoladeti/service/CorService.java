package br.unicesumar.escoladeti.service;

import br.unicesumar.escoladeti.controller.DataPage;
import static br.unicesumar.escoladeti.controller.DataPage.pageRequestForAsc;
import br.unicesumar.escoladeti.entity.Cor;
import br.unicesumar.escoladeti.repository.CorRepository;
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
public class CorService {

    @Autowired
    private CorRepository repo;

    public DataPage<Cor> getCor(Integer pagina) {
        return new DataPage<>(repo.findAll(pageRequestForAsc(pagina, "nome")));
    }

    public void salvar(Cor cor) {
        repo.save(cor);
    }

    public Cor recuperarPeloId(Long id) {
        return repo.findOne(id);
    }

    public void remover(Cor cor) {
        repo.delete(cor);
    }

    public List<Cor> getTodasCor() {
        return repo.findAll(new Sort(new Sort.Order(ASC, "nome")));
    }

    public Cor carregar(Long id) {
        return repo.findOne(id);
    }
}
