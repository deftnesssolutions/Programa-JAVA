package DAO;

import java.util.List;

import modelo.Produto;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

public class ProdutoDAOHibernate implements ProdutoDAO {

	private Session session;

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(Produto produto) {
		this.session.save(produto);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Produto> listar() {
		Criteria lista = session.createCriteria(Produto.class);
		return lista.list();
	}

	@Override
	public void excluir(Produto produto) {
		this.session.delete(produto);
	}

	@Override
	public Produto pesquisar(String string) {
		Query consultaDescricao= this.session.createQuery("FROM Produto p WHERE p.descricao LIKE :descricao");
		consultaDescricao.setString("descricao", "%"+string+"%");
		return (Produto) consultaDescricao.uniqueResult();
	}

	@Override
	public void alterar(Produto produto) {
		this.session.merge(produto);
	}
	
	
}
