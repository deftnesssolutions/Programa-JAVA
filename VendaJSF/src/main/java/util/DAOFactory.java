package util;

import DAO.ClienteDAO;
import DAO.ClienteDAOHibernate;
import DAO.ProdutoDAO;
import DAO.ProdutoDAOHibernate;
import DAO.VendaDAO;
import DAO.VendaDAOHibernate;

public class DAOFactory
{
	public static ClienteDAO criaClienteDAO()
	{
		ClienteDAOHibernate clienteDAOHibernate =new ClienteDAOHibernate();
		clienteDAOHibernate.setSession(HibernateUtil.getSession().getCurrentSession());
		return clienteDAOHibernate;
	}

	public static ProdutoDAO criaProdutoDAO() {
		ProdutoDAOHibernate produtoDAOHibernate =new ProdutoDAOHibernate();
		produtoDAOHibernate.setSession(HibernateUtil.getSession().getCurrentSession());
		return produtoDAOHibernate;
	}

	public static VendaDAO criaVendaDAO() {
		VendaDAOHibernate vendaDAOHibernate =new VendaDAOHibernate();
		vendaDAOHibernate.setSession(HibernateUtil.getSession().getCurrentSession());
		return vendaDAOHibernate;
	}
}
