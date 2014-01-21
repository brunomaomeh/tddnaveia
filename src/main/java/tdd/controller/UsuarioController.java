package tdd.controller;

import java.util.List;

import br.com.caelum.vraptor.Result;
import tdd.exception.UsuarioCadastradoException;
import tdd.model.Usuario;
import tdd.service.UsuarioService;

public class UsuarioController {

	private Result result;
	private UsuarioService service;
	
	public UsuarioController(Result result, UsuarioService service) {
		this.result = result;
		this.service = service;
	}
	
	public void carregaUm(Usuario usuario) {
		Usuario usuarioRetornado = service.carregaUm(usuario);
		result.include("usuario", usuarioRetornado);
	}

	public void cadastraUm(Usuario usuario) {
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
		service.excuiUm(usuario);
		result.include("sucesso", "Usuário excluído com sucesso!");
	}

	public void editaUm(Usuario usuario) {
		service.editaUm(usuario);
		result.include("sucesso", "Usuário editado com sucesso!");
	}

}
