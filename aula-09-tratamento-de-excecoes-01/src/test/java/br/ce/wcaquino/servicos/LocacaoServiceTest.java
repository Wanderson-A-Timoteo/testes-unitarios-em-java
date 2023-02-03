package br.ce.wcaquino.servicos;



import static br.ce.wcaquino.utils.DataUtils.isMesmaData;
import static br.ce.wcaquino.utils.DataUtils.obterDataComDiferencaDias;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import junit.framework.Assert;

public class LocacaoServiceTest {

	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testeLocacao() throws Exception {
		//cenario
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 1");
		Filme filme = new Filme("Filme 1", 2, 5.0);
		
		//acao
		Locacao locacao = service.alugarFilme(usuario, filme);
			
		//verificacao
		error.checkThat(locacao.getValor(), is(equalTo(5.0)));
		error.checkThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
		error.checkThat(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)), is(true));		
	}
	
	// 3 tipos de tratamento de exceção
	// 1º Exceção elegante 
	// Este tese verifica se foi lançado uma exceção de filme sem estoque para passar no teste
	@Test(expected = Exception.class)
	public void testeLocacao_filmesSemEstoque() throws Exception {
		//cenario
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 1");
		Filme filme = new Filme("Filme 1", 0, 5.0);
		
		//acao
		service.alugarFilme(usuario, filme);
	}
	
	// 2º - Exceção robusta - Parte 01
	// Este tese verifica se foi lançado uma exceção com a msn "Filme sem estoque" para passar no teste
	@Test
	public void testeLocacao_filmesSemEstoque_02() {
		//cenario
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 1");
		Filme filme = new Filme("Filme 1", 0, 5.0); // Lança exceção pq estoque tá zerado
		
		//acao
		try {
			service.alugarFilme(usuario, filme);
		} catch (Exception e) {
			assertThat(e.getMessage(), is("Filme sem estoque"));
		}
	}
	
	// 2º - Exceção robusta - Parte 02
	// Mas se o estoque não estiver zerado a exceção não é lançada
	// Para resguardar que seja capturada uma exceção é preciso add Assert.fail()
	@Test
	public void testeLocacao_filmesSemEstoque_03() {
		//cenario
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 1");
		Filme filme = new Filme("Filme 1", 2, 5.0); // Não lança exceção pq tem estoque
		
		//acao
		try {
			service.alugarFilme(usuario, filme);
			// Para não gerar um falso positivo
			// Se não for lançado nenhuma exceção podemos resgardar o teste com Assert.fail()
			org.junit.Assert.fail("Deveria ter lançado uma exceção");
		} catch (Exception e) {
			assertThat(e.getMessage(), is("Filme sem estoque"));
		}
	}
	
	// 3º - Exceção forma nova 
	// @Rule ExpectedException
	@Test
	public void testeLocacao_filmesSemEstoque_04() throws Exception {
		//cenario
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 1");
		Filme filme = new Filme("Filme 1", 0, 5.0);
		
		// ExpectedException - Precisa ser declarada antes da ação
		exception.expect(Exception.class);	// É esperado que uma exception seja lançada
		exception.expectMessage("Filme sem estoque");
		
		//acao
		service.alugarFilme(usuario, filme);
			
		
	}
}
