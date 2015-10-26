package br.unicesumar.time5.service;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.unicesumar.time5.controller.DataPage;
import static br.unicesumar.time5.controller.DataPage.pageRequestForAsc;
import br.unicesumar.time5.entity.Telefone;
import br.unicesumar.time5.repository.TelefoneRepository;
import java.util.List;
import org.springframework.data.domain.Sort;
import static org.springframework.data.domain.Sort.Direction.ASC;

@Service
@Transactional
public class TelefoneService {

    private static final Logger logger = LoggerFactory.getLogger(TelefoneService.class);

    @Autowired
    private TelefoneRepository telefoneRepository;

    public DataPage<Telefone> getTelefones(Integer pagina) {
        return new DataPage<>(telefoneRepository.findAll(pageRequestForAsc(pagina, "pes_id")));
    }

    public void salvar(Telefone telefone) {
        telefoneRepository.save(telefone);
    }

    public Telefone recuperarPeloId(Long id) {
        return telefoneRepository.findOne(id);
    }

    public void remover(Telefone telefone) {
        telefoneRepository.delete(telefone);
    }

    public List<Telefone> getTodosTelefones() {
        return telefoneRepository.findAll(new Sort(new Sort.Order(ASC, "nome")));
    }

    public Telefone carregar(Long id) {
        return telefoneRepository.findOne(id);
    }

    public TelefoneRepository getTelefoneRepository() {
        return telefoneRepository;
    }

}
