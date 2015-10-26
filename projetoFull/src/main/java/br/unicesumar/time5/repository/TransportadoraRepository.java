package br.unicesumar.time5.repository;

import br.unicesumar.time5.entity.Transportadora;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransportadoraRepository extends JpaRepository<Transportadora, Long> {

    public Page<Transportadora> findByRazaoLike(Pageable pageAble, String razao);
    public Transportadora findByCnpj(String cnpj);
}
