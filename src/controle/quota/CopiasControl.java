package controle.quota;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;

import controle.Config;
import dao.DAO;
import dao.DAOqueryIservidor;
import dao.quota.DAOqueryCopias;
import model.impressao.Iservidor;
import model.quota.QcopiasRealizadas;
import model.quota.Qimpressora;

@ManagedBean
@ViewScoped
public class CopiasControl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Iservidor servidor;
	private Qimpressora impressora;
	private Date dataInicial;
	private Date dataFinal;
	private String nomeDocumento = "";
	public QcopiasRealizadas copias = new QcopiasRealizadas();
	@SuppressWarnings("unused")
	private List<QcopiasRealizadas> listaTodos;
	private List<QcopiasRealizadas> listaTodosAbatidos;
	private int totalCopias = (int) new DAOqueryCopias().somaCopias();
	private int totalCopiasAbatidos = 0;

	public CopiasControl() {
		filtrar();
	}

	public String getNomeDocumento() {
		return nomeDocumento;
	}

	public void setNomeDocumento(String nomeDocumento) {
		this.nomeDocumento = nomeDocumento;
	}

	public List<QcopiasRealizadas> getListaTodos() {
		return listaTodos;
	}

	public void setListaTodos(List<QcopiasRealizadas> listaTodos) {
		this.listaTodos = listaTodos;
	}

	public Iservidor getServidor() {
		return servidor;
	}

	public void setServidor(Iservidor servidor) {
		this.servidor = servidor;
	}

	public Qimpressora getImpressora() {
		return impressora;
	}

	public void setImpressora(Qimpressora impressora) {
		this.impressora = impressora;
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

	public QcopiasRealizadas getCopias() {
		return copias;
	}

	public void setCopias(QcopiasRealizadas copias) {
		this.copias = copias;
	}

	public List<QcopiasRealizadas> getListaUsuario() {
		return new DAOqueryCopias().copiasUsuario();
	}

	public long getTotalCopiasUsuario() {
		return new DAOqueryCopias().somaCopiasUsuario();
	}

	// public long getTotalCopias() {
	// return new DAOqueryCopias().somaCopias();
	// }

	public String returnDoc(String str) {
		Config conf = new Config();

		return conf.getUrlBase() + "/docImpressao/" + str + ".pdf";
	}

	public int getTotalCopias() {

		return totalCopias;
	}

	public void setTotalCopias(int totalCopias) {
		this.totalCopias = totalCopias;
	}

	public void pesquisaPadrao() {

	}

	public void filtrar() {

		// if (servidor != null && impressora != null && dataInicial != null
		// && dataFinal != null) {
		
		if (dataFinal != null && dataInicial != null) {
			if (dataFinal.compareTo(dataInicial) < 0) {
				
			
				
				FacesContext.getCurrentInstance().addMessage(
						"salvar",
						new FacesMessage(FacesMessage.SEVERITY_WARN, Label.getDataFinal(), null));
			}
		}
		
		setListaTodos(new DAOqueryCopias().filtroRelatorio(servidor,
				impressora, dataInicial, dataFinal, nomeDocumento));
		setTotalCopias((int) new DAOqueryCopias().contaCopias(servidor,
				impressora, dataInicial, dataFinal, nomeDocumento));
		

		/*
		 * } else { FacesContext .getCurrentInstance() .addMessage( "salvar",
		 * new FacesMessage( FacesMessage.SEVERITY_WARN,
		 * "Preecha todos os campos do filtro", null)); }
		 */
	}

	public void funcaoFiltraQuota() {
		// servidor = new
		// DAOqueryIservidor().pesquisaServidorLogin(SecurityContextHolder.getContext().getAuthentication().getName());
		filtrar();

	}

	public List<QcopiasRealizadas> getFiltraQuotaServidor() {

		servidor = new DAOqueryIservidor()
				.pesquisaServidorLogin(SecurityContextHolder.getContext()
						.getAuthentication().getName());
		filtrar();
		return listaTodos;
	}

	public List<QcopiasRealizadas> getFiltraQuota() {
		funcaoFiltraQuota();
		return listaTodos;
	}

	public int getFiltraTotalCopias() {
		funcaoFiltraQuota();
		return totalCopias;
	}

	public int getFiltraTotalCopiasServidor() {
		servidor = new DAOqueryIservidor()
				.pesquisaServidorLogin(SecurityContextHolder.getContext()
						.getAuthentication().getName());
		filtrar();
		return totalCopias;
	}

	public List<QcopiasRealizadas> getFiltraAbatido() {
		listaTodosAbatidos = new DAOqueryCopias().filtroRelatorioAbatido(
				servidor, impressora, dataInicial, dataFinal, "");
		return listaTodosAbatidos;
	}

	public int getFiltraTotalCopiasAbatido() {
		// funcaoFiltraQuota();
		totalCopiasAbatidos = (int) new DAOqueryCopias().contaCopiasAbatida(
				servidor, impressora, dataInicial, dataFinal, "");
		// funcaoFiltraQuota();
		return totalCopiasAbatidos;
	}

}
