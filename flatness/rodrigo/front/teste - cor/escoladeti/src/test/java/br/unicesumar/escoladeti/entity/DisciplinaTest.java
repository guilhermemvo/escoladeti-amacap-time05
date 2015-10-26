package br.unicesumar.escoladeti.entity;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

import br.unicesumar.escoladeti.entity.Disciplina;

public class DisciplinaTest {

	@Test
	public void testCompareTo() {
		Disciplina matematica = new Disciplina();
		assertThat(matematica.compareTo(null)).isEqualTo(-1);
		
		Disciplina aritimetica = new Disciplina();
		assertThat(matematica.compareTo(aritimetica)).isEqualTo(0);
		
		matematica.setNome("Matemática");
		assertThat(matematica.compareTo(aritimetica)).isEqualTo(-1);
		assertThat(aritimetica.compareTo(matematica)).isEqualTo(1);
		
		aritimetica.setNome("Aritimética");
		assertThat(matematica.compareTo(aritimetica)).isEqualTo(12);
		assertThat(aritimetica.compareTo(matematica)).isEqualTo(-12);
		
		aritimetica.setNome("Matemática");
		assertThat(matematica.compareTo(aritimetica)).isEqualTo(0);
	}
	
	
}
