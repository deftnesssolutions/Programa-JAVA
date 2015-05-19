package DAO;

import java.util.List;

import modelo.Venda;

public interface VendaDAO
{
	public void registraVenda(Venda v1);

	public List<Venda> listar();

	public void excluir(Venda venda);	
}
