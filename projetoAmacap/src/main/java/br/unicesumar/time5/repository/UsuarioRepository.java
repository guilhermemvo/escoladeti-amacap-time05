package br.unicesumar.time5.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.unicesumar.time5.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByLogin(String login);

    List<Usuario> findByLoginContainingOrderByLoginAsc(String login);

    Page<Usuario> findByLoginContainingAndAtivo(String login, boolean ativo, Pageable pageable);

}
