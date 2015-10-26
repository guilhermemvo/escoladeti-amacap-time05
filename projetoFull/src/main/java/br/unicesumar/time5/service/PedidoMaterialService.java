package br.unicesumar.time5.service;

import br.unicesumar.time5.repository.PedidoMaterialRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import br.unicesumar.time5.controller.DataPage;
import br.unicesumar.time5.entity.Material;
import br.unicesumar.time5.entity.PedidoMaterial;
import br.unicesumar.time5.repository.UnidadeProducaoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PedidoMaterialService {

    private static final Logger logger = LoggerFactory.getLogger(PedidoMaterialService.class);

    private final Integer totalRegistrosPorPagina = 8;

    @Autowired
    private PedidoMaterialRepository materialRepository;

    @Autowired
    private UnidadeProducaoRepository unidadeProducaoRepository;

    @Transactional(readOnly = true)
    public DataPage todosRecebidos(int pagina) {
        final PageRequest pageRequest = new PageRequest(pagina, totalRegistrosPorPagina, new Sort(Sort.Direction.DESC, "id"));

        Page<PedidoMaterial> resultado = materialRepository.findByRecebidoTrue(pageRequest);

        if (irParaUltimaPaginaContendoDados(pagina, resultado)) {
            int ultimaPagina = resultado.getTotalPages() - 1;
            resultado = materialRepository.findByRecebidoTrue(pageRequest);
        }

        return construirPagina(resultado);
    }

    private DataPage construirPagina(Page<PedidoMaterial> result) {
        return new DataPage(result);
    }

    private boolean irParaUltimaPaginaContendoDados(int pagina, Page<PedidoMaterial> resultado) {
        return isUltimaPagina(pagina, resultado) && existeRegistros(resultado);
    }

    public String salvarid(Long id, Boolean status) {
        PedidoMaterial m1 = materialRepository.findOne(id);
        m1.setRecebido(status);
        materialRepository.save(m1);
        return "OK";
    }

    public String salvarUnidadeProducao(Long id, Long idUnidadeProducao) {
        PedidoMaterial m1 = materialRepository.findOne(id);
        m1.setUnidadeProducao(unidadeProducaoRepository.findOne(idUnidadeProducao));
        materialRepository.save(m1);
        return "OK";
    }

    private boolean isUltimaPagina(int pagina, Page<PedidoMaterial> resultado) {
        return pagina >= resultado.getTotalPages() - 1;
    }

    private boolean existeRegistros(Page<PedidoMaterial> resultado) {
        return resultado.getTotalElements() > 0;
    }
}
