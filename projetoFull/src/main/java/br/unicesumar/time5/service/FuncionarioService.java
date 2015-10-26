package br.unicesumar.time5.service;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.unicesumar.time5.controller.DataPage;
import br.unicesumar.time5.entity.Funcionario;
import br.unicesumar.time5.repository.FuncionarioRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import static org.springframework.data.domain.Sort.Direction.ASC;

@Service
@Transactional
public class FuncionarioService extends AbstractService<Funcionario> {

    private static final Logger logger = LoggerFactory.getLogger(FuncionarioService.class);

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public DataPage<Funcionario> getFuncionarios(Integer pagina) {
        return findAll(pagina);
    }

    public void salvar(Funcionario funcionario) {
//        Funcionario p1 = funcionarioRepository.findByCpf(funcionario.getCpf());
//        if (p1 == null) {
//            funcionarioRepository.save(funcionario);
//        }
        funcionarioRepository.save(funcionario);

    }

    public Funcionario recuperarPeloId(Long id) {
        return funcionarioRepository.findOne(id);
    }

    public void remover(Funcionario funcionario) {
        funcionarioRepository.delete(funcionario);
    }

    public List<Funcionario> getTodosFuncionarios() {
        return funcionarioRepository.findAll(new Sort(new Sort.Order(ASC, "nome")));
    }

    public Funcionario carregar(Long id) {
        return funcionarioRepository.findOne(id);
    }

    public FuncionarioRepository getFuncionarioRepository() {
        return funcionarioRepository;
    }

    @Override
    public Page<Funcionario> executeQueryEncontrarPorNome(int pagina, int totalPorPagina, String nome) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, ordenarPorChave());

        return funcionarioRepository.findByNomeLike(pageRequest, "%" + nome.toUpperCase() + "%");
    }

    @Override
    public Page<Funcionario> executeQueryEncontrarTodos(int pagina, int totalPorPagina) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, ordenarPorChave());

        return funcionarioRepository.findAll(pageRequest);
    }

    public Funcionario BuscaFuncionarioPorCPF(String cpf) {
        return funcionarioRepository.findByCpf(cpf);
    }

    public DataPage<Funcionario> procurarPorNome(int pagina, String nome) {
        return findByNameLike(pagina, nome);
    }
}
