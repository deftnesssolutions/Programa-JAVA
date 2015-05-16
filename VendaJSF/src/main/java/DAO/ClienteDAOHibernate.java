package DAO;

import org.hibernate.Session;

import modelo.Cliente;

public class ClienteDAOHibernate implements ClienteDAO {

	private Session session;
	
	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}
	
	@Override
	public void salvar(Cliente cliente) {
		this.session.save(cliente);
	}

}
