package br.unicesumar.time5.service;

import br.unicesumar.time5.entity.Nota;
import br.unicesumar.time5.repository.NotaRepository;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NotaService extends AbstractService<Nota> {

    @Autowired
    private NotaRepository repositorio;
    
    public void salvar(Nota nota) {
        repositorio.save(nota);       
    }

    @Override
    public Page<Nota> executeQueryEncontrarPorNome(int pagina, int totalPorPagina, String nome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Page<Nota> executeQueryEncontrarTodos(int pagina, int totalPorPagina) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
