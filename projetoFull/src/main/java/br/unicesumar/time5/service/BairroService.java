package br.unicesumar.time5.service;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.unicesumar.time5.controller.DataPage;
import br.unicesumar.time5.entity.Bairro;
import br.unicesumar.time5.repository.BairroRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import static org.springframework.data.domain.Sort.Direction.ASC;

@Service
@Transactional
public class BairroService extends AbstractService<Bairro> {

    private static final Logger logger = LoggerFactory.getLogger(BairroService.class);

    @Autowired
    private BairroRepository bairroRepository;

    public DataPage<Bairro> getBairros(Integer pagina) {
        return findAll(pagina);
    }

    public void salvar(Bairro bairro) {
        bairroRepository.save(bairro);
    }

    public Bairro recuperarPeloId(Long id) {
        return bairroRepository.findOne(id);
    }

    public void remover(Bairro bairro) {
        bairroRepository.delete(bairro);
    }

    public List<Bairro> getTodosBairros() {
        return bairroRepository.findAll(new Sort(new Sort.Order(ASC, "nome")));
    }

    public Bairro carregar(Long id) {
        return bairroRepository.findOne(id);
    }

    public BairroRepository getBairroRepository() {
        return bairroRepository;
    }

    @Override
    public Page<Bairro> executeQueryEncontrarPorNome(int pagina, int totalPorPagina, String nome) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, ordenarPorChave());
        return bairroRepository.findByNomeLike(pageRequest, "%" + nome.toUpperCase() + "%");
    }

    @Override
    public Page<Bairro> executeQueryEncontrarTodos(int pagina, int totalPorPagina) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, ordenarPorChave());

        return bairroRepository.findAll(pageRequest);
    }
    
    public List<Bairro> procurarBairroPorIdCidade(Long id){
        return bairroRepository.encontrarBairroPorIdCidade(id);
        
        /*List<Bairro> bairros =  new ArrayList<>();
        for(Bairro bairro : getTodosBairros()){
            
            if(bairro.getCidade().getId().equals(id)){
                bairros.add(bairro);
            }
        }
        return bairros;
        */
    }
    
    public DataPage<Bairro> procurarPorNome(int pagina, String nome){        
        return findByNameLike(pagina, nome);
    }    
}
