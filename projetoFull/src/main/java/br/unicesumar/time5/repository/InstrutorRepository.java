package br.unicesumar.time5.repository;

import br.unicesumar.time5.entity.Instrutor;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InstrutorRepository extends JpaRepository<Instrutor, Long> {

    public Page<Instrutor> findByNomeLike(Pageable pageAble, String nome);
    public Instrutor findByCpf(String cpf);
}
