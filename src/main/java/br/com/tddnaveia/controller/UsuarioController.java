package br.com.tddnaveia.controller;

import java.util.List;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.tddnaveia.exception.UsuarioCadastradoException;
import br.com.tddnaveia.model.Usuario;
import br.com.tddnaveia.service.UsuarioService;

public class UsuarioController {

	private Result result;
	private UsuarioService service;
	private Validator validator;
	
	public UsuarioController(Result result, UsuarioService service, Validator validator) {
		this.result = result;
		this.service = service;
		this.validator = validator;
	}
	
	public void carregaUm(Usuario usuario) {
		Usuario usuarioRetornado = service.carregaUm(usuario);
		result.include("usuario", usuarioRetornado);
	}

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
	}

	public void listarPor(Usuario usuario) {
		List<Usuario> usuarios = service.listarPor(usuario);
		if(usuarios!=null){
			result.include("usuarios", usuarios);
		}else{
			result.include("erro", "Usuário não encontrado!");
		}
	}

	public void excluiUm(Usuario usuario) {
		service.excluiUm(usuario);
		result.include("sucesso", "Usuário excluído com sucesso!");
	}

	public void editaUm(Usuario usuario) {
		service.editaUm(usuario);
		result.include("sucesso", "Usuário editado com sucesso!");
	}

	
	public void paginaDeCadastro(){
		
	}
	
}