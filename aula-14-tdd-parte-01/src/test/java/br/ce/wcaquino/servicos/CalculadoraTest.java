package br.ce.wcaquino.servicos;

import org.junit.Test;

import br.ce.wcaquino.exceptions.NaoPodeDividirPorZeroException;

import org.junit.Assert;
import org.junit.Before;

public class CalculadoraTest {
	
	private Calculadora calc;
	
	@Before
	public void setup() {
		calc = new Calculadora();
	}

	@Test
	public void deveSomarDoisValores() {
		// Cenário
		int a = 5;
		int b = 3;
				
		// Ação
		int resultado = calc.somar(a, b);
		
		
		// Verificação
		Assert.assertEquals(8, resultado);
	}
	
	@Test
	public void deveSubtrairDoisValores() {
		// Cenário
		int a = 15;
		int b = 5;
		
		// Ação
		int resultado = calc.subtrair(a, b);
		
		
		// Verificação
		Assert.assertEquals(10, resultado);
	}
	
	@Test
	public void deveMultiplicarDoisValores() {
		// Cenário
		int a = 3;
		int b = 6;
		
		// Ação
		int resultado = calc.Multiplicar(a, b);
		
		
		// Verificação
		Assert.assertEquals(18, resultado);
	}
	
	@Test
	public void deveDividirDoisValores() throws NaoPodeDividirPorZeroException {
		// Cenário
		int a = 15;
		int b = 3;
		
		// Ação
		int resultado = calc.Dividir(a, b);
		
		
		// Verificação
		Assert.assertEquals(5, resultado);
	}
	
	@Test
	public void deveObterRestoDivisaoDoisValores() {
		// Cenário
		int a = 10;
		int b = 5;
		
		// Ação
		int resultado = calc.RestoDivisao(a, b);
		
		
		// Verificação
		Assert.assertEquals(0, resultado);
	}
	
	@Test(expected = NaoPodeDividirPorZeroException.class)
	public void deveLancarExcecaoAoDividirPorZero() throws NaoPodeDividirPorZeroException {
		// Cenário
		int a = 10;
		int b = 0;
		
		calc.Dividir(a, b);
	}
}
