package br.com.tddnaveia.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.tddnaveia.controller.UsuarioController;
import br.com.tddnaveia.exception.UsuarioCadastradoException;
import br.com.tddnaveia.model.Usuario;
import br.com.tddnaveia.service.UsuarioService;

import com.google.common.collect.Lists;

public class UsuarioControllerTest {

	private UsuarioController controller;
	private Result result = new MockResult();
//	private MockValidator validator = new MockValidator();
	@Mock
	private UsuarioService service;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		controller = new UsuarioController(result, service
//												 , validator
												 );
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
	
//	@Test
//	public void quandoNomeNaoInformado_ocorreErroDeValidacao(){
//		Usuario usuarioSemNome = usuarioJoao();
//		usuarioSemNome.setNome("");
//		validaCamposObrigatorios(usuarioSemNome, "Nome não informado");
//	}
//	
//	private void validaCamposObrigatorios(
//			Usuario usuario, String categoria) {
//		try {
//			controller.cadastraUm(usuario);
//			fail("Deveria ter ocorrido um erro de validacao!");
//		} catch (ValidationException exception) {
//			List<Message> errors = exception.getErrors();
//			assertFalse(errors.isEmpty());
//			assertEquals(1, errors.size());
//			Message validationError = errors.get(0);
//			assertEquals("erro", validationError.getCategory());
//			assertEquals(categoria, validationError.getMessage());
//		}
//	}
	
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
		verify(service).excluiUm(any(Usuario.class));
		assertEquals("Usuário excluído com sucesso!", result.included().get("sucesso"));
	}

	@Test
	public void deveSerPossivelEditarUmUsuario(){
		controller.editaUm(any(Usuario.class));
		verify(service).editaUm(any(Usuario.class));
		assertEquals("Usuário editado com sucesso!", result.included().get("sucesso"));
	}
	
	@Test
	public void deveCarregarPaginaDeEdicao(){
		when(service.carregaUm(any(Usuario.class))).thenReturn(usuarioJoao());
		controller.paginaDeEdicao(any(Usuario.class));
		assertEquals(usuarioJoao(), result.included().get("usuario"));
	}
	
}
