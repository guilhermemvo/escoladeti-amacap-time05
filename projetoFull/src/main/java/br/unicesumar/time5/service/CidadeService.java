package br.unicesumar.time5.service;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.unicesumar.time5.controller.DataPage;
import br.unicesumar.time5.entity.Cidade;
import br.unicesumar.time5.repository.CidadeRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import static org.springframework.data.domain.Sort.Direction.ASC;

@Service
@Transactional
public class CidadeService extends AbstractService<Cidade> {

    private static final Logger logger = LoggerFactory.getLogger(CidadeService.class);

    @Autowired
    private CidadeRepository cidadeRepository;

    public DataPage<Cidade> getCidades(Integer pagina) {
        return findAll(pagina);
    }

    public void salvar(Cidade cidade) {
        cidadeRepository.save(cidade);
    }

    public Cidade recuperarPeloId(Long id) {
        return cidadeRepository.findOne(id);
    }

    public void remover(Cidade cidade) {
        cidadeRepository.delete(cidade);
    }

    public List<Cidade> getTodasCidades() {
        return cidadeRepository.findAll(new Sort(new Sort.Order(ASC, "nome")));
    }

    public Cidade carregar(Long id) {
        return cidadeRepository.findOne(id);
    }

    public CidadeRepository getCidadeRepository() {
        return cidadeRepository;
    }


    public Cidade getCodeLocalization(long codigoIBGE) {
        return getCidadeRepository().findByCodigoIBGE(codigoIBGE);
    }


    @Override
    public Page<Cidade> executeQueryEncontrarPorNome(int pagina, int totalPorPagina, String nome) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, ordenarPorChave());
        return cidadeRepository.findByNomeLike(pageRequest, "%" + nome.toUpperCase() + "%");
    }

    @Override
    public Page<Cidade> executeQueryEncontrarTodos(int pagina, int totalPorPagina) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, ordenarPorChave());

        return cidadeRepository.findAll(pageRequest);
    }
    
    public List<Cidade> procurarCidadePorIdUf(Long id){
        return cidadeRepository.procurarCidadePeloIdDaUnidadeFederativa(id);
        /*
        List<Cidade> cidades = new ArrayList<>();
        for(Cidade cidade : getTodasCidades()){
            if(cidade.getUnidadefederativa().getId().equals(id)){
                cidades.add(cidade);
            }
        }
        return cidades;
        */
    }

    public DataPage<Cidade> procurarPorNome(int pagina, String nome){        
        return findByNameLike(pagina, nome);
    }

}
