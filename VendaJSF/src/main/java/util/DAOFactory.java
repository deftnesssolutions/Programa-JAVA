package util;

import DAO.ClienteDAO;
import DAO.ClienteDAOHibernate;

public class DAOFactory
{
	public static ClienteDAO criaClienteDAO()
	{
		ClienteDAOHibernate clienteDAOHibernate =new ClienteDAOHibernate();
		clienteDAOHibernate.setSession(HibernateUtil.getSession().getCurrentSession());
		return clienteDAOHibernate;
	}
}
