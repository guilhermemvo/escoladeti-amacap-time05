package br.unicesumar.escoladeti.entity;

import static br.unicesumar.escoladeti.util.list.ListUtil.toList;
import static java.lang.String.format;
import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import br.unicesumar.escoladeti.entity.Alternativa;
import br.unicesumar.escoladeti.entity.QuestaoObjetiva;

public class QuestaoObjetivaTest {

	private QuestaoObjetiva questao;

	@Before
	public void setUp() {
		questao = new QuestaoObjetiva();
	}
	
	@Test
	public void testQuestaoSemAlternativasNaoFalhaNaVerificacaoConsistencia() {
		consitenciaOk();
	}

	@Test
	public void testNaoPermiteQuestaoSemAlternativaCorreta() {
		Alternativa alternativaCorreta = new Alternativa(questao);
		alternativaCorreta.setOrdem(1);
		
		questao.setAlternativas(toList(alternativaCorreta));
		
		consistenciaFalhaComErro("Dentre as alternativas informadas nenhuma foi indicada como correta.");
		
		Alternativa alternativaIncorreta = new Alternativa(questao);
		alternativaIncorreta.setOrdem(2);
		
		questao.setAlternativas(toList(alternativaCorreta, alternativaIncorreta));
		
		consistenciaFalhaComErro("Dentre as alternativas informadas nenhuma foi indicada como correta.");
		
		alternativaCorreta.setCorreta(true);
		
		consitenciaOk();
	}
	
	@Test
	public void testNaoPermiteQuestaoComAlternativasComOrdemRepetida() {
		Alternativa alternativa1 = new Alternativa(questao);
		alternativa1.setOrdem(1);
		alternativa1.setCorreta(true);
		
		Alternativa alternativa2 = new Alternativa(questao);
		alternativa2.setOrdem(2);
		
		Alternativa alternativa1Errada = new Alternativa(questao);
		alternativa1Errada.setOrdem(1);
		
		questao.setAlternativas(toList(alternativa1, alternativa2, alternativa1Errada));
		
		consistenciaFalhaComErro("A alternativa '(Incorreta) 1 - null' está com a ordem 1 que é a mesma da alternativa anterior.");
		
		alternativa1Errada.setOrdem(3);
		
		consitenciaOk();
	}
	
	@Test
	public void testNaoPermiteQuestaoComAlternativasComFuroOrdem() {
		Alternativa alternativa1 = new Alternativa(questao);
		alternativa1.setOrdem(1);
		alternativa1.setCorreta(true);
		
		Alternativa alternativa2 = new Alternativa(questao);
		alternativa2.setOrdem(2);
		
		Alternativa alternativa3 = new Alternativa(questao);
		alternativa3.setOrdem(4);
		
		questao.setAlternativas(toList(alternativa1, alternativa2, alternativa3));
		
		consistenciaFalhaComErro("A alternativa '(Incorreta) 4 - null' está fora de ordem. Era esperado que ela estivesse na posição 3, mas ela está na posição 4.");
		
		alternativa3.setOrdem(3);
		
		consitenciaOk();
	}

	private void consitenciaOk() {
		try {
			questao.verificarConsistencia();
		} catch (Exception e) {
			e.printStackTrace();
			fail(format("Não deveria haver nenhuma inconsistência, mas... %s", e.getMessage()));
		}
	}
	
	private void consistenciaFalhaComErro(String erroEsperado) {
		try {
			questao.verificarConsistencia();
			fail(format("Não foi disparado o erro esperado '%s'.", erroEsperado));
		} catch (Exception e) {
			e.printStackTrace();
			assertThat(e.getMessage()).isEqualTo(erroEsperado);
		}
	}
}
