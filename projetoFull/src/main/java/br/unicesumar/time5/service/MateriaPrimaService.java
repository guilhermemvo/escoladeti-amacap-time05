package br.unicesumar.time5.service;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.unicesumar.time5.controller.DataPage;
import static br.unicesumar.time5.controller.DataPage.pageRequestForAsc;
import br.unicesumar.time5.entity.MateriaPrima;
import br.unicesumar.time5.repository.MateriaPrimaRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import static org.springframework.data.domain.Sort.Direction.ASC;

@Service
@Transactional
public class MateriaPrimaService extends AbstractService<MateriaPrima>{

    private static final Logger logger = LoggerFactory.getLogger(MateriaPrimaService.class);

    @Autowired
    private MateriaPrimaRepository materiaPrimaRepository;

    public DataPage<MateriaPrima> getMateriaPrima(Integer pagina) {
        return findAll(pagina);
    }

    public void salvar(MateriaPrima materiaPrima) {
        materiaPrimaRepository.save(materiaPrima);
    }

    public MateriaPrima recuperarPeloId(Long id) {
        return materiaPrimaRepository.findOne(id);
    }

    public void remover(MateriaPrima materiaPrima) {
        materiaPrimaRepository.delete(materiaPrima);
    }

    public List<MateriaPrima> getTodasMateriaPrima() {
        return materiaPrimaRepository.findAll(new Sort(new Sort.Order(ASC, "nome")));
    }

    public MateriaPrima carregar(Long id) {
        return materiaPrimaRepository.findOne(id);
    }

    public MateriaPrimaRepository getMateriaPrimaRepository() {
        return materiaPrimaRepository;
    }

    @Override
    public Page<MateriaPrima> executeQueryEncontrarPorNome(int pagina, int totalPorPagina, String nome) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, ordenarPorChave());

        return materiaPrimaRepository.findByNomeLike(pageRequest, "%" + nome + "%");
    }

    @Override
    public Page<MateriaPrima> executeQueryEncontrarTodos(int pagina, int totalPorPagina) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, ordenarPorChave());

        return materiaPrimaRepository.findAll(pageRequest);
    }

}
