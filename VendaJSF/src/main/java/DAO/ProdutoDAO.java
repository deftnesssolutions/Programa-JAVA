package DAO;

import java.util.List;

import modelo.Produto;

public interface ProdutoDAO
{
	public void salvar(Produto produto);

	public List<Produto> listar();

	public void excluir(Produto produto);

	public Produto pesquisar(String string);

	public void alterar(Produto produto);
}
