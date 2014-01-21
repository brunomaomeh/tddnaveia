package tdd.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.google.common.collect.Lists;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.util.test.MockResult;
import tdd.exception.UsuarioCadastradoException;
import tdd.model.Usuario;
import tdd.service.UsuarioService;

public class UsuarioControllerTest {

	private UsuarioController controller;
	private Result result = new MockResult();
	
	@Mock
	private UsuarioService service;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		controller = new UsuarioController(result, service);
	}
	
	@Test
	public void deveSerPossivelCarregarUmUsuario(){
		when(service.carregaUm(any(Usuario.class))).thenReturn(usuarioJoao());
		controller.carregaUm(usuarioJoao());
		assertEquals(usuarioJoao(), result.included().get("usuario"));
	}

	private Usuario usuarioJoao() {
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		usuario.setNome("João");
		usuario.setPassword("12345");
		usuario.setLogin("joao");
		usuario.setEmail("joao@tddnaveia.com");
		return usuario;
	}
	
	@Test
	public void deveSerPossivelCadastrarUmUsuario(){
		controller.cadastraUm(usuarioJoao());
		verify(service).cadastraUm(any(Usuario.class));
		assertEquals("Usuario Cadastrado com Sucesso.", result.included().get("sucesso"));
	}
	
	@Test
	public void lancaExcecao_QuandoUsuarioJaCadastrado(){
		doThrow(new UsuarioCadastradoException("Usuario já existe!")).when(service).cadastraUm(any(Usuario.class));
		controller.cadastraUm(any(Usuario.class));
		assertEquals("Usuario já existe!", result.included().get("erro"));
	}
	
	@Test
	public void deveListarOsUsuarios(){
		when(service.listarPor(any(Usuario.class))).thenReturn(Lists.newArrayList(usuarioJoao()));
		controller.listarPor(any(Usuario.class));
		assertEquals(Lists.newArrayList(usuarioJoao()), result.included().get("usuarios"));
	}
	
	@Test
	public void deveRetornarErro_QuandoUsuarioNaoEncontrado(){
		when(service.listarPor(any(Usuario.class))).thenReturn(null);
		controller.listarPor(any(Usuario.class));
		assertEquals("Usuário não encontrado!", result.included().get("erro"));
	}
	
	@Test
	public void deveSerPossivelExcluirUmUsuario(){
		controller.excluiUm(any(Usuario.class));
		verify(service).excuiUm(any(Usuario.class));
		assertEquals("Usuário excluído com sucesso!", result.included().get("sucesso"));
	}

	@Test
	public void deveSerPossivelEditarUmUsuario(){
		controller.editaUm(any(Usuario.class));
		verify(service).editaUm(any(Usuario.class));
		assertEquals("Usuário editado com sucesso!", result.included().get("sucesso"));
	}
	
}
