package br.unicesumar.time5.service;

import br.unicesumar.time5.entity.Material;
import br.unicesumar.time5.repository.LivroRepository;
import br.unicesumar.time5.repository.MaterialRepository;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MaterialService extends AbstractService<Material>{

    private static final Logger logger = LoggerFactory.getLogger(MaterialService.class);

    @Autowired
    private MaterialRepository materialRepository;
    @Autowired
    private LivroRepository livroRepository;

    public List<Material> procurarMaterial(String nome) {
        List<Material> listaalunos = new ArrayList<>();
        listaalunos.addAll(materialRepository.findByNomeLike("%" + nome.toUpperCase() + "%"));
        listaalunos.addAll(livroRepository.findByPnldLike("%" + nome.toUpperCase() + "%"));
        return listaalunos;
    }

    public List<Material> getTodosMaterial() {
        return materialRepository.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "nome")));
    }

    public List<Material> salvar(List<Material> material) {
        List<Material> materiais = new ArrayList();
        for (Material m : material) {
            materiais.add(materialRepository.saveAndFlush(m));
        }
        return materiais;
    }

    @Override
    public Page<Material> executeQueryEncontrarPorNome(int pagina, int totalPorPagina, String nome) {
        return null;
    }

    @Override
    public Page<Material> executeQueryEncontrarTodos(int pagina, int totalPorPagina) {
         final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, ordenarPorChave());

        return materialRepository.findAll(pageRequest);
    }

}
