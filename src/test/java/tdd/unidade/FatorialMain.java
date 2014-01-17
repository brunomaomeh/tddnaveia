package tdd.unidade;


public class FatorialMain {
	public static void main(String[] args) {
		Fatorial fatorial = new Fatorial();
		Integer fatorialDe1 = fatorial.de(1);
		if (new Integer(1).equals(fatorialDe1)) {
			System.out.println("O Fatorial de 1 está correto: " + fatorialDe1);
		} else {
			System.out.println("O Fatorial de 1 está errado: " + fatorialDe1);
		}
	}
}
