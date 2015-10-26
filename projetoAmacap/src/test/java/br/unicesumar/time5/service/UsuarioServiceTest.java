/*
package br.unicesumar.time5.service;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import br.unicesumar.time5.entity.Usuario;
import br.unicesumar.time5.repository.UsuarioRepository;
import br.unicesumar.time5.service.UsuarioService;

public class UsuarioServiceTest {

    private UsuarioService usuarioServiceSpy;
    private UsuarioRepository usuarioRepositoryMock;

    @Before
    public void setUp() {
        usuarioServiceSpy = spy(new UsuarioService());
        usuarioRepositoryMock = mock(UsuarioRepository.class);

        doReturn(usuarioRepositoryMock).when(usuarioServiceSpy).getUsuarioRepository();
    }

    @Test
    public void testInicializaUsuarioAdminComUsuarioInexistenteExistente() {
        when(usuarioRepositoryMock.findByLogin("admin")).thenReturn(mock(Usuario.class));

        usuarioServiceSpy.inicializarUsuarioAdmin();

        verify(usuarioRepositoryMock).findByLogin("admin");
        verify(usuarioRepositoryMock, never()).save(any(Usuario.class));
    }

}
*/