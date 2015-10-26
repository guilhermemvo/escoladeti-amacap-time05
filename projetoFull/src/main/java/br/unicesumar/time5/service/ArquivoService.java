package br.unicesumar.time5.service;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.unicesumar.time5.controller.DataPage;
import br.unicesumar.time5.entity.Arquivo;
import br.unicesumar.time5.repository.ArquivoRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import static org.springframework.data.domain.Sort.Direction.ASC;

@Service
@Transactional
public class ArquivoService extends AbstractService<Arquivo> {

    private static final Logger logger = LoggerFactory.getLogger(ArquivoService.class);

    @Autowired
    private ArquivoRepository arquivoRepository;

    public DataPage<Arquivo> getArquivos(Integer pagina) {
        return findAll(pagina);
    }

    public void salvar(Arquivo arquivo) {
        arquivoRepository.save(arquivo);
    }

    public Arquivo recuperarPeloId(Long id) {
        return arquivoRepository.findOne(id);
    }

    public void remover(Arquivo arquivo) {
        arquivoRepository.delete(arquivo);
    }

    public List<Arquivo> getTodosArquivos() {
        return arquivoRepository.findAll(new Sort(new Sort.Order(ASC, "origem")));
    }

    public Arquivo carregar(Long id) {
        return arquivoRepository.findOne(id);
    }

    public ArquivoRepository getArquivoRepository() {
        return arquivoRepository;
    }

    public Page<Arquivo> executeQueryEncontrarPorOrigem(int pagina, int totalPorPagina, String origem) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, ordenarPorChave());
        return arquivoRepository.findByOrigemLike(pageRequest, "%" + origem.toUpperCase() + "%");
    }

   
    @Override
    public Page<Arquivo> executeQueryEncontrarTodos(int pagina, int totalPorPagina) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, ordenarPorChave());

        return arquivoRepository.findAll(pageRequest);
    }

    @Override
    public Page<Arquivo> executeQueryEncontrarPorNome(int pagina, int totalPorPagina, String nome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public DataPage<Arquivo> procurarPorNome(int pagina, String nome){        
        return findByNameLike(pagina, nome);
    }    

}
