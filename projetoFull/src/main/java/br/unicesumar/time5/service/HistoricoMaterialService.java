package br.unicesumar.time5.service;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.unicesumar.time5.controller.DataPage;
import br.unicesumar.time5.entity.HistoricoMaterial;
import br.unicesumar.time5.repository.HistoricoMaterialRepository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import static org.springframework.data.domain.Sort.Direction.ASC;

@Service
@Transactional
public class HistoricoMaterialService extends AbstractService<HistoricoMaterial> {

    private static final Logger logger = LoggerFactory.getLogger(HistoricoMaterialService.class);

    @Autowired
    private HistoricoMaterialRepository historicomaterialRepository;

    public DataPage<HistoricoMaterial> getHistoricoMaterials(Integer pagina) {
        return findAll(pagina);
    }

    public void salvar(HistoricoMaterial historicomaterial) {
        historicomaterialRepository.save(historicomaterial);
    }

    public HistoricoMaterial recuperarPeloId(Long id) {
        return historicomaterialRepository.findOne(id);
    }

    public void remover(HistoricoMaterial historicomaterial) {
        historicomaterialRepository.delete(historicomaterial);
    }

    public List<HistoricoMaterial> getTodasHistoricoMaterials() {
        return historicomaterialRepository.findAll(new Sort(new Sort.Order(ASC, "dataAlteracao")));
    }

    public HistoricoMaterial carregar(Long id) {
        return historicomaterialRepository.findOne(id);
    }

    public HistoricoMaterialRepository getHistoricoMaterialRepository() {
        return historicomaterialRepository;
    }

    //  public HistoricoMaterial getCodeLocalization(HistoricoMaterial unidadeFederativa) {
    //     return getHistoricoMaterialRepository().findByCodigoIBGE(unidadeFederativa.getCodigoIBGE());
    // }
    @Override
    public Page<HistoricoMaterial> executeQueryEncontrarTodos(int pagina, int totalPorPagina) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, ordenarPorChave());

        return historicomaterialRepository.findAll(pageRequest);
    }

    @Override
    public Page<HistoricoMaterial> executeQueryEncontrarPorNome(int pagina, int totalPorPagina, String nome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
