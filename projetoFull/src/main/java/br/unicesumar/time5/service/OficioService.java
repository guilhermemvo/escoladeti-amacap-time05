package br.unicesumar.time5.service;

import br.unicesumar.time5.entity.Oficio;
import br.unicesumar.time5.entity.Pedido;
import br.unicesumar.time5.repository.NotaRepository;
import br.unicesumar.time5.repository.OficioRepository;
import br.unicesumar.time5.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OficioService extends AbstractService<Oficio> {

    @Autowired
    private OficioRepository repositorio;
    @Autowired
    private PedidoRepository pedidoRepo;

    public void salvar(Oficio oficio) {
        repositorio.save(oficio);
        Pedido p1 = pedidoRepo.findOne(oficio.getPedido());
        p1.setStatus("AGUARDANDO MATERIAIS");
        pedidoRepo.save(p1);
    }

    @Override
    public Page<Oficio> executeQueryEncontrarPorNome(int pagina, int totalPorPagina, String nome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Page<Oficio> executeQueryEncontrarTodos(int pagina, int totalPorPagina) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
