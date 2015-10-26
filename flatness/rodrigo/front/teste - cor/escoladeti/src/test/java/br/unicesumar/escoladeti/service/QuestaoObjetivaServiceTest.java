package br.unicesumar.escoladeti.service;

import static br.unicesumar.escoladeti.controller.DataPage.pageRequestForAsc;
import static br.unicesumar.escoladeti.util.list.ListUtil.toList;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.unicesumar.escoladeti.entity.Alternativa;
import br.unicesumar.escoladeti.entity.QuestaoObjetiva;
import br.unicesumar.escoladeti.repository.AlternativaRepository;
import br.unicesumar.escoladeti.repository.QuestaoObjetivaRepository;

public class QuestaoObjetivaServiceTest {

	private QuestaoObjetivaRepository questaoObjetivaRepositoryMock;
	private AlternativaRepository alternativaRepositoryMock;
	private QuestaoObjetivaService questaoObjetivaServiceSpy;

	@Before
	public void setUp() {
		questaoObjetivaRepositoryMock = mock(QuestaoObjetivaRepository.class);
		
		alternativaRepositoryMock = mock(AlternativaRepository.class);
		
		questaoObjetivaServiceSpy = spy(new QuestaoObjetivaService());
		
		doReturn(questaoObjetivaRepositoryMock).when(questaoObjetivaServiceSpy).getQuestaoRepository();
		doReturn(alternativaRepositoryMock).when(questaoObjetivaServiceSpy).getAlternativaRepository();
	}
	
	@Test
	public void testGetQuestoes() {
		Page<QuestaoObjetiva> paginaResultadoMock = mock(Page.class);
		when(paginaResultadoMock.getContent()).thenReturn(new ArrayList<QuestaoObjetiva>());
		when(paginaResultadoMock.getNumber()).thenReturn(0);
		when(paginaResultadoMock.getSize()).thenReturn(1);
		
		when(questaoObjetivaRepositoryMock.findAll(any(Pageable.class))).thenReturn(paginaResultadoMock);
		
		questaoObjetivaServiceSpy.getQuestoes(1);
		
		verify(questaoObjetivaRepositoryMock).findAll(eq(pageRequestForAsc(1, "enunciado")));
	}
	
	@Test
	public void testSalvar() {
		QuestaoObjetiva questaoSpy = spy(new QuestaoObjetiva());
		
		Alternativa alternativa1 = new Alternativa(questaoSpy);
		alternativa1.setOrdem(1);
		alternativa1.setCorreta(true);
		
		Alternativa alternativa2Descartada = new Alternativa(questaoSpy);
		alternativa2Descartada.setOrdem(2);
		
		Alternativa alternativa2Nova = new Alternativa(questaoSpy);
		alternativa2Nova.setOrdem(2);
		
		questaoSpy.setAlternativas(toList(alternativa1, alternativa2Nova));

		when(questaoObjetivaRepositoryMock.save(questaoSpy)).thenReturn(questaoSpy);
		when(alternativaRepositoryMock.findAllByQuestao(questaoSpy)).thenReturn(toList(alternativa2Descartada));
		
		questaoObjetivaServiceSpy.salvar(questaoSpy);
		
		verify(questaoSpy).verificarConsistencia();
		verify(questaoObjetivaRepositoryMock).save(questaoSpy);
		
		verify(alternativaRepositoryMock).findAllByQuestao(questaoSpy);
		
		verify(alternativaRepositoryMock).save(alternativa1);
		verify(alternativaRepositoryMock).save(alternativa2Nova);
		
		verify(alternativaRepositoryMock).delete(alternativa2Descartada);
	}
	
	@Test
	public void testCarregar() {
		questaoObjetivaServiceSpy.carregar(10L);
		verify(questaoObjetivaRepositoryMock).findOne(10L);
	}
}
