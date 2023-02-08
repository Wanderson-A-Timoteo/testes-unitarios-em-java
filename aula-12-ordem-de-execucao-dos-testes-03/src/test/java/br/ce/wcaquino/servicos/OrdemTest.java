package br.ce.wcaquino.servicos;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/* Para garantir que os metodos serão executados na ordem que eu quero,
 * podemos adicionar a anotação @FixMethodOrder passando o metodo de ordenação 
 * por ordem alfabetica NAME_ASCENDING.
 * Caso tenhamos varios metodos com nomes diferentes porém algum deles começar 
 * com letra iguais ou um teste com letra D executa depois de outro com letra R
 * podemos iniciar o nome dos metodos por exemplo com teste01NOME, teste02NOME...
 * Assim garantiremos que eles sejam executados em ordem.
 * Apesar de existir esses metodos para garantir o ordem de execução 
 * é importante que deixemos nossos testes independentes.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrdemTest {
	
	public static int contador = 0;
	
	@Test
	public void inicia() {
		contador = 1;	
	}
	
	@Test
	public void verifica() {
		Assert.assertEquals(1, contador);
	}	
}
