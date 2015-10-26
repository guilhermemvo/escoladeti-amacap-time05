package br.unicesumar.time5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.unicesumar.time5.entity.PessoaFisica;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, Long> {

    public Page<PessoaFisica> findByIdLike(Pageable pageAble, String id);

    public PessoaFisica findByCpf(String cpf);

    public List<PessoaFisica> findByCpfLike(String cpf);

    public List<PessoaFisica> findByRgLike(String value);
    
    public List<PessoaFisica> findByNomeLike(String value);
    
    public List<PessoaFisica> findByCpfLikeOrNomeLikeIgnoreCaseOrderByNomeAsc(String Cpf, String Nome);
}
