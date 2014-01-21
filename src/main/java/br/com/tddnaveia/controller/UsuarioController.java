package br.com.tddnaveia.controller;

import java.util.List;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.tddnaveia.exception.UsuarioCadastradoException;
import br.com.tddnaveia.model.Usuario;
import br.com.tddnaveia.service.UsuarioService;

@Resource
@Path("")
public class UsuarioController {

	private Result result;
	private UsuarioService service;
//	private Validator validator;
	
	public UsuarioController(Result result, UsuarioService service
//			, Validator validator
			) {
		this.result = result;
		this.service = service;
//		this.validator = validator;
	}
	
	@Get
	@Path("/usuario/carrega")
	public void carregaUm(Usuario usuario) {
		Usuario usuarioRetornado = service.carregaUm(usuario);
		result.include("usuario", usuarioRetornado);
	}
	
	@Post
	@Path("/usuario/cadastra")
	public void cadastraUm(final Usuario usuario) {
//	 	validator.checking(new Validations() {{
//	        that(!usuario.getNome().isEmpty(),  "erro", "Nome nao informado");
//	      }
//	    });
//	    validator.onErrorRedirectTo(this).paginaDeCadastro();
		try {
			service.cadastraUm(usuario);
			result.include("sucesso", "Usuario Cadastrado com Sucesso.");
		} catch (UsuarioCadastradoException e) {
			result.include("erro", "Usuario já existe!");
		}
		result.redirectTo(this).paginaDeCadastro();
	}

	@Get
	@Path("/usuario/lista")
	public void listarPor(Usuario usuario) {
		List<Usuario> usuarios = service.listarPor(usuario);
		if(usuarios!=null){
			result.include("usuarios", usuarios);
		}else{
			result.include("erro", "Usuário não encontrado!");
		}
	}
	
	@Post
	@Path("/usuario/exclui")
	public void excluiUm(Usuario usuario) {
		service.excluiUm(usuario);
		result.include("sucesso", "Usuário excluído com sucesso!");
	}

	@Post
	@Path("/usuario/edita")
	public void editaUm(Usuario usuario) {
		service.editaUm(usuario);
		result.include("sucesso", "Usuário editado com sucesso!");
	}

	@Get
	@Path("/usuario/")
	public void paginaDeCadastro(){
		
	}
	
	@Get
	@Path("/")
	public void index(){
		
	}
}