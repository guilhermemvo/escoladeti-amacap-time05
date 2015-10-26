/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unicesumar.time5.service;

import br.unicesumar.time5.entity.Endereco;
import br.unicesumar.time5.repository.EnderecoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author felipesabadinifacina
 */
@Service
@Transactional
public class EnderecoService extends AbstractService<Endereco>{
    private static final Logger logger = LoggerFactory.getLogger(EnderecoService.class);
        
    EnderecoRepository enderecoRepo;
        
    public void salvar(Endereco endereco){
        this.enderecoRepo.save(endereco);
    }
    
    public Endereco recuperarPeloId(Long id){
        return this.enderecoRepo.findOne(id);
        
    }
    
    public void remover(Endereco endereco) {
        enderecoRepo.delete(endereco);
    }
    
    @Override
    public Page<Endereco> executeQueryEncontrarPorNome(int pagina, int totalPorPagina, String nome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Page<Endereco> executeQueryEncontrarTodos(int pagina, int totalPorPagina) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
