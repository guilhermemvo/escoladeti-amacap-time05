package br.unicesumar.time5.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import br.unicesumar.time5.controller.DataPage;
import static br.unicesumar.time5.controller.DataPage.pageRequestForAsc;
import br.unicesumar.time5.entity.UsuarioPerfilAcesso;
import br.unicesumar.time5.repository.UsuarioPerfilAcessoRepository;
import java.util.List;
import org.springframework.data.domain.Sort;
import static org.springframework.data.domain.Sort.Direction.ASC;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UsuarioPerfilAcessoService {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioPerfilAcessoService.class);

    @Autowired
    private UsuarioPerfilAcessoRepository usuarioPerfilAcessoRepository;

    public DataPage<UsuarioPerfilAcesso> getUsuarioPerfilAcesso(Integer pagina) {
        return new DataPage<>(usuarioPerfilAcessoRepository.findAll(pageRequestForAsc(pagina, "descricao")));
    }

    public void salvar(UsuarioPerfilAcesso usuarioPerfilAcesso) {
        usuarioPerfilAcessoRepository.save(usuarioPerfilAcesso);
    }

    public UsuarioPerfilAcesso recuperarPeloId(Long id) {
        return usuarioPerfilAcessoRepository.findOne(id);
    }

    public void remover(UsuarioPerfilAcesso usuarioPerfilAcesso) {
        usuarioPerfilAcessoRepository.delete(usuarioPerfilAcesso);
    }

    public List<UsuarioPerfilAcesso> getTodosUsuarioPerfilAcessos() {
        return usuarioPerfilAcessoRepository.findAll(new Sort(new Sort.Order(ASC, "descricao")));
    }

    public UsuarioPerfilAcesso carregar(Long id) {
        return usuarioPerfilAcessoRepository.findOne(id);
    }

    public UsuarioPerfilAcessoRepository getUsuarioPerfilAcessoRepository() {
        return usuarioPerfilAcessoRepository;
    }

}
