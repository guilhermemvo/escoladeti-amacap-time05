package br.unicesumar.time5.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.unicesumar.time5.entity.AssociadoPJ;
import br.unicesumar.time5.entity.AssociadoPJMovimentacao;

public interface AssociadoPJMovimentacaoRepository extends JpaRepository<AssociadoPJMovimentacao,Long> {

	public List<AssociadoPJMovimentacao> findByAssociadoPJ(AssociadoPJ associadoPJ);
	
	public List<AssociadoPJMovimentacao> findByAssociadoPJ(Long id);
}
