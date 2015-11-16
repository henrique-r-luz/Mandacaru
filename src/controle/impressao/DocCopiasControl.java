package controle.impressao;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import controle.ImprimeRelatorio;

import dao.DAO;
import dao.DAOinsereDocCopias;
import dao.DAOqueryDocCopias;

import model.impressao.IdocCopias;
import model.impressao.Iservidor;

@ManagedBean
@ViewScoped
public class DocCopiasControl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IdocCopias copias = new IdocCopias();
	private int idGlpi = 0;
	private List<IdocCopias> filteredCopias;
	private Iservidor servidor = new Iservidor();
	private String nomeServidor = "";
	private Date dataInicial;
	private Date dataFinal;
	private ContaCopias contaCopias;

	public ContaCopias getContaCopias() {
		return contaCopias;
	}

	public void setContaCopias(ContaCopias contaCopias) {
		this.contaCopias = contaCopias;
	}

	public String getNomeServidor() {
		return nomeServidor;
	}

	public void setNomeServidor(String nomeServidor) {
		this.nomeServidor = nomeServidor;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public List<IdocCopias> getFilteredCopias() {
		return filteredCopias;
	}

	public void setFilteredCopias(List<IdocCopias> filteredCopias) {
		this.filteredCopias = filteredCopias;
	}

	public IdocCopias getCopias() {
		return copias;
	}

	public void setCopias(IdocCopias copias) {
		this.copias = copias;
		idGlpi = copias.getIdGlpi();
		servidor = copias.getServidor();
	}

	public void salva() {

		if (new DAOqueryDocCopias().verificaIdGlpi(copias.getIdGlpi())) {
			new DAOinsereDocCopias().adiciona(copias, copias.getServidor());
			clear();
		} else {
			FacesContext.getCurrentInstance().addMessage(
					"salvar",
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"idGlpi já existe", null));
		}
		// new DAOinsereDocCopias().adiciona(copias, servidor);
	}

	public void clear() {

		copias = new IdocCopias();
	}

	public void altera() {
		if (idGlpi == copias.getIdGlpi()) {
			new DAOinsereDocCopias().altera(copias, copias.getServidor());
			clear();
		} else {
			if (new DAOqueryDocCopias().verificaIdGlpi(copias.getIdGlpi())) {
				new DAOinsereDocCopias().altera(copias, servidor);
				clear();
			} else {
				FacesContext.getCurrentInstance().addMessage(
						"salvar",
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								"idGlpi já existe", null));
			}
		}
	}

	public void verificaData() {
		if (dataInicial.getTime() > dataFinal.getTime()) {
			FacesContext
					.getCurrentInstance()
					.addMessage(
							"salvar",
							new FacesMessage(
									FacesMessage.SEVERITY_WARN,
									"Data inicial não pode ser maior que a final",
									null));
			// return new List<IdocCopias>();
		}
	}

	public void impressao() {
		if(dataInicial!=null && dataFinal!=null){
			HashMap<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("dataInicial", convertData_br(dataInicial));
			parameterMap.put("dataFinal",  convertData_br(dataFinal));
			parameterMap.put("dataInicial_tb", convertData_sql(dataInicial));
			parameterMap.put("dataFinal_tb",  convertData_sql(dataFinal));
			parameterMap.put("soma_tb",  contaCopias.getValor());
			
		new ImprimeRelatorio().imprime("/relatorio/copias_solicitante.jasper","ralatorioSolicitente",parameterMap);
		}else{
			FacesContext
			.getCurrentInstance()
			.addMessage(
					"salvar",
					new FacesMessage(
							FacesMessage.SEVERITY_WARN,
							"Data inicial e final  não podem ser vazios",
							null));	
		}
		// return new ImprimeRelatorio().geraRelatorioPassandoConexao();
	}

	public void impressaoMes() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.responseComplete();
		@SuppressWarnings("unused")
		ServletContext scontext = (ServletContext) facesContext
				.getExternalContext().getContext();
		if(dataInicial!=null && dataFinal!=null){
			HashMap<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("dataInicial", convertData_br(dataInicial));
			parameterMap.put("dataFinal",  convertData_br(dataFinal));
			parameterMap.put("dataInicial_tb", convertData_sql(dataInicial));
			parameterMap.put("dataFinal_tb",  convertData_sql(dataFinal));
			parameterMap.put("soma_tb",  contaCopias.getValor());
		//	parameterMap.put("logo",  scontext.getRealPath("/relatorio.logo.jpg"));
			
		new ImprimeRelatorio().imprime("/relatorio/copias_mes.jasper","ralatorioMes",parameterMap);
		}else{
			FacesContext
			.getCurrentInstance()
			.addMessage(
					"salvar",
					new FacesMessage(
							FacesMessage.SEVERITY_WARN,
							"Data inicial e final  não podem ser vazios",
							null));	
		}
		// return new ImprimeRelatorio().geraRelatorioPassandoConexao();
	}
	
	public List<IdocCopias> getListaTodos() {
		contaCopias = new ContaCopias(new DAOqueryDocCopias().listaTodos(
				nomeServidor, dataInicial, dataFinal));

		return contaCopias.getListaTodos();

	}
	
	
	public String convertData_br(Date data){
		return     new SimpleDateFormat("dd/MM/yyyy").format(data.getTime());  
	}
	
    public String convertData_sql(Date data){
    	return     new SimpleDateFormat("yyyy-MM-dd").format(data.getTime()); 
	}

	
	 
	

	// public int getTotalCopias(){

	// }

}
