package br.ce.wcaquino.servicos;



import static br.ce.wcaquino.utils.DataUtils.isMesmaData;
import static br.ce.wcaquino.utils.DataUtils.obterDataComDiferencaDias;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.junit.Test;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;

public class LocacaoServiceTest {

	@Test
	public void teste() {
		//cenario
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 1");
		Filme filme = new Filme("Filme 1", 2, 5.0);
		
		//acao
		Locacao locacao = service.alugarFilme(usuario, filme);
		
		/* verificacao				 
		 * Adicionando import estático, botão direito no metodo is, assertThat e outros /Source/Add import
		 * ou colocar o curso encima do metodo + tecla de atalho ctrl + shift + M 
		 */
		// Verifique que o valor da locação é 5.0
		assertThat(locacao.getValor(), is(5.0));
		
		// Verifique que o valor da locação é igual a 5.0
		assertThat(locacao.getValor(), is(equalTo(5.0)));
		
		// Verifique que o valor da locação NÂO é 6.0 
		assertThat(locacao.getValor(), is(not(6.0)));
		
		// Verifique que a data é mesma data é verdadeiro
		assertThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
		assertThat(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)), is(true));
	}
}
