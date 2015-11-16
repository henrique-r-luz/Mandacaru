package controle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.xml.parsers.ParserConfigurationException;

import org.primefaces.component.dialog.Dialog;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.xml.sax.SAXException;

import com.sun.faces.context.flash.ELFlash;

import java.io.InputStream;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import dao.DAO;
import dao.DAOatualizaPedidoStatus;
import dao.DAOqueryOrcamento;

import dao.DAOinsereOrcamento;

import model.Ccotacao;

import model.Citem;
import model.Corcamento;
import model.Cpedidos;
import seguranca.Md5;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class OrcamentoControl implements Serializable {

	private Corcamento orcamento = new Corcamento();
	private Cpedidos pedidos;
	private List<Citem> listItem;
	private List<Corcamento> listOrcamento;
	private CotacaoControl cotacaoControl = new CotacaoControl();
	private UploadedFile file;
	private String imagem;
	private String nomeImg;
	private String nomeBd = "";
	private String visiblePanel = "false";
	private File fileCaminho;
	private StreamedContent files;
	private Config config = new Config();
	private StatusControl statusControl = new StatusControl();
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CotacaoControl getCotacaoControl() {
		return cotacaoControl;
	}

	public void setCotacaoControl(CotacaoControl cotacaoControl) {
		this.cotacaoControl = cotacaoControl;
	}

	public String getVisiblePanel() {
		return visiblePanel;
	}

	public void setVisiblePanel(String visiblePanel) {
		this.visiblePanel = visiblePanel;
	}

	public StreamedContent getFiles() {
		return files;
	}

	public void setFiles(StreamedContent files) {
		this.files = files;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;

	}

	public Cpedidos getPedidos() {
		return pedidos;
	}

	public void setPedidos(Cpedidos pedidos) {
		this.pedidos = pedidos;
	}

	public List<Corcamento> getListOrcamento() {
		return listOrcamento;
	}

	public void setListOrcamento(List<Corcamento> listOrcamento) {
		this.listOrcamento = listOrcamento;
	}

	public List<Citem> getListItem() {
		return listItem;
	}

	public void setListItem(List<Citem> listItem) {
		this.listItem = listItem;
	}

	public Corcamento getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(Corcamento orcamento) {
		this.orcamento = orcamento;
	}

	public void salva(List<Citem> listItens) {
		new DAOinsereOrcamento().insereOrcamento(listItens);
	}

	public OrcamentoControl() {
		inicia();
	}

	public void inicia() {
		if (ELFlash.getFlash().get("id") == null) {

			id = -1;
		} else {

			id = (Integer) ELFlash.getFlash().get("id");
		}
		if (id == -1) {
		/*	HttpServletResponse response = (HttpServletResponse) FacesContext
					.getCurrentInstance().getExternalContext().getResponse();
			try {
				response.sendRedirect("/index.jsf");
				FacesContext.getCurrentInstance().responseComplete();
			} catch (Exception e) {
				e.printStackTrace();
			}*/
			/*
			 * try { FacesContext .getCurrentInstance() .getExternalContext()
			 * .dispatch( FacesContext.getCurrentInstance()
			 * .getExternalContext() .getRequestContextPath() + "/index.jsf"); }
			 * catch (IOException e) { // TODO Auto-generated catch block
			 * e.printStackTrace(); }
			 */
		} else {
			if (pedidos == null) {

				pedidos = new DAO<Cpedidos>(Cpedidos.class).buscaPorId(id);
				pedidos.setOrcamento(new DAOqueryOrcamento().orcamentoPedidos(id));
				listOrcamento = pedidos.getOrcamento();
			}

		}

	}

	public String voltaPagina() {
		return "/pedidos/cria-lista-pedidos?faces-redirect=true";
	}

	public void salvaCotacao() {

		if (nomeBd.equals("")) {
			FacesContext.getCurrentInstance().addMessage(
					"salvar",
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Necessário haver 1 orçamento", null));

		} else {
			cotacaoControl.salvaCotacao(nomeBd, orcamento);

			limpaImg();
			clear();
		}

	}

	private void clear() {
		cotacaoControl.clear();
		// cotacao1 = new Ccotacao();
	}

	public void selectOrcamento(Corcamento orcamento) {
		// clearPop();
		this.orcamento = orcamento;
		cotacaoControl.setIdOrcamento(orcamento.getId());

	}

	public void doUpload(FileUploadEvent event) throws IOException {
		file = event.getFile();
		salvarImg();

	}

	public void salvarImg() {

		FacesContext ctx = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage();
		URL fileUrl = null;
		String str = null;

		String nomeArquivo = file.getFileName(); // Nome do arquivo enviado
		byte[] conteudo = file.getContents(); // Conteudo a ser gravado no

		nomeBd = new GeraNomeImg(".jpg").getNome("");
		nomeImg = "/" + config.getFileImageOrcamento() + "/" + nomeBd;
		imagem = config.getUrlBase()
				+ FacesContext.getCurrentInstance().getExternalContext()
						.getRequestContextPath() + "/"
				+ config.getFileImageOrcamento() + "/" + nomeBd;
		fileCaminho = new File(config.getUrl() + config.getFileImageOrcamento()
				+ "\\" + nomeBd);

		try {

			FileOutputStream fos = new FileOutputStream(fileCaminho);
			fos.write(conteudo);
			fos.flush();
			fos.close();
			visiblePanel = "true";
			// verOrcamento();

		} catch (IOException ex) {

			msg.setSummary(ex.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);

		} finally {
			ctx.addMessage("mensagens", msg);

		}

	}

	public void limpaImg() {

		nomeImg = "";
		nomeBd = "";
		imagem = "";
		file = null;
		visiblePanel = "false";
		// cotacao1.setData(null);

	}

	public void removeImg() {
		File f = new File(config.getUrl() + config.getFileImageOrcamento()
				+ "\\" + nomeBd);
		f.delete();
	}

	public void limparPop(CloseEvent event) {
		clearPop();
	}

	public void clearPop() {
		removeImg();
		limpaImg();
		clear();
	}

	// função que envia os pedidos para abrir processo.
	
	public String enviaPedido() {

		List<Citem> auxItem = new ArrayList<Citem>();
		for (Corcamento auxOrcamento : listOrcamento) {

			if(cotacaoControl.verificaCotacao(auxOrcamento)){
				auxItem.add(auxOrcamento.getItem());
			}else{
				
				FacesContext.getCurrentInstance().addMessage(
						"salvar",
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								"Necessário cadastrar todos os orçamentos", null));
				return "";
			}
		}
		statusControl.addStatusProcesso(auxItem, pedidos);

		// new DAOatualizaPedidoStatus().atualizaEstatusDiretoria(pedidos);
		ELFlash.getFlash().put("msg", "1");
		

		return "/pedidos/criar-pedido?faces-redirect=true";
	}

	public void verOrcamento() {
		InputStream stream = null;
		try {
			stream = new FileInputStream(new File(new Config().getUrl()
					+ nomeImg));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		files = new DefaultStreamedContent(stream, "image/jpg", nomeBd);
	}

}
