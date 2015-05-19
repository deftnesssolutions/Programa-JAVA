package teste;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import modelo.Cliente;
import modelo.Produto;
import modelo.Venda;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import RN.ClienteRN;
import RN.ProdutoRN;
import RN.VendaRN;

public class VendaTest extends TestAbstract
{
	Cliente c1;
	Cliente c2;
	Cliente c3;
	Produto p1;
	Produto p2;
	Produto p3;
	@Before
	public void setup()
	{
		c1 = new Cliente("10546817327", "teste1@gmail.com", "rua teste", new Date(), "Teste1", 2000f);
		c2 = new Cliente("75038401104", "teste2@gmail.com", "rua teste2", new Date(), "Teste2", 1000f);
		c3 = new Cliente("56309473700", "teste3@gmail.com", "rua teste3", new Date(), "Teste3", 3000f);
		ClienteRN clienteRN = new ClienteRN();
		clienteRN.salvar(c1);
		clienteRN.salvar(c2);
		clienteRN.salvar(c3);
		
		p1 = new Produto("Lote", "Caderno", (java.sql.Date) DataBD(), 50, 7.0);
		p2 = new Produto("Lote2", "Regua", (java.sql.Date) DataBD(), 30, 2.5);
		p3 = new Produto("Fardo", "Papel", (java.sql.Date) DataBD(), 300, 1.5);
		ProdutoRN produtoRN = new ProdutoRN();
		produtoRN.salvar(p1);
		produtoRN.salvar(p2);
		produtoRN.salvar(p3);
		
		
	}
	
	@After
	public void limparTabela()
	{
		ProdutoRN produtoRN = new ProdutoRN();
		List<Produto> lp= produtoRN.listar();
		for (Produto produto : lp) {
			produtoRN.excluir(produto);
		}
		
		ClienteRN clienteRN = new ClienteRN();
		List<Cliente> lc= clienteRN.listar();
		for (Cliente cliente : lc) {
			clienteRN.excluir(cliente);
		}
		
		VendaRN vendaRN = new VendaRN();
		List<Venda> lv = vendaRN.listar();
		for (Venda venda : lv) {
			vendaRN.excluir(venda);
		}
	}
	
	@Test
	public void registraVendaTest() 
	{
		VendaRN vendaRN = new VendaRN();
		Venda v1 = new Venda();
		v1.setCliente(c1);
		v1.setProduto(p1);
		v1.setDataVenda((java.sql.Date) DataBD());
		
		Venda v2 = new Venda();
		v1.setCliente(c2);
		v1.setProduto(p2);
		v1.setDataVenda((java.sql.Date) DataBD());
		
		Venda v3 = new Venda();
		v1.setCliente(c3);
		v1.setProduto(p3);
		v1.setDataVenda((java.sql.Date) DataBD());
		
		vendaRN.registraVenda(v1);
		vendaRN.registraVenda(v2);
		vendaRN.registraVenda(v3);
		
		List<Venda> lista= vendaRN.listar();
		assertEquals(3, lista.size());
	}

}
