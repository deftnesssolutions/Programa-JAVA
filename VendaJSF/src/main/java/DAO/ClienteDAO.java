package DAO;

import java.util.List;

import modelo.Cliente;

public interface ClienteDAO
{
	public void salvar(Cliente cliente);

	public List<Cliente> listar();

	public void excluir(Cliente cliente);

	public Cliente pesquisar(String string);

	public void alterar(Cliente cliente);

	
}
