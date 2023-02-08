package br.ce.wcaquino.servicos;

import org.junit.Assert;
import org.junit.Test;

public class OrdemTest {
	
	public static int contador = 0;

	// Removido anotação teste
	public void inicia() {
		contador = 1;	
	}
	
	// Removido anotação teste
	public void verifica() {
		Assert.assertEquals(1, contador);
	}	
	
	/*
	 * Para garantir que o JUnit execute em ordem podemos adicionar 
	 * os metodos em um unico teste.
	 * Porém, caso tenhamos varios metodos dentro deste unico teste
	 * e um deles ocorrer falha, ficará muito dificil localizar qual 
	 * dos metodos ocorreu a falha
	 */
	@Test
	public void testGeral() {
		inicia();
		verifica();
	}

}
