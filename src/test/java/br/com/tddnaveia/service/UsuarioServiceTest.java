package br.com.tddnaveia.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.persistence.EntityExistsException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.tddnaveia.model.Usuario;
import br.com.tddnaveia.service.UsuarioService;

import com.google.common.collect.Lists;

import dbunit.DbUnitManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:applicationContext-test.xml"})
@Transactional
public class UsuarioServiceTest {
	
	private static final String USUARIO = "src/test/java/datasets/Usuario.xml";
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private DbUnitManager dbUnitManager;
	
	@Before
	public void setUp(){
		dbUnitManager.cleanAndInsert(USUARIO);
	}
	
	@Test
	public void deveCarregarUsuarioJoao(){
		Usuario usuario = usuarioService.carregaUm(usuarioJoao());
		assertEquals(usuarioJoao(), usuario);
	}
	
	@Test
	public void deveCadastrarUmUsuario(){
		usuarioService.cadastraUm(usuarioJose());
		assertEquals(usuarioJose(), usuarioService.carregaUm(usuarioJose()));
	}
	
	@Test(expected=EntityExistsException.class)
	public void lancaExcecao_QuandoUsuarioJaCadastrado(){
		usuarioService.cadastraUm(usuarioMaria());
		usuarioService.cadastraUm(usuarioMaria());
	}
	
	private Usuario usuarioJoao(){
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		usuario.setLogin("joao");
		usuario.setNome("João");
		usuario.setEmail("joao@tddnaveia.com");
		usuario.setPassword("12345");
		return usuario;
	}
	
	private Usuario usuarioJose(){
		Usuario usuario = new Usuario();
		usuario.setId(2L);
		usuario.setLogin("jose");
		usuario.setNome("João");
		usuario.setEmail("jose@tddnaveia.com");
		usuario.setPassword("54321");
		return usuario;
	}
	
	private Usuario usuarioMaria(){
		Usuario usuario = new Usuario();
		usuario.setId(3L);
		usuario.setLogin("maria");
		usuario.setNome("Maria");
		usuario.setEmail("maria@tddnaveia.com");
		usuario.setPassword("33333");
		return usuario;
	}
	
	private Usuario usuarioJoaquim(){
		Usuario usuario = new Usuario();
		usuario.setId(4L);
		usuario.setLogin("joaquim");
		usuario.setNome("Joaquim");
		usuario.setEmail("joaquim@tddnaveia.com");
		usuario.setPassword("44444");
		return usuario;
	}
	
	@Test
	public void deveListarUsuariosPorLogin(){
		Usuario usuarioTeste = new Usuario();
		usuarioTeste.setLogin("a");
		List<Usuario> usuarios = usuarioService.listarPor(usuarioTeste);
		assertEquals(Lists.newArrayList(usuarioJoao(), usuarioMaria(), usuarioJoaquim()), usuarios);
	}
	
	@Test
	public void deveDeletarUmUsuario(){
		assertEquals(usuarioMaria(), usuarioService.carregaUm(usuarioMaria()));
		usuarioService.excluiUm(usuarioMaria());
		assertNull(usuarioService.carregaUm(usuarioMaria()));
	}
	
	@Test
	public void deveEditarUmUsuario(){
		assertEquals(usuarioMaria(), usuarioService.carregaUm(usuarioMaria()));
		Usuario mariaEditada = usuarioMaria();
		mariaEditada.setEmail("mariazinha@tddnaveia.com");
		usuarioService.editaUm(mariaEditada);
		assertEquals(mariaEditada, usuarioService.carregaUm(usuarioMaria()));
	}
}
