package br.unicesumar.time5.service;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.unicesumar.time5.controller.DataPage;
import static br.unicesumar.time5.controller.DataPage.pageRequestForAsc;
import br.unicesumar.time5.entity.TipoTelefone;
import br.unicesumar.time5.repository.TipoTelefoneRepository;
import java.util.List;
import org.springframework.data.domain.Sort;
import static org.springframework.data.domain.Sort.Direction.ASC;

@Service
@Transactional
public class TipoTelefoneService {

    private static final Logger logger = LoggerFactory.getLogger(TipoTelefoneService.class);

    @Autowired
    private TipoTelefoneRepository tipotelefoneRepository;

    public DataPage<TipoTelefone> getTiposTelefone(Integer pagina) {
        return new DataPage<>(tipotelefoneRepository.findAll(pageRequestForAsc(pagina, "nome")));
    }

    public void salvar(TipoTelefone tipotelefone) {
        tipotelefoneRepository.save(tipotelefone);
    }

    public TipoTelefone recuperarPeloId(Long id) {
        return tipotelefoneRepository.findOne(id);
    }

    public void remover(TipoTelefone tipotelefone) {
        tipotelefoneRepository.delete(tipotelefone);
    }

    public List<TipoTelefone> getTodosTiposTelefone() {
        return tipotelefoneRepository.findAll(new Sort(new Sort.Order(ASC, "nome")));
    }

    public TipoTelefone carregar(Long id) {
        return tipotelefoneRepository.findOne(id);
    }

    public TipoTelefoneRepository getTipoTelefoneRepository() {
        return tipotelefoneRepository;
    }

}
