package br.unicesumar.escoladeti.entity;

import static br.unicesumar.escoladeti.util.list.ListUtil.toList;
import static java.lang.String.format;
import static java.util.Collections.sort;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import br.unicesumar.escoladeti.exceptions.InconsistenciaException;

import java.util.Collections;

@Entity
public class QuestaoObjetiva extends Questao {

	@OneToMany(mappedBy = "questao")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Alternativa> alternativas;
	
	public QuestaoObjetiva() {
		super(TipoQuestao.OBJETIVA);
	}
	
	@Override
	public void avoidLazyFail() {
		super.avoidLazyFail();
		getAlternativas().size();
	}

	public List<Alternativa> getAlternativas() {
		if (alternativas == null)
			alternativas = toList();
                
                Collections.sort(alternativas);
		
		return alternativas;
	}

	public void setAlternativas(List<Alternativa> alternativas) {
		this.alternativas = alternativas;
	}

	@Override
	protected void executarVerificacaoDeConsistenciaEspecializada() {
		super.executarVerificacaoDeConsistenciaEspecializada();
		if (getAlternativas().isEmpty())
			return;
		
		if (!existePeloMenosUmaAlternativaCorreta())
			throw new InconsistenciaException("Dentre as alternativas informadas nenhuma foi indicada como correta.");
		
		verificaOrdemDasAlternativas();
	}
	
	private boolean existePeloMenosUmaAlternativaCorreta() {
		for (Alternativa alternativa : getAlternativas()) {
			if (alternativa.getCorreta())
				return true;
		}
		return false;
	}

	private void verificaOrdemDasAlternativas() {
		int ordemEsperada = 1;
		int ordemAnterior = 0;
		
		sort(getAlternativas());
		
		for (Alternativa alternativa : getAlternativas()) {
			int ordemAlternativa = alternativa.getOrdem().intValue();
			if (ordemAlternativa != ordemEsperada) {
				if (ordemAlternativa == ordemAnterior)
					throw new InconsistenciaException(format("A alternativa '%s' está com a ordem %d que é a mesma da alternativa anterior.", alternativa, ordemAlternativa));
				
				throw new InconsistenciaException(format("A alternativa '%s' está fora de ordem. Era esperado que ela estivesse na posição %d, mas ela está na posição %d.", 
						alternativa, ordemEsperada, ordemAlternativa));
			}
			
			ordemAnterior = ordemEsperada;
			ordemEsperada++;
		}
	}
	
}
