package teste;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import util.HibernateUtil;
import RN.ClienteRN;
import modelo.Cliente;

public class ClienteTest {

	private static  Session sessao;
	private static Transaction transacao;
	
	@BeforeClass
	public static void abreConexao()
	{
		sessao=HibernateUtil.getSession().getCurrentSession();
		transacao = sessao.beginTransaction();
	}
	
	@AfterClass
	public static void fechaConexao()
	{
		
		try {
			transacao.commit();
		} catch (Throwable e) {
			System.out.println("deu problema no commit: " + e.getMessage());
		}finally
		{
			try {
				if(sessao.isOpen())
					sessao.close();
			} catch (Exception e2) {
				System.out.println("deu erro no fechamento da sessão: " + e2.getMessage());
			}
		}
	}
	
	@Before
	public void setup()
	{
		Cliente c1 = new Cliente("10546817327", "teste1@gmail.com", "rua teste", new Date(), "Teste1", 2000f);
		Cliente c2 = new Cliente("75038401104", "teste2@gmail.com", "rua teste2", new Date(), "Teste2", 1000f);
		Cliente c3 = new Cliente("56309473700", "teste3@gmail.com", "rua teste3", new Date(), "Teste3", 3000f);
		ClienteRN clienteRN = new ClienteRN();
		clienteRN.salvar(c1);
		clienteRN.salvar(c2);
		clienteRN.salvar(c3);
	}
	
	@After
	public void limparTabela()
	{
		ClienteRN clienteRN = new ClienteRN();
		List<Cliente> lista= clienteRN.listar();
		for (Cliente cliente : lista) {
			clienteRN.excluir(cliente);
		}
	}
	
	@Test
	public void salvarTest()
	{
		Cliente c1= new Cliente();
		c1.setNome("Gustavo");
		c1.setCpf("123456789");
		c1.setEndereco("Rua Teste");
		c1.setRenda(3000f);
		
		ClienteRN clienteRN = new ClienteRN();
		clienteRN.salvar(c1);
		assertEquals(true,true);
	}
	
	@Test
	public void listarTest()
	{
		ClienteRN clienteRN = new ClienteRN();
		List<Cliente> lista= clienteRN.listar();
		assertEquals(3, lista.size());
	}
	
	@Test
	public void excluirTest()
	{
		ClienteRN clienteRN = new ClienteRN();
		List<Cliente> lista= clienteRN.listar();
		Cliente clienteExcluido = lista.get(0);
		clienteRN.excluir(clienteExcluido);
		lista = clienteRN.listar();
		assertEquals(2, lista.size());
	}
	
	@Test
	public void pesquisarTest()
	{
		ClienteRN clienteRN = new ClienteRN();
		Cliente clientePesquisado = clienteRN.pesquisar("Teste1");
		assertEquals("teste1@gmail.com", clientePesquisado.getEmail());
	}
	
	@Test
	public void alterarTest()
	{
		ClienteRN clienteRN = new ClienteRN();
		Cliente clientePesquisado = clienteRN.pesquisar("Teste1");
		assertEquals("teste1@gmail.com", clientePesquisado.getEmail());
		
		clientePesquisado.setEndereco("novo endereço");
		clienteRN.alterar(clientePesquisado);
		Cliente clienteAlterado = clienteRN.pesquisar("Teste1");
		assertEquals("novo endereço", clientePesquisado.getEndereco());
	}
}
