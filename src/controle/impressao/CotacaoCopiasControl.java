package controle.impressao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import controle.quota.Label;
import dao.DAO;
import dao.DAOqueryCotacaocopia;
import model.impressao.IcotacaoCopias;

@ManagedBean
@ViewScoped
public class CotacaoCopiasControl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IcotacaoCopias cotacao = new IcotacaoCopias();
	private BigDecimal valor = BigDecimal.ZERO;
	private Date dataInicial;
	private Date dataFinal;
	private List<IcotacaoCopias> listaCotacao;

	public List<IcotacaoCopias> getListaCotacao() {
		return listaCotacao;
	}

	public void setListaCotacao(List<IcotacaoCopias> listaCotacao) {
		this.listaCotacao = listaCotacao;
	}

	public IcotacaoCopias getCotacao() {
		return cotacao;
	}

	public void setCotacao(IcotacaoCopias cotacao) {
		this.cotacao = cotacao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
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

	public CotacaoCopiasControl() {
		listaTodos();
	}

	public void salva() {
		if (!cotacao.getValor().equals(BigDecimal.ZERO)
				|| cotacao.getValor().equals(null)) {
			if (new DAOqueryCotacaocopia().verificaDados(valor,
					cotacao.getDataInicial(), cotacao.getDataFinal())) {

				FacesContext.getCurrentInstance().addMessage(
						"salvar",
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								Label.getCotacaoExite(),
								null));

			} else {
				if(cotacao.getDataFinal().compareTo(cotacao.getDataInicial())>0){
					new DAO<IcotacaoCopias>(IcotacaoCopias.class).adiciona(cotacao);
					clear();
					listaTodos();
				}
				else{
					FacesContext.getCurrentInstance().addMessage(
							"salvar",
							new FacesMessage(FacesMessage.SEVERITY_WARN,
									Label.getDataInicial(),
									null));
				}
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(
					"salvar",
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"O valor n�o pode ser 0,00", null));
		}

	}

	public void altera() {

		if (cotacao.getValor().equals(BigDecimal.ZERO)
				|| cotacao.getValor().equals(null)) {
			FacesContext.getCurrentInstance().addMessage(
					"salvar",
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							Label.getValorCopia(), null));
		} else {

			if (new DAOqueryCotacaocopia().VerificaAltera(cotacao.getId())) {
				if (new DAOqueryCotacaocopia().verificaDados(valor,
						cotacao.getDataInicial(), cotacao.getDataFinal())) {

					FacesContext
							.getCurrentInstance()
							.addMessage(
									"salvar",
									new FacesMessage(
											FacesMessage.SEVERITY_WARN,
											Label.getPeriodoCotacao(),
											null));

				} else {
					new DAO<IcotacaoCopias>(IcotacaoCopias.class)
							.atualiza(cotacao);
				}
			} else {

				FacesContext
						.getCurrentInstance()
						.addMessage(
								"salvar",
								new FacesMessage(
										FacesMessage.SEVERITY_WARN,
										Label.getAlteraCotacao(),
										null));
			}

		}

		listaTodos();
	}

	public void clear() {
		cotacao = new IcotacaoCopias();
		valor = BigDecimal.ZERO;
		dataInicial = null;
		dataFinal = null;
	}

	public void pesquisa() {
		if ((valor.equals(BigDecimal.ZERO) || valor.equals(null))
				&& dataInicial == null && dataFinal == null) {
			listaTodos();

		} else {
			listaCotacao = new DAOqueryCotacaocopia().pesquisaCotacaoCopias(
					valor, dataInicial, dataFinal);
		}
		/*
		 * }else{ FacesContext.getCurrentInstance().addMessage( "salvar", new
		 * FacesMessage(FacesMessage.SEVERITY_WARN, "O valor n�o pode ser 0,00",
		 * null)); }
		 */
	}

	public void listaTodos() {
		clear();
		if (valor.equals(BigDecimal.ZERO) && dataFinal == null
				&& dataInicial == null) {
			listaCotacao = new DAO<IcotacaoCopias>(IcotacaoCopias.class)
					.listaTodos();
		} else {
			listaCotacao = new DAOqueryCotacaocopia().pesquisaCotacaoCopias(
					valor, dataInicial, dataFinal);
		}

	}

}
