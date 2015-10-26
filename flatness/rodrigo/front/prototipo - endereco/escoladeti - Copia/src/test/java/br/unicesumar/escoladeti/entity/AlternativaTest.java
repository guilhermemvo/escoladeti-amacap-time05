package br.unicesumar.escoladeti.entity;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class AlternativaTest {

	@Test
	public void testCompareTo() {
		Alternativa alternativa5 = new Alternativa();
		assertThat(alternativa5.compareTo(null)).isEqualTo(-1);
		
		Alternativa alternativa2 = new Alternativa();
		assertThat(alternativa5.compareTo(alternativa2)).isEqualTo(0);
		
		alternativa5.setOrdem(5);
		assertThat(alternativa5.compareTo(alternativa2)).isEqualTo(-1);
		assertThat(alternativa2.compareTo(alternativa5)).isEqualTo(1);
		
		alternativa2.setOrdem(2);
		assertThat(alternativa5.compareTo(alternativa2)).isEqualTo(1);
		assertThat(alternativa2.compareTo(alternativa5)).isEqualTo(-1);
		
		alternativa2.setOrdem(5);
		assertThat(alternativa5.compareTo(alternativa2)).isEqualTo(0);
	}
	
}
