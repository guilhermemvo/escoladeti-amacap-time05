package br.unicesumar.time5.repository;

import br.unicesumar.time5.entity.Oficio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OficioRepository extends JpaRepository<Oficio, Long> {

}
