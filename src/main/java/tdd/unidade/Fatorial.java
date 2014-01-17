package tdd.unidade;

public class Fatorial {

	public Integer de(Integer valor) {
		int fatorial = valor;
		for (int i = (valor - 1); i > 1; i--) {
			fatorial = fatorial * i;
		}

		return fatorial;
	}

}
