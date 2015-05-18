package RN;

import java.util.List;

import modelo.Produto;
import util.DAOFactory;
import DAO.ProdutoDAO;

public class ProdutoRN 
{
	private ProdutoDAO produtoDAO;
	
	public ProdutoRN()
	{
		this.produtoDAO=DAOFactory.criaProdutoDAO();
	}

	public void salvar(Produto p1) {
		this.produtoDAO.salvar(p1);	
	}

	public List<Produto> listar() {
		return this.produtoDAO.listar();
	}

	public void excluir(Produto produto) {
		this.produtoDAO.excluir(produto);
	}

	public Produto pesquisar(String string) {
		return this.produtoDAO.pesquisar(string) ;
	}

	public void alterar(Produto produto) {
		this.produtoDAO.alterar(produto);
	}
}
