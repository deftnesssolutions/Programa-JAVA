package RN;

import modelo.Cliente;
import DAO.ClienteDAO;
import util.DAOFactory;

public class ClienteRN 
{
	private ClienteDAO clienteDAO;
	
	public ClienteRN()
	{
		this.clienteDAO=DAOFactory.criaClienteDAO();
	}

	public void salvar(Cliente c1)
	{
		this.clienteDAO.salvar(c1);
	}
}
