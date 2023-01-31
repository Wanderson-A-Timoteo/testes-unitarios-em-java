package br.ce.wcaquino.servicos;

import org.junit.Assert;
import org.junit.Test;

import br.ce.wcaquino.entidades.Usuario;

public class AssertTest {
	
	@Test
	public void test() {
		Assert.assertTrue(true);
		Assert.assertFalse(false);
		
		// AssertEquals compara se um parametro é igual ao outro
		Assert.assertEquals(1, 1); // 1 é igual a 1
		
		/* O terceiro parametro é a margem de erro. Nele passamos quantas casas decimais
		 * queremos comparar.  
		 */
		Assert.assertEquals(0.5123, 0.512, 0.001); // 0.5123 é igual a 0.512 com margem de erro
		
		/** 
		 * Se aumentar a margem de erro ocorrerá falha, pois o teste 
		 * vai comparar até a quarta casa decimal no segundo parametro
		 */
		//Assert.assertEquals(0.5123, 0.512, 0.0001);  
		
		Assert.assertEquals(Math.PI, 3.14, 0.01);
		
		int i = 5; // Tipo primitivo
		Integer i2 = 5; // Tipo Objeto
		/*
		 * O Java não permite comparação de tipos primitivos com tipos objetos
		 */
		//Assert.assertEquals(i, i2); // Ocorrerá erro na comparação de primitivo com objeto
		
		/*
		 *Podemos comparar um valor primitivo com um objeto de duas formas 
		 */
		// 1º forma: Convertendo o tipo primitivo para objeto
		Assert.assertEquals(Integer.valueOf(i), i2);
		
		// 2º forma: Convertendo o objeto para tipo primitivo
		Assert.assertEquals(i, i2.intValue());
		
		// Comparação de Styrings
		Assert.assertEquals("bola", "bola");
		
		/*
		 *  Comparação de Strings precisam ser todas iguais, neste caso como uma String
		 *  esta maiuscula ocorrerá falha
		 */
		//Assert.assertEquals("bola", "Bola");
		
		/*
		 * Para comparação de Strings com letras maiusculas e minuscula devemos usar
		 * assertTrue  
		 **/
		Assert.assertTrue("bola".equalsIgnoreCase("Bola"));
		
		// Comparação de radical
		Assert.assertTrue("bola".startsWith("bo"));
		
		//Comparação entre dois objetos
		Usuario u1 = new Usuario("Usuario 1");
		Usuario u2 = new Usuario("Usuario 1");
		Usuario u3 = u2;
		Usuario u4 = null;
		/* 
		 * Neste caso o assertEquals não considera esses dois objetos iguais, pois não 
		 * existe um metodo equals implementado no objeto Usuario.
		 * pois quem diz se um objeto é igual a outro objeto será o próprio objeto e para 
		 * isso é necessario criar um metodo equals no objeto que esta sendo comparado, 
		 * neste caso o objeto Usuario.
		 */
		/* Gera erro pois sem o metodo equals implementado em Usuario quem fará a comparação 
		 * será o Objeto mais ancestral object e este fará a comparação entre instancias, 
		 * isso retornará falso pois são instancia diferentes, embora sejam o mesmo objto
		 */
		//Assert.assertEquals(u1, u2); 
		
		/* 
		 * Desta forma o object mais ancestral de Usuario comparará a instancia e retornará 
		 * verdadeiro
		 */
		Assert.assertEquals(u1, u1); 
		
		/*
		 * Então, como podemos comprar U1 e U2 e obter o resultado verdadeiro já que os 
		 * dois são o mesmo objeto?
		 * Criando o metodo equal no objeto que esta sendo comparado, neste caso o 
		 * objeto Usuario.
		 */
		/*
		 * Após criar o metodo hashCode() and equals(), no objeto Usuario, a comparação 
		 * entre os dois objetos retornará verdadeiro
		 */
		Assert.assertEquals(u1, u2);
		
		/*
		 * Mas, se precisarmos saber se a instancia entre os dois objetos em 
		 * comparação são iguais, ou seja, saber se é o mesmo objeto?  
		 */
		/* 
		 * Neste caso usamos o assertSame para comparar se uma instancia é igual a outra
		 * Neste caso gerara erro pois u1 tem instancia diferente de u2
		 */
		//Assert.assertSame(u1, u2); 
		
		// Neste caso a comparação é verdadeira
		Assert.assertSame(u1, u1); 
		
		/* 
		 * Neste caso é verdadeiro, pois estou apontando que u3 recebe o objeto u2.
		 * Assim u3 terá a mesma instancia que u2
		 */
		Assert.assertSame(u3, u2);
		
		/*
		 * Se precisarmos verificar se um objeto é nulo? Podemos comparar de dois formas
		 */
		// 1º forma com assertTrue
		Assert.assertTrue(u4 == null);
		
		// 2º forma com assertNull
		Assert.assertNull(u4);
	}

}
