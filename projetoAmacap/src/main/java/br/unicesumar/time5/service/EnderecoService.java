package br.unicesumar.time5.service;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.unicesumar.time5.controller.DataPage;
import static br.unicesumar.time5.controller.DataPage.pageRequestForAsc;
import br.unicesumar.time5.entity.Endereco;
import br.unicesumar.time5.repository.EnderecoRepository;
import java.util.List;
import org.springframework.data.domain.Sort;
import static org.springframework.data.domain.Sort.Direction.ASC;

@Service
@Transactional
public class EnderecoService {

    private static final Logger logger = LoggerFactory.getLogger(EnderecoService.class);

    @Autowired
    private EnderecoRepository enderecoRepository;

    public DataPage<Endereco> getEnderecos(Integer pagina) {
        return new DataPage<>(enderecoRepository.findAll(pageRequestForAsc(pagina, "nome")));
    }

    public void salvar(Endereco endereco) {
        enderecoRepository.save(endereco);
    }

    public Endereco recuperarPeloId(Long id) {
        return enderecoRepository.findOne(id);
    }

    public void remover(Endereco endereco) {
        enderecoRepository.delete(endereco);
    }

    public List<Endereco> getTodosEnderecos() {
        return enderecoRepository.findAll(new Sort(new Sort.Order(ASC, "nome")));
    }

    public Endereco carregar(Long id) {
        return enderecoRepository.findOne(id);
    }

    public EnderecoRepository getEnderecoRepository() {
        return enderecoRepository;
    }

}
