package br.unicesumar.time5.repository;


import br.unicesumar.time5.entity.Material;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialRepository extends JpaRepository<Material, Serializable>{
    public List<Material> findByNomeLike(String nome);
}
