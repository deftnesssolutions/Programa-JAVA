package Bean;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import util.DAOFactory;
import RN.ClienteRN;
import RN.ProdutoRN;
import RN.VendaRN;
import modelo.Cliente;
import modelo.Produto;
import modelo.Venda;

@ManagedBean(name="vendaBean")
@ViewScoped
public class VendaBean
{
	private Cliente clienteSelecionado;
	private Produto produtoSelecionado = new Produto();
	private Double valorTotal;
	private List<SelectItem> cboClientes;
	

	List<Produto> carrinhoCompra = new ArrayList<Produto>();
	
	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public List<Produto> getCarrinhoCompra() {
		return carrinhoCompra;
	}

	public void setCarrinhoCompra(List<Produto> carrinhoCompra) {
		this.carrinhoCompra = carrinhoCompra;
	}
	
	public List<SelectItem> getCboClientes() {
		
		if(this.cboClientes==null)
		{
			this.cboClientes = new ArrayList<SelectItem>();
			ClienteRN clienteRN = new ClienteRN();
			List<Cliente> listaClientes = clienteRN.listar();
			
			if(listaClientes !=null && !listaClientes.isEmpty())
			{
				SelectItem item;
				for (Cliente c : listaClientes)
				{
					item = new SelectItem(c, c.getNome());
					this.cboClientes.add(item);
				}
			}
		}
		
		return cboClientes;
	}

	 public String buscarProduto()
	 {
		 ProdutoRN produtoRN = new ProdutoRN();
		 Produto produtoPesquisado = new Produto();
		 if(this.produtoSelecionado.getDescricao() !=null && !this.produtoSelecionado.getDescricao().equals(""))
		 {
			produtoPesquisado = produtoRN.pesquisar(this.produtoSelecionado.getDescricao());
		 }
		 
		 if(produtoPesquisado!=null)
		 {
			 this.carrinhoCompra.add(produtoPesquisado);
			 calculaTotal();
		 }
		 return null;
	 }

	private void calculaTotal() {
		valorTotal = 0.0;
		if(!this.carrinhoCompra.isEmpty())
		{
			for (Produto p : this.carrinhoCompra) {
				valorTotal += p.getValor();
			}
		}
		
	}
	
	public String excluirProdutoCarrinho()
	{
		if(this.carrinhoCompra != null && !this.carrinhoCompra.isEmpty())
		{
			if(this.produtoSelecionado != null)
			{
				this.carrinhoCompra.remove(produtoSelecionado);
				calculaTotal();
			}
		}
		return null;
	}
	
	public String finalizarVenda()
	{
		if(!this.carrinhoCompra.isEmpty())
		{
			ArrayList<Venda> vendas = new ArrayList<Venda>();
			
			for (Produto p : this.carrinhoCompra) {
				if(this.clienteSelecionado != null)
				{
					vendas.add(new Venda(p,this.clienteSelecionado));
				}
			}
			
			for (Venda venda : vendas) {
				VendaRN vendaRN = new VendaRN();
				venda.setDataVenda((Date) DAOFactory.DataBD());
				vendaRN.registraVenda(venda);
			}
		}
		return null;
	}
}
