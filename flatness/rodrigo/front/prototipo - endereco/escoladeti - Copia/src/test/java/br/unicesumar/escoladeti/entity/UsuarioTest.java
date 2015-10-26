package br.unicesumar.escoladeti.entity;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

import br.unicesumar.escoladeti.entity.Usuario;

public class UsuarioTest {

	@Test
	public void testSetSenha() {
		Usuario usuario = new Usuario();
		
		usuario.setSenha("123mudar");
		assertThat(usuario.getSenha()).isEqualTo("89794b621a313bb59eed0d9f0f4e8205");
		
		usuario.setSenha("RobsonAlecioSomera");
		assertThat(usuario.getSenha()).isEqualTo("49116f5aefa284d8cfd578448f1edce4");
	}
}
