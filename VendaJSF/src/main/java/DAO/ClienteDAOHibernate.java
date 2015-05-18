package DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
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
	@SuppressWarnings("unchecked")
	@Override
	public List<Cliente> listar() {
		Criteria lista = session.createCriteria(Cliente.class);
		return lista.list();
	}
	@Override
	public void excluir(Cliente cliente) {
		this.session.delete(cliente);
	}
	@Override
	public Cliente pesquisar(String string) {
		Query consultaName = this.session.createQuery("FROM Cliente c WHERE c.nome LIKE :nome");
		consultaName.setString("nome", "%"+string+"%");
		return (Cliente) consultaName.uniqueResult();
	}
	@Override
	public void alterar(Cliente cliente) {
		//this.session.update(cliente);
		this.session.merge(cliente);
	}
	

}
