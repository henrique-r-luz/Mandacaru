package controle;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpServletResponse;

import com.sun.faces.context.flash.ELFlash;

import model.Ccotacao;
import model.Citem;
import model.Corcamento;
import model.Cpedidos;
import model.Cprocesso;
import model.Cstatus;
import dao.DAO;
import dao.DAOinsereItemPedido;
import dao.DAOinsereItemProcesso;
import dao.DAOqueryItem;
import dao.DAOqueryOrcamento;
import dao.DAOqueryPedidosItens;
import dao.DAOqueryProcesso;

@ManagedBean
@ViewScoped
public class ListaProcesso implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DataModelOrcamento modelItens;
	private DataModelOrcamento modelItensSelect;
	private Corcamento[] selectedItens;
	private Corcamento[] selectedItensList;
	private Cprocesso processo;

	// private PedidosControl PedidosControl;
	private List<Corcamento> listAux;
	private List<Corcamento> listAuxItem;
	private List<Corcamento> listaTodosItens;
	private int id;
	private StatusControl statusControl;

	public List<Corcamento> getListAux() {
		return listAux;
	}

	public void setListAux(List<Corcamento> listAux) {
		this.listAux = listAux;
	}

	public Cprocesso getProcesso() {
		return processo;
	}

	public void setProcesso(Cprocesso processo) {
		this.processo = processo;
	}

	public ListaProcesso() {
		inicia();

	}

	public String inicia() {
		if (ELFlash.getFlash().get("id") == null) {

			id = -1;
		} else {

			id = (Integer) ELFlash.getFlash().get("id");
		}
		if (id == -1) {
			HttpServletResponse response = (HttpServletResponse) FacesContext
					.getCurrentInstance().getExternalContext().getResponse();
			try {
				response.sendRedirect("/index.jsf");
				FacesContext.getCurrentInstance().responseComplete();
			} catch (Exception e) {
				e.printStackTrace();
			}
			/*
			 * try { FacesContext .getCurrentInstance() .getExternalContext()
			 * .dispatch(FacesContext.getCurrentInstance() .getExternalContext()
			 * .getRequestContextPath() + "/index.jsf"); } catch (IOException e)
			 * { // TODO Auto-generated catch block e.printStackTrace(); }
			 */
		} else {

			// realizar queri para buscar orçamento
			adicionaModeloTable();
		}
		return "selecao";
	}

	public Corcamento[] getSelectedItensList() {
		return selectedItensList;
	}

	public void setSelectedItensList(Corcamento[] selectedItensList) {
		this.selectedItensList = selectedItensList;
	}

	public Corcamento[] getSelectedItens() {
		return selectedItens;
	}

	public void setSelectedItens(Corcamento[] selectedItens) {
		this.selectedItens = selectedItens;
	}

	public DataModelOrcamento getModelItensSelect() {
		return modelItensSelect;
	}

	public void setModelItensSelect(DataModelOrcamento modelItensSelect) {
		this.modelItensSelect = modelItensSelect;
	}

	public DataModelOrcamento getModelItens() {

		return modelItens;
	}

	public void setModelItens(DataModelOrcamento modelItens) {
		this.modelItens = modelItens;
	}

	private void adicionaModeloTable() {
		processo = new DAOqueryProcesso().processos(id);
		// processo.setItem(new DAOqueryItem().pesquisItemAddProcesso(id));
		listAux = new DAOqueryOrcamento().pesquisOrcamentoProcessoSelect(id);// realizar
																				// uma
		// pesquisa para
		// retornar os
		// itens do
		// processo
		// listaTodosItens = new DAOqueryItem().pesquisItemProcesso();// gerar a
		// lista de
		// pedidos
		// enviados
		// monstra itens cujo o pedidos foram fecahdo
		listAuxItem  = processo.getOrcamento();

		listaTodosItens = new ValorMedio().valoMedioTotal(listAux);
	
		for (Corcamento item : listAuxItem) {
			listaTodosItens.remove(item);
		}

		modelItensSelect = new DataModelOrcamento(listaTodosItens);
		modelItens = new DataModelOrcamento(
				new ValorMedio().valoMedioTotal(listAuxItem));
	
	}

	public void adicionarItem() {
		// criar novos procedimentos
		new DAOinsereItemProcesso().addItem(selectedItens, processo);
		new DAOinsereItemProcesso().removeItem(selectedItensList, processo);
		adicionaModeloTable();
	}

	public String enviaPregao() {
		System.out.println("envia pregao");
		List<Citem> listItem = new ArrayList<Citem>();
		for (Corcamento orca : listAuxItem) {
			listItem.add(orca.getItem());
		}
		statusControl = new StatusControl();

		statusControl.addStatusPregao(listItem, processo);
		ELFlash.getFlash().put("msg", "1");

		return "/processo/criar-processo?faces-redirect=true";

		// new DAOatualizaPedidoStatus().atualizaEstatusDiretoria(pedidos);
		// ELFlash.getFlash().put("msg", "1");
	}

	/*
	 * public String criaOrcamento() { int cont = 0; for (Corcamento orcamento :
	 * listAux) { if (orcamento.getItem().getQuantidade() == 0) {
	 * FacesContext.getCurrentInstance().addMessage( null, new
	 * FacesMessage(FacesMessage.SEVERITY_WARN, "A quantidade do item " +
	 * orcamento.getItem().getMaterial() .getNome().toUpperCase() +
	 * " não pode ser 0", "Watch out for PrimeFaces!")); cont++;
	 * 
	 * } else { orcamento.setQuantidade(orcamento.getItem().getQuantidade());
	 * new DAO<Corcamento>(Corcamento.class).atualiza(orcamento); } }
	 * 
	 * if (cont == 0) {
	 * 
	 * return "/pedidos/cria-orcamento?faces-redirect=true"; } else { return "";
	 * } }
	 */

	/*
	 * public void validaQuantidade(FacesContext fc, UIComponent component,
	 * Object value) throws ValidatorException {
	 * 
	 * int valor = Integer.parseInt((String) value); //for (Citem item :
	 * listAuxItem) { if (valor == 0 && flag==1) {
	 * FacesContext.getCurrentInstance().addMessage( null, new
	 * FacesMessage(FacesMessage.SEVERITY_WARN, "A quantidade do item " +
	 * " não pode ser 0", "Watch out for PrimeFaces!"));
	 * 
	 * }
	 * 
	 * flag=0;
	 * 
	 * }
	 */
}
