package br.unicesumar.time5.repository;

import br.unicesumar.time5.entity.PerfilAcesso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioPerfilRepository extends JpaRepository<PerfilAcesso, Long> {

}
