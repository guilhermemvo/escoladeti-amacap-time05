package br.unicesumar.time5.service;

import br.unicesumar.time5.controller.DataPage;
import br.unicesumar.time5.entity.Pais;
import br.unicesumar.time5.entity.UnidadeFederativa;
import br.unicesumar.time5.repository.PaisRepository;
import br.unicesumar.time5.repository.UnidadeFederativaRepository;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import static org.springframework.data.domain.Sort.Direction.ASC;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UnidadeFederativaService extends AbstractService<UnidadeFederativa> {

    private static final Logger logger = LoggerFactory.getLogger(UnidadeFederativaService.class);

    @Autowired
    private UnidadeFederativaRepository unidadefederativaRepository;
    
    private PaisService servicePais;

    public DataPage<UnidadeFederativa> getUnidadesFederativas(Integer pagina) {
        return findAll(pagina);
    }

    public void salvar(UnidadeFederativa unidadefederativa) {
        unidadefederativaRepository.save(unidadefederativa);
    }

    public UnidadeFederativa recuperarPeloId(Long id) {
        return unidadefederativaRepository.findOne(id);
    }

    public void remover(UnidadeFederativa unidadefederativa) {
        unidadefederativaRepository.delete(unidadefederativa);
    }

    public List<UnidadeFederativa> getTodasUnidadesFederativas() {
        return unidadefederativaRepository.findAll(new Sort(new Sort.Order(ASC, "nome")));
    }

    public UnidadeFederativa carregar(Long id) {
        return unidadefederativaRepository.findOne(id);
    }

    public UnidadeFederativaRepository getUnidadeFederativaRepository() {
        return unidadefederativaRepository;
    }

    public UnidadeFederativa getCodeLocalization(long codigoIBGE) {
        return getUnidadeFederativaRepository().findByCodigoIBGE(codigoIBGE);
    }


    @Override
    public Page<UnidadeFederativa> executeQueryEncontrarPorNome(int pagina, int totalPorPagina, String nome) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, ordenarPorChave());
        return unidadefederativaRepository.findByNomeLike(pageRequest, "%" + nome.toUpperCase() + "%");
    }

    @Override   
    public Page<UnidadeFederativa> executeQueryEncontrarTodos(int pagina, int totalPorPagina) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, ordenarPorChave());

        return unidadefederativaRepository.findAll(pageRequest);
    }

    public List<UnidadeFederativa> findbyCodigoPais(Long id) {
        return unidadefederativaRepository.procurarUnidadeFederativaPeloIdDoPais(id);
        /*
        List<UnidadeFederativa> ufs = new ArrayList<>();
        for(UnidadeFederativa uf : getTodasUnidadesFederativas()){
            if(uf.getPais().getId().equals(id)){
                ufs.add(uf);
            }
        }        
        return ufs;
        */
    }
    
    public DataPage<UnidadeFederativa> procurarPorNome(int pagina, String nome){        
        return findByNameLike(pagina, nome);
    }    
}
