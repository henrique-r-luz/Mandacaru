package controle;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Logger;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.mysql.jdbc.Connection;

import dao.JPAUtil;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ImprimeRelatorio {

	private java.sql.Connection conexao;
	private String saida;

	public String imprime(String url,String nome, HashMap<String, Object> parametreMap) {
		try {
			criaConexao();
			

			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.responseComplete();
			ServletContext scontext = (ServletContext) facesContext
					.getExternalContext().getContext();
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(scontext
					.getRealPath(url),
					parametreMap, conexao);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
			JRPdfExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
			exporter.exportReport();
			byte[] bytes = baos.toByteArray();
			if (bytes != null && bytes.length > 0) {
				HttpServletResponse response = (HttpServletResponse) facesContext
						.getExternalContext().getResponse();
				response.setContentType("application/pdf");
				response.setHeader("Pragma", "no-cache");  
				response.setHeader("Cache-control", "private");  
				response.setDateHeader("Expires", 0);  
				response.setContentType("application/pdf");  
				response.setHeader("Content-Disposition", "attachment; filename=\""+nome+".pdf\"");  
				response.setContentLength(bytes.length);
				ServletOutputStream outputStream = response.getOutputStream();
				outputStream.write(bytes, 0, bytes.length);
				outputStream.flush();
				outputStream.close();
				FacesContext.getCurrentInstance().responseComplete();
			//	JasperViewer.viewReport(jasperPrint, false);  
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			 try {
				conexao.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		 return null;
	}
	
	
		
	

	public void criaConexao() throws ClassNotFoundException, SQLException {
		String endereco = "localhost";
		String porta = "3306";
		String banco = "compras2";
		String usuario = "root";
		String senha = "jfHg#15s";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexao = DriverManager.getConnection("jdbc:mysql://" + endereco
					+ ":" + porta + "/" + banco + "?user=" + usuario
					+ "&password=" + senha);
		} catch (ClassNotFoundException ex) {
			throw ex;
		} catch (SQLException ex) {
			throw ex;
		}
	}

	public java.sql.Connection getConexao() {
		return conexao;
	}
	
	
	
	
}
