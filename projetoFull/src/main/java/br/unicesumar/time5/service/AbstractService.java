package br.unicesumar.time5.service;

import br.unicesumar.time5.controller.DataPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

public abstract class AbstractService<T> {

    private final Integer totalRegistrosPorPagina = 8;

    @Transactional(readOnly = true)
    public DataPage findAll(int pagina) {
        Page<T> resultado = executeQueryEncontrarTodos(pagina, totalRegistrosPorPagina);

        if (irParaUltimaPaginaContendoDados(pagina, resultado)) {
            int ultimaPagina = resultado.getTotalPages() - 1;
            resultado = executeQueryEncontrarTodos(ultimaPagina, totalRegistrosPorPagina);
        }

        return construirPagina(resultado);
    }

    @Transactional(readOnly = true)
    public DataPage<T> findByNameLike(int pagina, String nomeDescricao) {

        Page<T> resultado = executeQueryEncontrarPorNome(pagina, totalRegistrosPorPagina, nomeDescricao);

        if (irParaUltimaPaginaContendoDados(pagina, resultado)) {
            int ultimaPagina = resultado.getTotalPages() - 1;
            resultado = executeQueryEncontrarPorNome(ultimaPagina, totalRegistrosPorPagina, nomeDescricao);
        }

        return construirPagina(resultado);
    }

    private DataPage construirPagina(Page<T> result) {
        return new DataPage(result);
    }

    public abstract Page<T> executeQueryEncontrarPorNome(int pagina, int totalPorPagina, String nome);
    public abstract Page<T> executeQueryEncontrarTodos(int pagina, int totalPorPagina);

    protected Sort ordenarPorChave() {
        return new Sort(Sort.Direction.ASC, "nome");
    }

    private boolean irParaUltimaPaginaContendoDados(int pagina, Page<T> resultado) {
        return isUltimaPagina(pagina, resultado) && existeRegistros(resultado);
    }

    private boolean isUltimaPagina(int pagina, Page<T> resultado) {
        return pagina >= resultado.getTotalPages() - 1;
    }

    private boolean existeRegistros(Page<T> resultado) {
        return resultado.getTotalElements() > 0;
    }
}
