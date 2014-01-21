package tdd.unidade;

import java.io.File;
import java.io.FileWriter;

public class FatorialSistemaDeArquivos {
	private FileWriter escritor;

	public boolean salva(File arquivo) {
		try {
			escritor = new FileWriter(arquivo);
			escritor.write(new Fatorial().de(15));
			escritor.close();
			return true;
		} catch (Exception e) {
			return false; 
		}
	}
	
}
