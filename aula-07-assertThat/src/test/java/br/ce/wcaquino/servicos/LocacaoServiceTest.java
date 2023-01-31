package br.ce.wcaquino.servicos;



import java.util.Date;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.utils.DataUtils;

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
		 * Em assertThat = igual que
		 * O 1º parametro será o inverso, ele será sempre o valor recebido e o 2º parametro
		 * será o valor que esperamos.
		 * Para comparar valores double é preciso importar de hamcrest o metodo CoreMatchers
		 * Tem duas formas de verificação
		 */ 
		// 1º forma de verificação ou comparação: Verifique que o valor da locação é 5.0
		Assert.assertThat(locacao.getValor(), CoreMatchers.is(5.0));
		
		// 2º forma de verificação: Verifique que o valor da locação é igual a 5.0
		Assert.assertThat(locacao.getValor(), CoreMatchers.is(CoreMatchers.equalTo(5.0)));
		
		// Negação em uma verificação: Verifique que o valor da locação NÂO é 6.0 
		Assert.assertThat(locacao.getValor(), CoreMatchers.is(CoreMatchers.not(6.0)));
		
		Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
		Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));
	}
}
