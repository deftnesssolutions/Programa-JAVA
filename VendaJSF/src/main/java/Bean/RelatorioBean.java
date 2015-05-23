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

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import modelo.Cliente;
import RN.ClienteRN;
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
	
	
	@SuppressWarnings("unchecked")
	public String geraRelatio()throws IOException 
	{
		/*
		//VendaRN vendaRN = new VendaRN();
		//List<Venda> vendaAll = vendaRN.listar();
		//ArrayList<Venda> vendasPorCliente = new ArrayList<Venda>();
		 try {  
	            System.out.println("entrou no visualizar relatorio");  
	            if (this.clienteSelecionado == null) {  
	                return null;  
	            }  
	            //---------- gera o relatorio ----------  
	            @SuppressWarnings("rawtypes")
				Map parametros = new HashMap();  
	            parametros.put("idCliente", this.clienteSelecionado.getId());  
	            Collection<String> c = new ArrayList<String>();    
	            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(c);
	            JasperPrint jasperPrint = JasperFillManager.fillReport(getClass().getResourceAsStream("/Relatorios/frmVendas.jasper"), parametros, dataSource);  
	            byte[] b = JasperExportManager.exportReportToPdf(jasperPrint);   	  
	            HttpServletResponse res = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();  
	            res.setContentType("application/pdf");  
	            //Código abaixo gerar o relatório e disponibiliza diretamente na página   
	            res.setHeader("Content-disposition", "inline;filename=frmVendas.pdf");  
	            //Código abaixo gerar o relatório e disponibiliza para o cliente baixar ou salvar   
	            //res.setHeader("Content-disposition", "attachment;filename=arquivo.pdf");  
	            res.getOutputStream().write(b);  
	            res.getCharacterEncoding();  
	            FacesContext.getCurrentInstance().responseComplete();  
	            System.out.println("saiu do visualizar relatorio");  
	        } catch (Exception ex) {  
	            ex.printStackTrace();  
	        }  
		*/
		FacesContext fc = FacesContext.getCurrentInstance();
        ServletContext context = (ServletContext) fc.getExternalContext().getContext();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        File relatorioJasper = new File(context.getRealPath("/WEB-INF/Relatorios/frmVendas.jasper"));
 
        // parâmetros, se houver
        @SuppressWarnings("rawtypes")
		Map parametros = new HashMap();
        parametros.put("idCliente", this.clienteSelecionado.getId()); 
        byte[] bytes = null;
 
        try {
 
            bytes = JasperRunManager.runReportToPdf(relatorioJasper.getPath(), parametros);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        if (bytes != null && bytes.length > 0) {
            // Envia o relatório em formato PDF para o browser
            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream ouputStream = response.getOutputStream();
            ouputStream.write(bytes, 0, bytes.length);
            ouputStream.flush();
            ouputStream.close();
        }
		return null;
	}
	
	
	
	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}
}
