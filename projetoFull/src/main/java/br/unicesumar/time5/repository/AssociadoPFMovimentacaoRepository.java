package br.unicesumar.time5.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.unicesumar.time5.entity.AssociadoPF;
import br.unicesumar.time5.entity.AssociadoPFMovimentacao;

public interface AssociadoPFMovimentacaoRepository extends JpaRepository<AssociadoPFMovimentacao,Long> {

	public List<AssociadoPFMovimentacao> findByAssociadoPF(AssociadoPF associadoPF);
	
	public List<AssociadoPFMovimentacao> findByAssociadoPF(Long id);
}
