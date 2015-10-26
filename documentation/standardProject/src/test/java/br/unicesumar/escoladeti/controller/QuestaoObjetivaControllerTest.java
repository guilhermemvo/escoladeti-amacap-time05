package br.unicesumar.escoladeti.controller;

import static br.unicesumar.escoladeti.util.list.ListUtil.toList;
import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.intThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.matchers.GreaterOrEqual;

import br.unicesumar.escoladeti.entity.QuestaoObjetiva;
import br.unicesumar.escoladeti.service.QuestaoObjetivaService;

public class QuestaoObjetivaControllerTest {

	private QuestaoObjetivaService questaoObjetivaServiceMock;
	private QuestaoObjetivaController questaoObjetivaControllerSpy;

	@Before
	public void setUp() {
		questaoObjetivaServiceMock = mock(QuestaoObjetivaService.class);
		questaoObjetivaControllerSpy = spy(new QuestaoObjetivaController());
		
		doReturn(questaoObjetivaServiceMock).when(questaoObjetivaControllerSpy).getService();
	}
	
	@Test
	public void testListar() {
		DataPage<QuestaoObjetiva> dataPage1 = mock(DataPage.class);
		when(dataPage1.getList()).thenReturn(toList(new QuestaoObjetiva()));
		
		when(questaoObjetivaServiceMock.getQuestoes(1)).thenReturn(dataPage1);

		DataPage<QuestaoObjetiva> dataPageVazia = mock(DataPage.class);
		when(dataPageVazia.getList()).thenReturn(new ArrayList<QuestaoObjetiva>());
		
		when(questaoObjetivaServiceMock.getQuestoes(intThat(new GreaterOrEqual<>(2)))).thenReturn(dataPageVazia);
		
		DataPage<QuestaoObjetiva> dataPage1Retornada = questaoObjetivaControllerSpy.listar(1);
		assertThat(dataPage1Retornada.getList()).hasSize(1);
		verify(questaoObjetivaServiceMock).getQuestoes(1);
		
		DataPage<QuestaoObjetiva> dataPage2Retornada = questaoObjetivaControllerSpy.listar(2);
		assertThat(dataPage2Retornada.getList()).isEmpty();
		verify(questaoObjetivaServiceMock).getQuestoes(2);
		
		DataPage<QuestaoObjetiva> dataPage3Retornada = questaoObjetivaControllerSpy.listar(3);
		assertThat(dataPage3Retornada.getList()).isEmpty();
		verify(questaoObjetivaServiceMock).getQuestoes(3);
	}
	
	@Test
	public void testRemover() {
		QuestaoObjetiva questaoObjetivaMock = mock(QuestaoObjetiva.class);
		
		assertThat(questaoObjetivaControllerSpy.remover(questaoObjetivaMock)).isEqualTo("OK");
		verify(questaoObjetivaServiceMock).remover(questaoObjetivaMock);
	}
	
	@Test
	public void testSalvar() {
		QuestaoObjetiva questaoObjetivaMock = mock(QuestaoObjetiva.class);
		
		assertThat(questaoObjetivaControllerSpy.salvar(questaoObjetivaMock)).isEqualTo("OK");
		verify(questaoObjetivaServiceMock).salvar(questaoObjetivaMock);
	}
	
	@Test
	public void testCarregar() {
		QuestaoObjetiva questaoObjetivaMock = mock(QuestaoObjetiva.class);
		
		when(questaoObjetivaServiceMock.carregar(10L)).thenReturn(questaoObjetivaMock);
		
		assertThat(questaoObjetivaControllerSpy.carregar(2L)).isNull();
		assertThat(questaoObjetivaControllerSpy.carregar(4L)).isNull();
		assertThat(questaoObjetivaControllerSpy.carregar(6L)).isNull();
		assertThat(questaoObjetivaControllerSpy.carregar(8L)).isNull();

		assertThat(questaoObjetivaControllerSpy.carregar(10L)).isEqualTo(questaoObjetivaMock);
		
		verify(questaoObjetivaServiceMock, times(5)).carregar(anyLong());
	}
}
