package br.unicesumar.escoladeti.entity;

import static br.unicesumar.escoladeti.util.nanotime.NanotimeUtil.changeStrategy;
import static org.fest.assertions.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import br.unicesumar.escoladeti.util.nanotime.IncrementalNanotimeStrategy;

public class SuperEntitadeTest {

	private EntidadeDummy dummy;

	@Before
	public void setUp() {
		changeStrategy(new IncrementalNanotimeStrategy(15000));
		dummy = new EntidadeDummy("OK");
	}
	
	@Test
	public void testEquals() {
		assertThat(dummy.equals(null)).isFalse();
		assertThat(dummy.equals(dummy)).isTrue();
		assertThat(dummy.equals("OK")).isFalse();
		
		assertThat(dummy.getId()).isNull();

		EntidadeDummy otherDummy = dummy.copy();
		assertThat(otherDummy.getId()).isNull();
		assertThat(otherDummy.value()).isEqualTo("OK");
		
		assertThat(dummy.equals(otherDummy)).isTrue();
		
		otherDummy.updateNanotime();
		assertThat(dummy.equals(otherDummy)).isFalse();
		
		otherDummy.setId(2L);
		assertThat(dummy.equals(otherDummy)).isFalse();
		
		dummy.setId(1L);
		assertThat(dummy.equals(otherDummy)).isFalse();

		dummy.setId(2L);
		assertThat(dummy.equals(otherDummy)).isTrue();
	}
	
	@Test
	public void testToString() {
		assertThat(dummy.toString()).isEqualTo("EntidadeDummy[id = [null], nanotime = 15001, value = 'OK']");

		dummy.setId(2L);
		assertThat(dummy.toString()).isEqualTo("EntidadeDummy[id = 2, nanotime = 15001, value = 'OK']");
	}
}
