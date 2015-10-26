package br.unicesumar.time5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.unicesumar.time5.entity.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    public Page<Pessoa> findByIdLike(Pageable pageAble, String id);

}
