package br.unicesumar.time5.repository;

import br.unicesumar.time5.entity.Solicitante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SolicitanteRepository extends JpaRepository<Solicitante, Long>{
    
    public Page<Solicitante> findByNomeLike(Pageable pageAble, String nome);
    public Solicitante findByCpf(String cpf);
    
}
