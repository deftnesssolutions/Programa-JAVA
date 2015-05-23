package Bean;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.pdf.codec.Base64.InputStream;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import modelo.Cliente;
import modelo.RelatorioVenda;
import modelo.Venda;
import RN.ClienteRN;
import RN.VendaRN;
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
	
	public void geraRelatio() throws JRException, IOException 
	{
		VendaRN vendaRN = new VendaRN();
		List<Venda> vendaAll = vendaRN.listar();
		 ArrayList<RelatorioVenda> vendasReportModel = new ArrayList<RelatorioVenda>();
		for (Venda venda : vendaAll) {
			 RelatorioVenda vendaPorCliente = new RelatorioVenda();
			 if(venda.getCliente().getId()==this.clienteSelecionado.getId())
			 {
				 vendaPorCliente.setCliente_nome(this.clienteSelecionado.getNome());
				 vendaPorCliente.setProduto_descricao(venda.getProduto().getDescricao());
				 vendaPorCliente.setProduto_valor(venda.getProduto().getValor());
				 vendaPorCliente.setVenda_data_venda(venda.getDataVenda());
				 vendasReportModel.add(vendaPorCliente);
			 }
		}

		FacesContext fc = FacesContext.getCurrentInstance();
        ServletContext context = (ServletContext) fc.getExternalContext().getContext();
        File relatorioJasper = new File(context.getRealPath("/WEB-INF/Relatorios/frmVendas.jasper"));
		Map<String,Object> parametros = new HashMap<String,Object>();
        parametros.put("idCliente", this.clienteSelecionado.getId().toString());
        JasperPrint jasperPrint = JasperFillManager.fillReport(relatorioJasper.getPath(), parametros,new JRBeanCollectionDataSource(vendasReportModel));
        HttpServletResponse res = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();  
        res.addHeader("Content-disposition", "inline;filename=frmVendas.pdf"); 
        ServletOutputStream stream = res.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        FacesContext.getCurrentInstance().responseComplete(); 
        stream.flush();
        stream.close();
		//return null;
	}
	
	
	
	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}
}
