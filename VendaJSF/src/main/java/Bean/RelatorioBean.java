package Bean;

import java.util.ArrayList;
//import java.util.HashMap;
import java.util.List;
//import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

//import net.sf.jasperreports.engine.JasperCompileManager;
//import net.sf.jasperreports.engine.JasperExportManager;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperReport;
//import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import modelo.Cliente;
//import modelo.Venda;
import RN.ClienteRN;
//import RN.VendaRN;

@ManagedBean(name="relatorioBean")
@ViewScoped
public class RelatorioBean {
	private Cliente clienteSelecionado;
	private List<SelectItem> cboClientes;
	
	public List<SelectItem> getCboClientes()
	{
			
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
	
	public String geraRelatio() 
	{
		/*
		VendaRN vendaRN = new VendaRN();
		List<Venda> vendas = vendaRN.listar();
		@SuppressWarnings("rawtypes")
		Map params = new HashMap();
		params.put("idCliente", this.clienteSelecionado);
		try {
			JasperReport pathjrxml = JasperCompileManager.compileReport("Relatorios/frmVendas.jrxml");
			JasperPrint printReport = JasperFillManager.fillReport(pathjrxml, params, new
										JRBeanCollectionDataSource(vendas));
			JasperExportManager.exportReportToPdfFile(printReport, "Relatorios/Vendas.pdf");
		} catch( Throwable t ) {
		      System.out.println( t.getMessage() );
		    }
		*/
		return null;
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}
}
