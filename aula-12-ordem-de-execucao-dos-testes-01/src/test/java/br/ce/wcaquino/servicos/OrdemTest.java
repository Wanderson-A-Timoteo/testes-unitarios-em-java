package br.ce.wcaquino.servicos;

import org.junit.Assert;
import org.junit.Test;

public class OrdemTest {
	
	public static int contador = 0;
	/* O JUnit não garante a ordem de execução dos teste
	 * Neste caso ocorre uma falha no teste devido o JUnit executar
	 * primeiro o teste verifica() para depois executar o inicia()
	 * e como o teste verifica() é dependente do resultado de inicia()
	 * ocorre a falha. 
	 */
	@Test
	public void inicia() {
		contador = 1;	
	}
	
	@Test
	public void verifica() {
		Assert.assertEquals(1, contador);
	}	
}
