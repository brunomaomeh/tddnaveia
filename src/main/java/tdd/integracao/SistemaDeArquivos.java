package tdd.integracao;

import java.io.FileWriter;
import java.io.IOException;

import tdd.unidade.Fatorial;

public class SistemaDeArquivos {

	private Fatorial fatorial;

	public SistemaDeArquivos(Fatorial fatorial) {
		this.fatorial = fatorial;
	}
	
	public void salva(FileWriter escritor, Integer valor) throws IOException {
		Integer resultado = fatorial.de(valor);
		escritor.write(resultado);
		escritor.flush();
	}

}
