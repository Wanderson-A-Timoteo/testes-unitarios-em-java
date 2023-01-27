package negocio;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * Classe de teste criada para garantir o funcionamento das principais operações
 * sobre clientes, realizadas pela classe {@link GerenciadoraClientes}.
 * 
 * @author wanderson
 */
public class GerenciadoraClientesTest_Ex4 {

	private GerenciadoraClientes gerClientes;

	/**
	 * Teste básico da pesquisa de um cliente a partir do seu ID.
	 * 
	 * @author wanderson
	 */
	@Test
	public void testPesquisaCliente() {

		/* ========== Montagem do cenário ========== */
		
		// criando alguns clientes
		Cliente cliente01 = new Cliente(1, "Wanderson Timóteo", 39, "wanderson@gmail.com", 1, true);
		Cliente cliente02 = new Cliente(2, "Ryan Timóteo", 19, "ryan@gmail.com", 2, true);
		
		// inserindo os clientes criados na lista de clientes do banco
		List<Cliente> clientesDoBanco = new ArrayList<>();
		clientesDoBanco.add(cliente01);
		clientesDoBanco.add(cliente02);
		
		gerClientes = new GerenciadoraClientes(clientesDoBanco);

		/* ========== Execução ========== */
		Cliente cliente = gerClientes.pesquisaCliente(1);
		
		/* ========== Verificações ========== */
		assertThat(cliente.getId(), is(1));
		
	}
	
	/**
	 * Teste básico da remoção de um cliente a partir do seu ID.
	 * 
	 * @author wanderson
	 */
	@Test
	public void testRemoveCliente() {

		/* ========== Montagem do cenário ========== */
		
		// criando alguns clientes
		Cliente cliente01 = new Cliente(1, "Wanderson Timóteo", 39, "wanderson@gmail.com", 1, true);
		Cliente cliente02 = new Cliente(2, "Ryan Timóteo", 19, "ryan@gmail.com", 2, true);
		
		// inserindo os clientes criados na lista de clientes do banco
		List<Cliente> clientesDoBanco = new ArrayList<>();
		clientesDoBanco.add(cliente01);
		clientesDoBanco.add(cliente02);
		
		gerClientes = new GerenciadoraClientes(clientesDoBanco);
		
		/* ========== Execução ========== */
		boolean clienteRemovido = gerClientes.removeCliente(2);
		
		/* ========== Verificações ========== */
		assertThat(clienteRemovido, is(true));
		assertThat(gerClientes.getClientesDoBanco().size(), is(1));
		assertNull(gerClientes.pesquisaCliente(2));
		
	}
	
}

//Documenta��o e coment�rios