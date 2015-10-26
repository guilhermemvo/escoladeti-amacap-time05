package br.unicesumar.time5.repository;

import br.unicesumar.time5.entity.Escola;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EscolaRepository extends JpaRepository<Escola, Long> {

    public Page<Escola> findByRazaoLike(Pageable pageAble, String razao);

    public Escola findByCnpj(String cnpj);

    public List<Escola> findByRazaoLike(String razao);

    public List<Escola> findByFantasiaLike(String fantasia);

    public List<Escola> findByCnpjLike(String cnpj);

    public List<Escola> findByCnpjLikeOrRazaoLikeIgnoreCaseOrFantasiaLikeIgnoreCaseOrderByFantasiaAsc(String Cpf, String Razao, String Fantasia);
}
