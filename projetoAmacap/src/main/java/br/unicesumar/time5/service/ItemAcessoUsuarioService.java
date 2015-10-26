package br.unicesumar.time5.service;

import br.unicesumar.time5.repository.ItemAcessoUsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ItemAcessoUsuarioService {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    @Autowired
    private ItemAcessoUsuarioRepository itemAcessoUsuarioRepository;

    public ItemAcessoUsuarioRepository getItemAcessoUsuarioRepository() {
        return itemAcessoUsuarioRepository;
    }

}
