package Bean;

import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import modelo.Produto;
import RN.ProdutoRN;

@ManagedBean(name="produtoBean")
@RequestScoped
public class ProdutoBean
{
	private Produto produtoSelecionado = new Produto();
	private List<Produto> lista =  null;
	
	public Date DataBD()
	 {
		 java.util.Date d1 = new java.util.Date();  
		 java.sql.Date d2 = new java.sql.Date(d1.getTime());
		 return d2;
	 }
	
	public void salvar()
	{
		ProdutoRN produtoRN = new ProdutoRN();
		produtoSelecionado.setDataCadastro((java.sql.Date) DataBD());
		if(this.produtoSelecionado.getId() != null && this.produtoSelecionado.getId() != 0)
		{
			produtoRN.alterar(this.produtoSelecionado);
		}else
		{
			produtoRN.salvar(produtoSelecionado);
			FacesMessage msg= new FacesMessage("Produto cadastrado com sucesso !!");
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null, msg);
		}
		this.lista = null;
	}

	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}

	public List<Produto> getLista() {
		ProdutoRN produtoRN = new ProdutoRN();
		if(lista==null)
		{
			lista=produtoRN.listar();
		}
		return lista;
	}
	
	public void excluir()
	{
		ProdutoRN produtoRN = new ProdutoRN();
		produtoRN.excluir(this.produtoSelecionado);
		this.lista = null;
	}
	
	public void novo()
	{
		this.produtoSelecionado = new Produto();
	}
}
