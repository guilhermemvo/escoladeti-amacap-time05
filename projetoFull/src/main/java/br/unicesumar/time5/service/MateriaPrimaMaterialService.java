package br.unicesumar.time5.service;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.unicesumar.time5.controller.DataPage;
import br.unicesumar.time5.entity.MateriaPrimaMaterial;
import br.unicesumar.time5.repository.MateriaPrimaMaterialRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import static org.springframework.data.domain.Sort.Direction.ASC;

@Service
@Transactional
public class MateriaPrimaMaterialService extends AbstractService<MateriaPrimaMaterial> {

    private static final Logger logger = LoggerFactory.getLogger(MateriaPrimaMaterialService.class);

    @Autowired
    private MateriaPrimaMaterialRepository materiaprimamaterialRepository;

    public DataPage<MateriaPrimaMaterial> getMateriaPrimaMaterials(Integer pagina) {
        return findAll(pagina);
    }

    public void salvar(MateriaPrimaMaterial materiaprimamaterial) {
        materiaprimamaterialRepository.save(materiaprimamaterial);
    }

    public MateriaPrimaMaterial recuperarPeloId(Long id) {
        return materiaprimamaterialRepository.findOne(id);
    }

    public void remover(MateriaPrimaMaterial materiaprimamaterial) {
        materiaprimamaterialRepository.delete(materiaprimamaterial);
    }

    public List<MateriaPrimaMaterial> getTodosMateriaPrimaMaterials() {
        return materiaprimamaterialRepository.findAll(new Sort(new Sort.Order(ASC, "quantidade")));
    }

    public MateriaPrimaMaterial carregar(Long id) {
        return materiaprimamaterialRepository.findOne(id);
    }

    public MateriaPrimaMaterialRepository getMateriaPrimaMaterialRepository() {
        return materiaprimamaterialRepository;
    }

   
    @Override
    public Page<MateriaPrimaMaterial> executeQueryEncontrarTodos(int pagina, int totalPorPagina) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, ordenarPorChave());

        return materiaprimamaterialRepository.findAll(pageRequest);
    }

    @Override
    public Page<MateriaPrimaMaterial> executeQueryEncontrarPorNome(int pagina, int totalPorPagina, String nome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
