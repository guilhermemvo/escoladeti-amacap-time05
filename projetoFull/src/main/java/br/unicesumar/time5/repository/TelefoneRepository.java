package br.unicesumar.time5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.unicesumar.time5.entity.Telefone;

public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
}
