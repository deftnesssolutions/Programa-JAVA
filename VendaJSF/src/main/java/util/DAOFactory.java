package util;

import DAO.ClienteDAO;
import DAO.ClienteDAOHibernate;
import DAO.ProdutoDAO;
import DAO.ProdutoDAOHibernate;

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
}
