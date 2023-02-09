package br.ce.wcaquino.servicos;

import org.junit.Test;
import org.junit.Assert;

public class CalculadoraTest {

	@Test
	public void deveSomarDoisValores() {
		// Cenário
		int a = 5;
		int b = 3;
		Calculadora calc = new Calculadora();
		
		// Ação
		int resultado = calc.somar(a, b);
		
		
		// Verificação
		Assert.assertEquals(8, resultado);
	}
}
