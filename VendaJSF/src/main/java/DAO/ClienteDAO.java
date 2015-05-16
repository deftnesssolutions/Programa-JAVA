package DAO;

import java.util.List;

import modelo.Cliente;

public interface ClienteDAO
{
	public void salvar(Cliente cliente);

	public List<Cliente> listar();
}
