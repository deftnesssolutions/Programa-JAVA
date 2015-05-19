package teste;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import util.HibernateUtil;

public abstract class TestAbstract
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
	public static Date DataBD()
	 {
		 java.util.Date d1 = new java.util.Date();  
		 java.sql.Date d2 = new java.sql.Date(d1.getTime());
		 return d2;
	 }
}
