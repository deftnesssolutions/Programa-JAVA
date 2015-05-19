package DAO;

import java.util.List;
import modelo.Venda;
import org.hibernate.Criteria;
import org.hibernate.Session;

public class VendaDAOHibernate implements VendaDAO
{
	private Session session;

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public void registraVenda(Venda v1) {
		session.save(v1);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Venda> listar() {
		Criteria lista = session.createCriteria(Venda.class);
		return lista.list();
	}

	@Override
	public void excluir(Venda venda) {
		session.delete(venda);
	}
		
}
