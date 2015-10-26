package br.unicesumar.time5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.unicesumar.time5.entity.Email;

public interface EmailRepository extends JpaRepository<Email, Long> {

}
