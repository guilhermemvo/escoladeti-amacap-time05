package br.unicesumar.time5.service;

import br.unicesumar.time5.controller.DataPage;
import br.unicesumar.time5.entity.Pedido;
import br.unicesumar.time5.repository.PedidoRepository;
import br.unicesumar.time5.types.Status;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static org.springframework.data.domain.Sort.Direction.ASC;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PedidoService extends AbstractService<Pedido> {

    @Autowired
    private PedidoRepository repo;

    public void salvar(Pedido pedido) {
        repo.save(pedido);
    }

    public List<Pedido> todos() {
        return repo.findAll(new Sort(new Sort.Order(ASC, "id")));
    }

    public DataPage<Pedido> getPedidos(Integer pagina) {
        return findAll(pagina);
    }

    public void remover(Long pedido) {
        repo.delete(pedido);
    }

    public Pedido recuperarPedidoPeloId(Long id) {
        return repo.findOne(id);
    }
    
    public String cancelar(Long id) {
    	Pedido pedidoCancelar = repo.findOne(id);
    	if(pedidoCancelar.getStatus().equals("CANCELADO")){
    		return "CANCELADO";
    	}else{
    			pedidoCancelar.setStatus(Status.CANCELADO.getDescricao());
    			repo.save(pedidoCancelar);
    	}
    	return "OK";
    }

    @Override
    public Page<Pedido> executeQueryEncontrarPorNome(int pagina, int totalPorPagina, String nome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Page<Pedido> executeQueryEncontrarTodos(int pagina, int totalPorPagina) {
        final PageRequest pageRequest = new PageRequest(pagina, totalPorPagina, new Sort(Sort.Direction.ASC, "id"));
        return repo.findAll(pageRequest);
    }

    public List<Pedido> procurarPorData(String de, String ate) {
        return repo.encontrarDataPorPeriodo(de, ate);
    }

    public Integer getCountPendentes() {
        return repo.getCountPendentes();
    }

    public void mudarStatusParaProducao(Long id) {
        Pedido p1 = repo.findOne(id);
        p1.setStatus("EM PRODUCAO");
        repo.save(p1);

    }
}
