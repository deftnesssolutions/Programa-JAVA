package Bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import RN.ClienteRN;
import RN.ProdutoRN;
import modelo.Cliente;
import modelo.Produto;

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
			//cboClientes=new ArrayList<SelectItem>();
			ClienteRN clienteRN = new ClienteRN();
			List<Cliente> listaClientes = clienteRN.listar();
			
			if(listaClientes !=null && !listaClientes.isEmpty())
			{
				SelectItem item;
				for (Cliente c : listaClientes)
				{
					item = new SelectItem(c, c.getNome());
					cboClientes.add(item);
				}
			}
		}
		
		
		return cboClientes;
	}

	 public void buscarProduto()
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
		 
	 }

	private void calculaTotal() {
		if(!this.carrinhoCompra.isEmpty())
		{
			for (Produto p : this.carrinhoCompra) {
				valorTotal += p.getValor();
			}
		}
		
	}
}
