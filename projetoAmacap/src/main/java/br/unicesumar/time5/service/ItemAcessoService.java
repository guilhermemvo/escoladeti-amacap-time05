package br.unicesumar.time5.service;

import br.unicesumar.time5.repository.ItemAcessoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ItemAcessoService {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    @Autowired
    private ItemAcessoRepository itemAcessoRepository;

    public ItemAcessoRepository getItemAcessoRepository() {
        return itemAcessoRepository;
    }

}
