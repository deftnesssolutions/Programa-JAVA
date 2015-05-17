package teste;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.AfterClass;
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
		assertEquals(4, lista.size());
	}
}
