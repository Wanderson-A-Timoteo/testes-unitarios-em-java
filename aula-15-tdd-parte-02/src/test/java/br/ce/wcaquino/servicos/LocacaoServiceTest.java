package br.ce.wcaquino.servicos;



import static br.ce.wcaquino.utils.DataUtils.isMesmaData;
import static br.ce.wcaquino.utils.DataUtils.obterDataComDiferencaDias;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;

public class LocacaoServiceTest {

	private LocacaoService service;
	
	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Before
	public void setup(){
		service = new LocacaoService();
	}
	
	@Test
	public void deveAlugarFilme() throws Exception {
		//cenario
		Usuario usuario = new Usuario("Usuario 1");
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 1, 5.0));
		
		//acao
		Locacao locacao = service.alugarFilme(usuario, filmes);
			
		//verificacao
		error.checkThat(locacao.getValor(), is(equalTo(5.0)));
		error.checkThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
		error.checkThat(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)), is(true));
	}
	
	@Test(expected = FilmeSemEstoqueException.class)
	public void naoDeveAlugarFilmeSemEstoque() throws Exception{
		//cenario
		Usuario usuario = new Usuario("Usuario 1");
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 0, 4.0));
		
		//acao
		service.alugarFilme(usuario, filmes);
	}
	
	@Test
	public void naoDeveAlugarFilmeSemUsuario() throws FilmeSemEstoqueException{
		//cenario
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 1, 5.0));
		
		//acao
		try {
			service.alugarFilme(null, filmes);
			Assert.fail();
		} catch (LocadoraException e) {
			assertThat(e.getMessage(), is("Usuario vazio"));
		}
	}

	@Test
	public void naoDeveAlugarFilmeSemFilme() throws FilmeSemEstoqueException, LocadoraException{
		//cenario
		Usuario usuario = new Usuario("Usuario 1");
		
		exception.expect(LocadoraException.class);
		exception.expectMessage("Filme vazio");
		
		//acao
		service.alugarFilme(usuario, null);
	}
	
	// Desconto de 25% no 3?? Filme
	@Test
	public void devePagar75PorCentoNoFilme3() throws FilmeSemEstoqueException, LocadoraException {
		// Cen??rio
		Usuario usuario = new Usuario("Usuario 01");
		List<Filme> filmes = Arrays.asList(
				new Filme("Filme 1", 2, 4.0), 
				new Filme("Filme 2", 2, 4.0), 
				new Filme("Filme 3", 2, 4.0)  // Desconto de 25% = 3.0
				);
				
		// A????o
		Locacao resultado = service.alugarFilme(usuario, filmes);
		
		// Verifica????o
		// Filme 4.0 - 25% = 3.0 ent??o 4.0 + 4.0 + 3.0 = 11.0
		assertThat(resultado.getValor(), is(11.0));
	}
	
	// Desconto de 50% no 4?? Filme
	@Test
	public void devePagar50PorCentoNoFilme4() throws FilmeSemEstoqueException, LocadoraException {
		// Cen??rio
		Usuario usuario = new Usuario("Usuario 01");
		List<Filme> filmes = Arrays.asList(
				new Filme("Filme 1", 2, 4.0), 
				new Filme("Filme 2", 2, 4.0), 
				new Filme("Filme 3", 2, 4.0), // Desconto de 25% = 3.0
				new Filme("Filme 4", 2, 4.0)  // Desconto de 50% = 2.0
				);
				
		// A????o
		Locacao resultado = service.alugarFilme(usuario, filmes);
		
		// Verifica????o
		// Filme 4.0 - 50% = 2.0 ent??o 4.0 + 4.0 + 3.0 + 2.0 = 13.0
		assertThat(resultado.getValor(), is(13.0));
	}
	
	// Desconto de 75% no 5?? Filme
	@Test
	public void devePagar25PorCentoNoFilme5() throws FilmeSemEstoqueException, LocadoraException {
		// Cen??rio
		Usuario usuario = new Usuario("Usuario 01");
		List<Filme> filmes = Arrays.asList(
				new Filme("Filme 1", 2, 4.0), 
				new Filme("Filme 2", 2, 4.0), 
				new Filme("Filme 3", 2, 4.0), // Desconto de 25% = 3.0
				new Filme("Filme 4", 2, 4.0), // Desconto de 50% = 2.0
				new Filme("Filme 5", 2, 4.0)  // Desconto de 75% = 1.0
				);
				
		// A????o
		Locacao resultado = service.alugarFilme(usuario, filmes);
		
		// Verifica????o
		// Filme 4.0 - 75% = 1.0 ent??o 4.0 + 4.0 + 3.0 + 2.0 + 1.0 = 14.0
		assertThat(resultado.getValor(), is(14.0));
	}
	
	// Desconto de 100% no 6?? Filme
	@Test
	public void devePagar00PorCentoNoFilme6() throws FilmeSemEstoqueException, LocadoraException {
		// Cen??rio
		Usuario usuario = new Usuario("Usuario 01");
		List<Filme> filmes = Arrays.asList(
				new Filme("Filme 1", 2, 4.0), 
				new Filme("Filme 2", 2, 4.0), 
				new Filme("Filme 3", 2, 4.0), // Desconto de 25% = 3.0
				new Filme("Filme 4", 2, 4.0), // Desconto de 50% = 2.0
				new Filme("Filme 5", 2, 4.0), // Desconto de 75% = 1.0
				new Filme("Filme 6", 2, 4.0)  // Desconto de 100% = 0.0
				);
				
		// A????o
		Locacao resultado = service.alugarFilme(usuario, filmes);
		
		// Verifica????o
		// Filme 4.0 - 100% = 0.0 ent??o 4.0 + 4.0 + 3.0 + 2.0 + 1.0 + 0.0 = 14.0
		assertThat(resultado.getValor(), is(14.0));
	}
}
