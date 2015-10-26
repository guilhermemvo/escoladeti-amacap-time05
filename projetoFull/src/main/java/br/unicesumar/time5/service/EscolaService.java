package br.unicesumar.time5.service;

import br.unicesumar.time5.controller.DataPage;
import br.unicesumar.time5.entity.Escola;
import br.unicesumar.time5.repository.EscolaRepository;
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
public class EscolaService extends AbstractService<Escola> {

    private static final Logger logger = LoggerFactory.getLogger(EscolaService.class);

    @Autowired
    private EscolaRepository escolaRepository;

    public DataPage<Escola> getEscolas(Integer pagina) {
        return findAll(pagina);
    }

    public Escola salvar(Escola escola) {
//        Escola p1 = escolaRepository.findByCnpj(escola.getCnpj());
//        if (p1 == null) {
//            escolaRepository.save(escola);
//        }

        // escolaRepository.save(escola);
        return escolaRepository.saveAndFlush(escola);

    }

    public Escola recuperarPeloId(Long id) {
        return escolaRepository.findOne(id);
    }

    public void remover(Escola escola) {
        escolaRepository.delete(escola);
    }

    public List<Escola> getTodosEscolas() {
        return escolaRepository.findAll(new Sort(new Sort.Order(ASC, "razao")));
    }

    public Escola carregar(Long id) {
        return escolaRepository.findOne(id);
    }

    public EscolaRepository getEscolaRepository() {
        return escolaRepository;
    }

    @Override
    public Page<Escola> executeQueryEncontrarPorNome(int pagina, int totalPorPagina, String nome) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, ordenarPorChave());

        return escolaRepository.findByRazaoLike(pageRequest, "%" + nome.toUpperCase() + "%");
    }

    @Override
    public Page<Escola> executeQueryEncontrarTodos(int pagina, int totalPorPagina) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, new Sort(Sort.Direction.ASC, "razao"));

        return escolaRepository.findAll(pageRequest);
    }

    public Escola BuscaEscolaPorCNPJ(String cnpj) {
        return escolaRepository.findByCnpj(cnpj);
    }

    public DataPage<Escola> procurarPorNome(int pagina, String nome) {
        return findByNameLike(pagina, nome);
    }

    public List<Escola> procurarEscolaFantasiaRazaoCnpj(String valor) {
        if (valor.length() >= 3) {
            return escolaRepository.findByCnpjLikeOrRazaoLikeIgnoreCaseOrFantasiaLikeIgnoreCaseOrderByFantasiaAsc("%" + valor + "%", "%" + valor + "%", "%" + valor + "%");
        } else {
            return null;
        }
    }

}
