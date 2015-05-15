package modelo;

import static org.junit.Assert.*;

import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import util.HibernateUtil;

public class ProdutoTest
{
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
	
	public Date DataBD()
	 {
		 java.util.Date d1 = new java.util.Date();  
		 java.sql.Date d2 = new java.sql.Date(d1.getTime());
		 return d2;
	 }
	
	@Before
	public void setup()
	{
		Produto p1 = new Produto("Lote", "Caderno", (java.sql.Date) DataBD(), 50, 7.0);
		Produto p2 = new Produto("Lote2", "Regua", (java.sql.Date) DataBD(), 30, 2.5);
		Produto p3 = new Produto("Fardo", "Papel", (java.sql.Date) DataBD(), 300, 1.5);
		Produto p4 = new Produto("Edicao", "Livro", (java.sql.Date) DataBD(), 10, 30.0);
		Produto p5 = new Produto("Caixa", "Caneta", (java.sql.Date) DataBD(), 90, 1.5);
		
		sessao.save(p1);
		sessao.save(p2);
		sessao.save(p3);
		sessao.save(p4);
		sessao.save(p5);
	}
	
	@Test
	public void salvarProdutoTest()
	{
		String sql="FROM produto p WHERE p.descricao LIKE: descricao ";
		Query consulta = sessao.createQuery(sql);
		consulta.setString("descricao", "%Re%");
		
		Produto produtoPesquisado = (Produto) consulta.uniqueResult();
		assertEquals("Lote2", produtoPesquisado.getUnidade());
	}
	
}
