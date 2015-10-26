package br.unicesumar.time5.service;

import br.unicesumar.time5.controller.DataPage;
import static br.unicesumar.time5.controller.DataPage.pageRequestForAsc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.unicesumar.time5.entity.Usuario;
import br.unicesumar.time5.repository.UsuarioRepository;
import java.util.List;
import org.springframework.data.domain.Sort;
import static org.springframework.data.domain.Sort.Direction.ASC;

@Service
@Transactional
public class UsuarioService {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    @Autowired
    private UsuarioRepository usuarioRepository;

    public DataPage<Usuario> getUsuarios(Integer pagina) {
        return new DataPage<>(usuarioRepository.findAll(pageRequestForAsc(pagina, "login")));
    }

    public void salvar(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    public Usuario recuperarPeloId(Long id) {
        return usuarioRepository.findOne(id);
    }

    public void remover(Usuario usuario) {
        usuarioRepository.delete(usuario);
    }

    public List<Usuario> getTodosUsuarios() {
        return usuarioRepository.findAll(new Sort(new Sort.Order(ASC, "nome")));
    }

    public Usuario carregar(Long id) {
        return usuarioRepository.findOne(id);
    }

    public UsuarioRepository getUsuarioRepository() {
        return usuarioRepository;
    }

    public void inicializarUsuarioAdmin() {
        logger.info("Verificando existência do usuário 'admin'...");
        Usuario usuarioLogin = getUsuarioRepository().findByLogin("admin");
        if (usuarioLogin == null) {
            logger.info("Usuário 'admin' não encontrado, criando...");

            usuarioLogin = new Usuario();
            usuarioLogin.setLogin("admin");
            usuarioLogin.setSenha("admin");
            usuarioLogin.setAtivo(true);

            logger.debug("Salvando {}", usuarioLogin);
            getUsuarioRepository().save(usuarioLogin);

        }
        logger.info("Usuário 'admin' verificado.");
    }
}
