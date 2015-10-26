package br.unicesumar.time5.service;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.unicesumar.time5.controller.DataPage;
import br.unicesumar.time5.entity.TipoMaterial;
import br.unicesumar.time5.repository.TipoMaterialRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import static org.springframework.data.domain.Sort.Direction.ASC;

@Service
@Transactional
public class TipoMaterialService extends AbstractService<TipoMaterial> {

    private static final Logger logger = LoggerFactory.getLogger(TipoMaterialService.class);

    @Autowired
    private TipoMaterialRepository tipoMaterialRepository;

    public DataPage<TipoMaterial> getTipoMaterial(Integer pagina) {
        return findAll(pagina);
    }

    public void salvar(TipoMaterial tipoMaterial) {
        tipoMaterialRepository.save(tipoMaterial);
    }

    public TipoMaterial recuperarPeloId(Long id) {
        return tipoMaterialRepository.findOne(id);
    }

    public void remover(TipoMaterial tipoMaterial) {
        tipoMaterialRepository.delete(tipoMaterial);
    }

    public List<TipoMaterial> getTodasTipoMaterial() {
        return tipoMaterialRepository.findAll(new Sort(new Sort.Order(ASC, "descricao")));
    }

    public TipoMaterial carregar(Long id) {
        return tipoMaterialRepository.findOne(id);
    }

    public TipoMaterialRepository getTipoMaterialRepository() {
        return tipoMaterialRepository;
    }

    public Page<TipoMaterial> executeQueryEncontrarPorDescricao(int pagina, int totalPorPagina, String descricao) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, new Sort(Sort.Direction.ASC, "descricao"));

        return tipoMaterialRepository.findByDescricaoLike(pageRequest, "%" + descricao.toUpperCase() + "%");
    }

    @Override
    public Page<TipoMaterial> executeQueryEncontrarTodos(int pagina, int totalPorPagina) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, new Sort(Sort.Direction.ASC, "descricao"));

        return tipoMaterialRepository.findAll(pageRequest);
    }

    @Override
    public Page<TipoMaterial> executeQueryEncontrarPorNome(int pagina, int totalPorPagina, String nome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public DataPage<TipoMaterial> procurarPorNome(int pagina, String nome){        
        return findByNameLike(pagina, nome);
    }    
}
