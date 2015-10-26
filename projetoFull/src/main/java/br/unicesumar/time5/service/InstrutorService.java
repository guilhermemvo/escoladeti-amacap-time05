package br.unicesumar.time5.service;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.unicesumar.time5.controller.DataPage;
import br.unicesumar.time5.entity.Instrutor;
import br.unicesumar.time5.repository.InstrutorRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import static org.springframework.data.domain.Sort.Direction.ASC;

@Service
@Transactional
public class InstrutorService extends AbstractService<Instrutor> {

    private static final Logger logger = LoggerFactory.getLogger(InstrutorService.class);

    @Autowired
    private InstrutorRepository instrutorRepository;

    public DataPage<Instrutor> getInstrutors(Integer pagina) {
        return findAll(pagina);
    }

    public void salvar(Instrutor instrutor) {
//        Instrutor p1 = instrutorRepository.findByCpf(instrutor.getCpf());
//        if (p1 == null) {
//            instrutorRepository.save(instrutor);
//        }
        instrutorRepository.save(instrutor);

    }

    public Instrutor recuperarPeloId(Long id) {
        return instrutorRepository.findOne(id);
    }

    public void remover(Instrutor instrutor) {
        instrutorRepository.delete(instrutor);
    }

    public List<Instrutor> getTodosInstrutors() {
        return instrutorRepository.findAll(new Sort(new Sort.Order(ASC, "nome")));
    }

    public Instrutor carregar(Long id) {
        return instrutorRepository.findOne(id);
    }

    public InstrutorRepository getInstrutorRepository() {
        return instrutorRepository;
    }

    @Override
    public Page<Instrutor> executeQueryEncontrarPorNome(int pagina, int totalPorPagina, String nome) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, ordenarPorChave());

        return instrutorRepository.findByNomeLike(pageRequest, "%" + nome.toUpperCase() + "%");
    }

    @Override
    public Page<Instrutor> executeQueryEncontrarTodos(int pagina, int totalPorPagina) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, ordenarPorChave());

        return instrutorRepository.findAll(pageRequest);
    }

    public Instrutor BuscaInstrutorPorCPF(String cpf) {
        return instrutorRepository.findByCpf(cpf);
    }
    
    public DataPage<Instrutor> procurarPorNome(int pagina, String nome){        
        return findByNameLike(pagina, nome);
    }        
}