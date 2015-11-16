package controle.quota;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import model.quota.QcopiasRealizadas;
import model.quota.QgrupoImpressao;
import model.quota.Qimpressora;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import controle.Config;
import controle.GeraNomeImg;
import controle.UsuarioControl;
import dao.quota.DAOatualizaQuota;
import dao.quota.DAOinsereCopias;
import dao.quota.DAOqueryGrupoImpressao;
import dao.quota.RelatorioCopias;

//import controle;

@ManagedBean
@ViewScoped
public class Quota implements Serializable {

	/**
	 * 
	 */

	private UploadedFile file;
	private QcopiasRealizadas copias = new QcopiasRealizadas();
	private Config config = new Config();
	private String nomeBd = "";
	private String tipo = "";
	private static final long serialVersionUID = 1L;
	private RuncomdLinux conexCups;
	private String verificaNumeroPaginas = "0";
	private List<Qimpressora> impressora = new ArrayList<Qimpressora>();
	private QgrupoImpressao grupoImpressao = new QgrupoImpressao();
	private String usuario = new UsuarioControl().getSecao();
	private int loopCopias = 1;
	private int copiasImpressao = 0;
	private String popImprime = "";
	private Qimpressora cotaImpressora = new Qimpressora();
	private int pagina = 0;
	private boolean paisagem = false;

	/*
	 * public void imprime(){
	 * 
	 * 
	 * try { //Carregando o PDF // PdfReader pdfReader = new PdfReader( //
	 * "D://disco E//compras2//sistema//WebContent//quota//edital_processo_selecao_mestrado_modelagem_computacional_sistemas_02_2014.pdf"
	 * ); // System.out.println("iprimirrrrrrrrrrrrrrrrrrrr>>>"+pdfReader.
	 * getNumberOfPages ()); }catch(IOException e){ e.printStackTrace(); }
	 * 
	 * }
	 */

	public Quota() {
		conexCups = new RuncomdLinux();
		List<Object[]> aux = new DAOqueryGrupoImpressao().ListagrupoUsuario(usuario);
		for (Object[] obj : aux) {
			grupoImpressao = (QgrupoImpressao) obj[0];
			impressora.add((Qimpressora) obj[1]);

		}

		if (!impressora.isEmpty())
			cotaImpressora = impressora.get(0);

		// System.out.println(" grupoImpressao ");
	}

	public String getNomeBd() {
		return nomeBd;
	}

	public void setNomeBd(String nomeBd) {
		this.nomeBd = nomeBd;
	}

	public boolean isPaisagem() {
		return paisagem;
	}

	public void setPaisagem(boolean paisagem) {
		this.paisagem = paisagem;
	}

	public Qimpressora getCotaImpressora() {
		return cotaImpressora;
	}

	public void setCotaImpressora(Qimpressora cotaImpressora) {
		this.cotaImpressora = cotaImpressora;
	}

	public String getPopImprime() {
		return popImprime;
	}

	public void setPopImprime(String popImprime) {
		this.popImprime = popImprime;
	}

	public String getCopiasImpressao() {

		return Integer.toString(copiasImpressao);
	}

	public void setCopiasImpressao(int copiasImpressao) {
		this.copiasImpressao = copiasImpressao;
	}

	public int getLoopCopias() {
		return loopCopias;
	}

	public void setLoopCopias(int loopCopias) {
		this.loopCopias = loopCopias;
	}

	public List<Qimpressora> getImpressora() {
		return impressora;
	}

	public void setImpressora(List<Qimpressora> impressora) {
		this.impressora = impressora;
	}

	public QgrupoImpressao getGrupoImpressao() {
		return grupoImpressao;
	}

	public void setGrupoImpressao(QgrupoImpressao grupoImpressao) {
		this.grupoImpressao = grupoImpressao;
	}

	public void testeNovo() {
		RuncomdLinux obj = new RuncomdLinux();
		obj.executecomando("h");
	}

	public void doUpload(FileUploadEvent event) throws IOException {

		file = event.getFile();
		if (verificaFormato(file)) {
			if (verificaGrupo()) {

				if (!new DAOqueryGrupoImpressao().retornaImpressoraGrupo(grupoImpressao.getId()).isEmpty()) {

					// this.popImprime = "pop.show();";

					salvarDoc();
					int quotaRestante = getQuotaRestante();
					this.setCopiasImpressao(quotaRestante);
				} else {

					this.popImprime = "";
					cancela();
					FacesContext.getCurrentInstance().addMessage("salvar",
							new FacesMessage(FacesMessage.SEVERITY_WARN, Label.getNaoPossuiGrupo(), null));

				}

			} else {
				this.popImprime = "";
				cancela();
				FacesContext.getCurrentInstance().addMessage("salvar",
						new FacesMessage(FacesMessage.SEVERITY_WARN, Label.getServidorGrupo(), null));
			}
		} else {

			this.popImprime = "";
			cancela();
			FacesContext.getCurrentInstance().addMessage("salvar",
					new FacesMessage(FacesMessage.SEVERITY_WARN, Label.getFormatoArquivo(), null));

		}

	}

	public int getQuotaRestante() {
		if (grupoImpressao.getDataAtualizacao() != null) {
			// return grupoImpressao.getQuantidade() - verificaCota();
			return grupoImpressao.getQuantidade() - verificaCota();

		} else {

			return 0;
		}

	}

	public boolean verificaFormato(UploadedFile file) {
		String[] exts = file.getFileName().split("\\.");
		String tipo = "." + exts[exts.length - 1].toLowerCase();
		if (tipo.equals(".doc") || tipo.equals(".docx") || tipo.equals(".odt") || tipo.equals(".xls")
				|| tipo.equals(".xlsx") || tipo.equals(".ods") || tipo.equals(".pdf") || tipo.equals(".png")
				|| tipo.equals(".jpg") || tipo.equals(".ott"))
			return true;
		else
			return false;

	}

	/*
	 * public void InsereDadoscopias(){ copias.setUrlDocumento(nomeBd); //
	 * copias.setData(new Date());
	 * 
	 * }
	 */

	public String getVerificaNumeroPaginas() {
		if (verificaNumeroPaginas.equals("1-1")) {
			return "1";
		} else {
			return verificaNumeroPaginas;
		}
	}

	public void setVerificaNumeroPaginas(String verificaNumeroPaginas) {

		// escreva aqui os codigos para tratar a string de paginas
		this.verificaNumeroPaginas = verificaNumeroPaginas;
	}

	public QcopiasRealizadas getCopias() {
		return copias;
	}

	public void setCopias(QcopiasRealizadas copias) {
		this.copias = copias;
	}

	public void salvarDoc() {

		FacesContext ctx = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage();
		// String[] tipo = new
		copias.setNomeDocumento(file.getFileName());
		String[] exts = file.getFileName().split("\\.");

		byte[] conteudo = file.getContents(); // Conteudo a ser gravado no
		tipo = "." + exts[exts.length - 1].toLowerCase();
		nomeBd = new GeraNomeImg().getNomeDoc("");

		File fileCaminho = new File(config.getUrl() + config.getDocImpressao() + nomeBd + tipo);

		salvaArquivo(fileCaminho, conteudo, msg, ctx, tipo);

	}

	public void salvaArquivo(File fileCaminho, byte[] conteudo, FacesMessage msg, FacesContext ctx, String ext) {
		try {

			FileOutputStream fos = new FileOutputStream(fileCaminho);
			fos.write(conteudo);
			fos.flush();
			fos.close();
			File file;

			//
			file = new File(config.getUrl() + config.getDocImpressao() + nomeBd + tipo);

			if (tipo != ".pdf") {

				while (!file.exists()) {
					// Aguarde 5 segundos

					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					file = new File(config.getUrl() + config.getDocImpressao() + nomeBd + tipo);
				}
				if (!ext.equals(".pdf")) {
					// verificar se o arquivo foi realmente convertido
					conexCups.convertArq(config.getUrl() + config.getDocImpressao() + nomeBd + tipo);

					// file.delete();
					// nomeBd = new GeraNomeImg(".pdf").geraNamePdf(nomeBd);
				}
				file = new File(config.getUrl() + config.getDocImpressao() + nomeBd + ".pdf");

				while (!file.exists()) {
					// Aguarde 5 segundos

					try {

						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					if (file.exists()) {
						File fileExcluir = new File(config.getUrl() + config.getDocImpressao() + nomeBd + tipo);
						// if (tipo != ".pdf") {
						// fileExcluir.delete();
					}
				}
				if (config.getConf().isProducao()) {
					int cont = 0;
					while (cont == 0) {
						// Aguarde 5 segundos

						try {

							Thread.sleep(10000);
							cont++;
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

				this.popImprime = "pop.show();";
				this.setVerificaNumeroPaginas("1-" + this.getNumeroPaginas());
			}
			// verOrcamento();

		} catch (IOException ex) {
			ex.getStackTrace();
			msg.setSummary(ex.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);

		} finally {
			ctx.addMessage("mensagens", msg);

		}
	}

	public int getNumeroPaginas() {

		PdfReader pdfReader;
		try {
			File file = new File(config.getUrl() + config.getDocImpressao() + nomeBd + ".pdf");
			if (file.exists()) {
				pdfReader = new PdfReader(config.getUrl() + config.getDocImpressao() + nomeBd + ".pdf");
				return pdfReader.getNumberOfPages();
			} else {
				return 0;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}

	}

	public void cancela() {
		file = null;
		copias = new QcopiasRealizadas();
		// cotaImpressora = new Qimpressora();
		// controlePop = 0;
		this.setLoopCopias(1);
		pagina = 0;
		// this.setVerificaNumeroPaginas("1-1");
	}

	public void cancelaPopImprime() {
		File file = new File(config.getUrl() + config.getDocImpressao() + nomeBd + ".pdf");
		file.delete();
		cancela();

	}

	public void imprimir() {

		if (verificaImpressoraRede(cotaImpressora.getIp())) {
			// if (true) {
			if (verificaPeridoquota()) {
				if (testaPaginas()) {

					if (loopCopias != 0) {
						if (!verificaNumeroPaginas.isEmpty()) {
							if (!nomeBd.equals("")) {
								// System.out.println("verificaCota()>>>"+verificaCota());
								// System.out.println("pagina>>"+pagina);
								if (grupoImpressao.getQuantidade() >= (verificaCota()
										+ calculaCopias(this.getLoopCopias(), pagina))) {
									copias.setImpressora(cotaImpressora);
									if (conexCups.executeImpressao(
											config.getUrl() + config.getDocImpressao() + nomeBd + ".pdf",
											this.getLoopCopias(), this.getVerificaNumeroPaginas(),
											copias.getImpressora().getNome(), nomeBd, paisagem)) {

										salvaCopias(nomeBd, calculaCopias(this.getLoopCopias(), pagina));
										cancela();
										nomeBd = "";
										this.popImprime = "pop.hide();";
									} else {
										FacesContext.getCurrentInstance().addMessage("salvar", new FacesMessage(
												FacesMessage.SEVERITY_WARN, Label.getNaoCupsImpressora(), null));
									}
								} else {

									FacesContext.getCurrentInstance().addMessage("salvar",
											new FacesMessage(FacesMessage.SEVERITY_WARN, Label.getNaoQuota(), null));
								}
							}
						} else {
							FacesContext.getCurrentInstance().addMessage("salvar",
									new FacesMessage(FacesMessage.SEVERITY_WARN, Label.getDefinirPaginas(), null));
						}
					} else {
						FacesContext.getCurrentInstance().addMessage("salvar",
								new FacesMessage(FacesMessage.SEVERITY_WARN, Label.getNumeroPagina(), null));
					}

				}
			} else {
				FacesContext.getCurrentInstance().addMessage("salvar",
						new FacesMessage(FacesMessage.SEVERITY_WARN, Label.getValidadeQuota(), null));
			}
		} else {
			FacesContext.getCurrentInstance().addMessage("salvar",
					new FacesMessage(FacesMessage.SEVERITY_WARN, Label.getDesligada(), null));
		}

	}

	public String getPdf() {
		return config.getUrlBase() + "/docImpressao/" + nomeBd + ".pdf";
	}

	public boolean verificaPeridoquota() {
		// Date dataFinal = new Date();

		Date dataNow = ConfiguracaoQuota.dataNow();

		Date dataFinal = ConfiguracaoQuota.ajustaData(grupoImpressao);

		dataFinal.setDate(dataFinal.getDate() + grupoImpressao.getValidadeEmDias());

		if (dataNow.compareTo(dataFinal) > 0) {
			return false;
		} else {
			return true;
		}

	}

	public boolean verificaImpressoraRede(String impressora) {
		String str = conexCups.verificaImpressora(impressora);
		String[] result = str.split("ttl=");
		if (result.length == 1) {
			return false;
		} else {
			return true;
		}
	}

	public int verificaCota() {
		return new RelatorioCopias().verifCopias(usuario, grupoImpressao);
	}

	public int calculaCopias(int loopCopias, int paginas) {

		return loopCopias * paginas;

	}

	public boolean verificaGrupo() {
		return new DAOqueryGrupoImpressao().verificaGrupo(usuario);
	}

	public void salvaCopias(String nomeDoc, int numeroTotalCopias) {
		// modelo de dialog
		// oncomplete="if (#{meuController.teste == true}) dialog.hide()"
		copias.setUrlDocumento(nomeDoc);
		copias.setData(new Date());
		copias.setNumeroCopias(numeroTotalCopias);
		new DAOinsereCopias().salva(copias, usuario, this);

	}

	public boolean testaPaginas() {
		pagina = 0;
		int cont = 0;
		String auxS = "";
		List<String> listaVerificacao = new ArrayList<String>();
		for (int i = 0; i < verificaNumeroPaginas.length(); i++) {
			char aux1 = verificaNumeroPaginas.charAt(i);
			if (!(aux1 == ' ')) {
				auxS = auxS + Character.toString(aux1);
			}
		}
		if (auxS.matches("^(\\s*\\d+\\s*\\-\\s*\\d+\\s*,?|\\s*\\d+\\s*,?)+$")) {
			String[] fatores = auxS.split(",");
			for (String item : fatores) {
				// listaVerificacao.add(item);
			}

			List<String> pares = new ArrayList<String>();
			for (String obj : fatores) {
				if (obj.indexOf("-") != -1) {
					pares.add(obj);
					// listaVerificacao.add(obj);
				} else {

					pagina = pagina + 1;
					listaVerificacao.add(obj);
				}
			}
			for (String obj : pares) {
				String[] auxobj = obj.split("-");
				pagina = pagina + (Integer.parseInt(auxobj[1]) - Integer.parseInt(auxobj[0]) + 1);
				listaVerificacao.add(auxobj[0]);
				listaVerificacao.add(auxobj[1]);
				// cont = cont + cobinacao(obj, pares);
			}

			int contaux = 0;
			for (String verif : listaVerificacao) {
				if (Integer.parseInt(verif) > getNumeroPaginas()) {
					cont++;
				}
				for (String verif2 : listaVerificacao) {
					if (verif.equals(verif2)) {
						contaux++;
					}
				}
			}

			if (contaux > listaVerificacao.size()) {
				cont++;
			}

			if (cont == 0) {
				return true;
			} else {
				FacesContext.getCurrentInstance().addMessage("salvar",
						new FacesMessage(FacesMessage.SEVERITY_WARN, Label.getDefinicaoPagina(), null));
				return false;
			}
		} else {

			FacesContext.getCurrentInstance().addMessage("salvar",
					new FacesMessage(FacesMessage.SEVERITY_WARN, Label.getErroPagina(), null));
			return false;
		}
	}

	private int cobinacao(String referencia, List<String> casamento) {
		int cont = 0;

		String[] objReferencia = referencia.split("-");

		for (String obj : casamento) {
			if (!obj.equals(referencia)) {
				String[] intervalo = obj.split("-");

				// n�mero inicial tem que ser menor ou igual ao n�mero final
				// n�mero final tem que ser maior ou igual ao n�mero inicial
				if (Integer.parseInt(objReferencia[0]) <= Integer.parseInt(intervalo[1])
						&& Integer.parseInt(objReferencia[1]) >= Integer.parseInt(intervalo[0])) {
					cont++;
				}

			}
		}

		return cont;
	}

	public void testeAgendamento() {
		DAOatualizaQuota dao;
		// TODO Auto-generated method stub

		dao = new DAOatualizaQuota();
		dao.atualiza();
	}

}
