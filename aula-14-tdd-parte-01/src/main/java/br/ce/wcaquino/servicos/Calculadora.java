package br.ce.wcaquino.servicos;

import br.ce.wcaquino.exceptions.NaoPodeDividirPorZeroException;

public class Calculadora {

	public int somar(int a, int b) {		
		return a + b;
	}

	public int subtrair(int a, int b) {		
		return a - b;
	}

	public int Multiplicar(int a, int b) {
		return a * b;
	}

	public int Dividir(int a, int b) throws NaoPodeDividirPorZeroException {
		if(b == 0) {
			throw new NaoPodeDividirPorZeroException();
		}
		
		return a / b;
	}

	public int RestoDivisao(int a, int b) {
		return a % b;
	}

}
