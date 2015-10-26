package br.unicesumar.time5.service;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.unicesumar.time5.controller.DataPage;
import br.unicesumar.time5.entity.Foto;
import br.unicesumar.time5.repository.FotoRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import static org.springframework.data.domain.Sort.Direction.ASC;

@Service
@Transactional
public class FotoService extends AbstractService<Foto> {

    private static final Logger logger = LoggerFactory.getLogger(FotoService.class);

    @Autowired
    private FotoRepository fotoRepository;

    public DataPage<Foto> getFotos(Integer pagina) {
        return findAll(pagina);
    }

    public void salvar(Foto foto) {
        fotoRepository.save(foto);
    }

    public Foto recuperarPeloId(Long id) {
        return fotoRepository.findOne(id);
    }

    public void remover(Foto foto) {
        fotoRepository.delete(foto);
    }

    public List<Foto> getTodosFotos() {
        return fotoRepository.findAll(new Sort(new Sort.Order(ASC, "origem")));
    }

    public Foto carregar(Long id) {
        return fotoRepository.findOne(id);
    }

    public FotoRepository getFotoRepository() {
        return fotoRepository;
    }

    public List<Foto> buscaFotos(Long idEvento) {
        List<Foto> fotosGaleria = fotoRepository.findByEvento(idEvento);
        return fotosGaleria;
    }

    @Override
    public Page<Foto> executeQueryEncontrarTodos(int pagina, int totalPorPagina) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, ordenarPorChave());

        return fotoRepository.findAll(pageRequest);
    }

    @Override
    public Page<Foto> executeQueryEncontrarPorNome(int pagina, int totalPorPagina, String nome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
