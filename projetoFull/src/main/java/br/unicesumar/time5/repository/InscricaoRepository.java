package br.unicesumar.time5.repository;

import br.unicesumar.time5.entity.Evento;
import br.unicesumar.time5.entity.Inscricao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InscricaoRepository extends JpaRepository<Inscricao, Long> {

    public List<Inscricao> findByEvento(Evento evento);
}
