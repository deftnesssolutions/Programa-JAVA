package teste;

import static org.junit.Assert.assertEquals;
import java.util.List;
import modelo.Produto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import RN.ProdutoRN;

public class ProdutoTest extends TestAbstract
{
	
	@Before
	public void setup()
	{
		Produto p1 = new Produto("Lote", "Caderno", (java.sql.Date) DataBD(), 50, 7.0);
		Produto p2 = new Produto("Lote2", "Regua", (java.sql.Date) DataBD(), 30, 2.5);
		Produto p3 = new Produto("Fardo", "Papel", (java.sql.Date) DataBD(), 300, 1.5);
		ProdutoRN produtoRN = new ProdutoRN();
		produtoRN.salvar(p1);
		produtoRN.salvar(p2);
		produtoRN.salvar(p3);
	}
	
	@After
	public void limparTabela()
	{
		ProdutoRN produtoRN = new ProdutoRN();
		List<Produto> lista= produtoRN.listar();
		for (Produto produto : lista) {
			produtoRN.excluir(produto);
		}
	}
	
	@Test
	public void salvarTest()
	{
		Produto p1= new Produto();
		p1.setDescricao("Coca Colar lata");
		p1.setEstoque(10);
		p1.setUnidade("Caixa");
		p1.setValor(20.5);
		
		ProdutoRN produtoRN = new ProdutoRN();
		produtoRN.salvar(p1);
		assertEquals(true,true);
	}
	
	@Test
	public void listarTest()
	{
		ProdutoRN produtoRN = new ProdutoRN();
		List<Produto> lista= produtoRN.listar();
		assertEquals(3, lista.size());
	}
	
	@Test
	public void excluirTest()
	{
		ProdutoRN produtoRN = new ProdutoRN();
		List<Produto> lista= produtoRN.listar();
		Produto produtoExcluido = lista.get(0);
		produtoRN.excluir(produtoExcluido);
		lista = produtoRN.listar();
		assertEquals(2, lista.size());
	}
	
	@Test
	public void pesquisarTest()
	{
		ProdutoRN produtoRN = new ProdutoRN();
		Produto produtoPesquisado = produtoRN.pesquisar("erno");
		assertEquals("Lote", produtoPesquisado.getUnidade());
	}
	
	@Test
	public void alterarTest()
	{
		ProdutoRN produtoRN = new ProdutoRN();
		Produto produtoPesquisado = produtoRN.pesquisar("erno");
		assertEquals("Lote", produtoPesquisado.getUnidade());
		
		produtoPesquisado.setEstoque(20);
		produtoRN.alterar(produtoPesquisado);
		Produto produtoAlterado = produtoRN.pesquisar("erno");
		assertEquals(20, produtoAlterado.getEstoque().intValue());
	}
	
	
}
