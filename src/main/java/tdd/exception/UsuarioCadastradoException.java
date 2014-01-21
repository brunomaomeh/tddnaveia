package tdd.exception;

@SuppressWarnings("serial")
public class UsuarioCadastradoException extends RuntimeException {

	public UsuarioCadastradoException() {
	}
	
	public UsuarioCadastradoException(String mensagem){
		super(mensagem);
	}
	
}
