package br.unicesumar.time5.repository;

import br.unicesumar.time5.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    public Page<Funcionario> findByNomeLike(Pageable pageAble, String nome);
    public Funcionario findByCpf(String cpf);
}
