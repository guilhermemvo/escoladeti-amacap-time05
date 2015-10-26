package br.unicesumar.escoladeti.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.unicesumar.escoladeti.entity.Usuario;
import br.unicesumar.escoladeti.repository.UsuarioRepository;

@Service
@Transactional
public class UsuarioService {
	
	private static final Logger logger = LoggerFactory.getLogger(UsuarioService.class);
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	public UsuarioRepository getUsuarioRepository() {
		return usuarioRepository;
	}

	public void inicializarUsuarioAdmin() {
		logger.info("Verificando existência do usuário 'admin'...");
		Usuario root = getUsuarioRepository().findByLogin("admin");
		if (root == null) {
			logger.info("Usuário 'admin' não encontrado, criando...");

			root = new Usuario();
			root.setLogin("admin");
			root.setSenha("123mudar");
			root.setAtivo(true);
			
			logger.debug("Salvando {}", root);
			getUsuarioRepository().save(root);
			
			inicializarUsuarios();
		}
		logger.info("Usuário 'admin' verificado.");
	}

	private void inicializarUsuarios() {
		Usuario novo = null;
		for (int i = 1; i <= 2; i++) {
			novo = new Usuario();
			novo.setLogin("login_" + i);
			novo.setSenha("senha_" + i);
			novo.setAtivo(Boolean.TRUE);

			logger.debug("Salvando {}", novo);
			getUsuarioRepository().save(novo);
		}
	}

}
