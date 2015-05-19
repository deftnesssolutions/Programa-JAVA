package RN;

import java.util.List;

import util.DAOFactory;
import DAO.VendaDAO;
import modelo.Venda;

public class VendaRN
{
	private VendaDAO vendaDAO;
	
	public VendaRN()
	{
		this.vendaDAO= DAOFactory.criaVendaDAO();
	}
	
	public void registraVenda(Venda v1)
	{
		this.vendaDAO.registraVenda(v1);
	}
	
	public List<Venda> listar()
	{
		return this.vendaDAO.listar();
	}

	public void excluir(Venda venda) {
		this.vendaDAO.excluir(venda);
	}
	
	
}
