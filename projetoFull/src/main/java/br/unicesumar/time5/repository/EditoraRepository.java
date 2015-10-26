package br.unicesumar.time5.repository;

import br.unicesumar.time5.entity.Editora;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EditoraRepository extends JpaRepository<Editora, Long> {

    public Page<Editora> findByRazaoLike(Pageable pageAble, String razao);
    public Editora findByCnpj(String cnpj);
}
