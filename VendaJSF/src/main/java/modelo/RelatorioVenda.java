package modelo;

import java.util.Date;

public class RelatorioVenda {
	private String cliente_nome;
	private String produto_descricao;
	private Double produto_valor;
	private Date venda_data_venda;
	public String getCliente_nome() {
		return cliente_nome;
	}
	public void setCliente_nome(String cliente_nome) {
		this.cliente_nome = cliente_nome;
	}
	public String getProduto_descricao() {
		return produto_descricao;
	}
	public void setProduto_descricao(String produto_descricao) {
		this.produto_descricao = produto_descricao;
	}
	public Double getProduto_valor() {
		return produto_valor;
	}
	public void setProduto_valor(Double produto_valor) {
		this.produto_valor = produto_valor;
	}
	public Date getVenda_data_venda() {
		return venda_data_venda;
	}
	public void setVenda_data_venda(Date venda_data_venda) {
		this.venda_data_venda = venda_data_venda;
	}
	
	
}
