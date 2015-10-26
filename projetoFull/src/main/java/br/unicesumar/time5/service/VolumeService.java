package br.unicesumar.time5.service;

import br.unicesumar.time5.controller.DataPage;
import br.unicesumar.time5.entity.Pedido;
import br.unicesumar.time5.entity.PedidoMaterial;
import br.unicesumar.time5.entity.Volume;
import br.unicesumar.time5.repository.PedidoRepository;
import br.unicesumar.time5.repository.VolumeRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VolumeService extends AbstractService<Volume> {

    @Autowired
    private VolumeRepository repositorio;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Override
    public Page<Volume> executeQueryEncontrarPorNome(int pagina, int totalPorPagina, String nome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Page<Volume> executeQueryEncontrarTodos(int pagina, int totalPorPagina) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, new Sort(Sort.Direction.ASC, "id"));
        return repositorio.findAll(pageRequest);
    }

    public DataPage<Volume> getVolumes(Integer pagina) {
        return findAll(pagina);
    }

    public List<Volume> procurarPorData(String de, String ate) {
        List<Pedido> pedidos = pedidoRepository.encontrarDataPorPeriodo(de, ate);
        List<Volume> retorno = new ArrayList<>();

        for (Pedido pedido : pedidos) {
            for (PedidoMaterial pedidoMaterial : pedido.getPedidoMaterial()) {
                for (Volume volume : pedidoMaterial.getVolumes()) {
                    retorno.add(volume);
                }
            }
        }

        return retorno;
    }
    
    public void salvar(Volume volume) {
        repositorio.save(volume);       
    }
    
    public Volume recuperarPeloId(Long id) {
        return repositorio.findOne(id);
    }

}
