package br.unicesumar.time5.service;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.unicesumar.time5.controller.DataPage;
import br.unicesumar.time5.entity.Editora;
import br.unicesumar.time5.entity.Telefone;
import br.unicesumar.time5.repository.EditoraRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import static org.springframework.data.domain.Sort.Direction.ASC;

@Service
@Transactional
public class EditoraService extends AbstractService<Editora> {

    private static final Logger logger = LoggerFactory.getLogger(EditoraService.class);

    @Autowired
    private EditoraRepository editoraRepository;

    public DataPage<Editora> getEditoras(Integer pagina) {
        return findAll(pagina);
    }

    public void salvar(Editora editora) {
//        Editora p1 = editoraRepository.findByCnpj(editora.getCnpj());
//        if (p1 == null) {
//            editoraRepository.save(editora);
//        }
        editoraRepository.save(editora);

    }

    public Editora recuperarPeloId(Long id) {
        return editoraRepository.findOne(id);
    }

    public void remover(Editora editora) {
        editoraRepository.delete(editora);
    }

    public List<Editora> getTodosEditoras() {
        return editoraRepository.findAll(new Sort(new Sort.Order(ASC, "razao")));
    }

    public Editora carregar(Long id) {
        return editoraRepository.findOne(id);
    }

    public EditoraRepository getEditoraRepository() {
        return editoraRepository;
    }

    @Override
    public Page<Editora> executeQueryEncontrarPorNome(int pagina, int totalPorPagina, String nome) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, ordenarPorChave());

        return editoraRepository.findByRazaoLike(pageRequest, "%" + nome.toUpperCase() + "%");
    }

    @Override
    public Page<Editora> executeQueryEncontrarTodos(int pagina, int totalPorPagina) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, new Sort(Sort.Direction.ASC, "razao"));

        return editoraRepository.findAll(pageRequest);
    }

    public Editora BuscaEditoraPorCNPJ(String cnpj) {
        return editoraRepository.findByCnpj(cnpj);
    }
    
    public DataPage<Editora> procurarPorNome(int pagina, String nome){        
        return findByNameLike(pagina, nome);
    }    
}
