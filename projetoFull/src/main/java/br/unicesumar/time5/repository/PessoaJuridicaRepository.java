package br.unicesumar.time5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.unicesumar.time5.entity.PessoaJuridica;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, Long> {

    public Page<PessoaJuridica> findByIdLike(Pageable pageAble, String id);

    public PessoaJuridica findByCnpj(String cpf);
}
