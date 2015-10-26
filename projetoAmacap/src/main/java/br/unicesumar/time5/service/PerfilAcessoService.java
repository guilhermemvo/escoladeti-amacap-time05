package br.unicesumar.time5.service;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.unicesumar.time5.controller.DataPage;
import static br.unicesumar.time5.controller.DataPage.pageRequestForAsc;
import br.unicesumar.time5.entity.PerfilAcesso;
import br.unicesumar.time5.repository.PerfilAcessoRepository;
import java.util.List;
import org.springframework.data.domain.Sort;
import static org.springframework.data.domain.Sort.Direction.ASC;

@Service
@Transactional
public class PerfilAcessoService {

    private static final Logger logger = LoggerFactory.getLogger(PerfilAcessoService.class);

    @Autowired
    private PerfilAcessoRepository perfilAcessoRepository;

    public DataPage<PerfilAcesso> getPerfilAcesso(Integer pagina) {
        return new DataPage<>(perfilAcessoRepository.findAll(pageRequestForAsc(pagina, "descricao")));
    }

    public void salvar(PerfilAcesso perfilAcesso) {
        perfilAcessoRepository.save(perfilAcesso);
    }

    public PerfilAcesso recuperarPeloId(Long id) {
        return perfilAcessoRepository.findOne(id);
    }

    public void remover(PerfilAcesso perfilAcesso) {
        perfilAcessoRepository.delete(perfilAcesso);
    }

    public List<PerfilAcesso> getTodosPerfilAcessos() {
        return perfilAcessoRepository.findAll(new Sort(new Sort.Order(ASC, "descricao")));
    }

    public PerfilAcesso carregar(Long id) {
        return perfilAcessoRepository.findOne(id);
    }

    public PerfilAcessoRepository getPerfilAcessoRepository() {
        return perfilAcessoRepository;
    }
}
