package br.unicesumar.time5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.unicesumar.time5.entity.Usuario;
import br.unicesumar.time5.entity.HistoricoMaterial;

public interface HistoricoMaterialRepository extends JpaRepository<HistoricoMaterial, Long> {


}
