package tdd.unidade;

import org.junit.Assert;
import org.junit.Test;

public class FatorialTest {

	@Test
	public void fatorialDe1() {
		Fatorial fatorial = new Fatorial();
		Integer fatorialDe1 = fatorial.de(1);
		Assert.assertEquals(new Integer(1), fatorialDe1);
	}
	
	@Test
	public void fatorialDe2() {
		Fatorial fatorial = new Fatorial();
		Integer fatorialDe2 = fatorial.de(2);
		Assert.assertEquals(new Integer(2), fatorialDe2);
	}
	
	@Test
	public void fatorialDe5() {
		Fatorial fatorial = new Fatorial();
		Integer fatorialDe5 = fatorial.de(5);
		Assert.assertEquals(new Integer(120), fatorialDe5);
	}
	
}
