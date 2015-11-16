package controle;

import java.io.IOException;
import java.io.Serializable;
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

import model.Citem;
import model.Corcamento;
import model.Cpedidos;
import model.Cstatus;
import dao.DAO;
import dao.DAOinsereItemPedido;
import dao.DAOqueryOrcamento;
import dao.DAOqueryPedido;
import dao.DAOqueryPedidosItens;

@ManagedBean
@ViewScoped
public class ListaPedidos implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DataModelItem modelItens;
	private DataModelItem modelItensSelect;
	private Citem[] selectedItens;
	private Citem[] selectedItensList;
	private Cpedidos pedidos;

	// private PedidosControl PedidosControl;
	private List<Corcamento> listAux;
	private List<Citem> listAuxItem;
	private List<Citem> listaTodosItens;
	private int id;

	public List<Corcamento> getListAux() {
		return listAux;
	}

	public void setListAux(List<Corcamento> listAux) {
		this.listAux = listAux;
	}

	public Cpedidos getPedidos() {
		return pedidos;
	}

	public void setPedidos(Cpedidos pedidos) {
		this.pedidos = pedidos;
	}

	public ListaPedidos() {
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


	public Citem[] getSelectedItensList() {
		return selectedItensList;
	}

	public void setSelectedItensList(Citem[] selectedItensList) {
		this.selectedItensList = selectedItensList;
	}

	public Citem[] getSelectedItens() {
		return selectedItens;
	}

	public void setSelectedItens(Citem[] selectedItens) {
		this.selectedItens = selectedItens;
	}

	public DataModelItem getModelItensSelect() {
		return modelItensSelect;
	}

	public void setModelItensSelect(DataModelItem modelItensSelect) {
		this.modelItensSelect = modelItensSelect;
	}

	public DataModelItem getModelItens() {

		return modelItens;
	}

	public void setModelItens(DataModelItem modelItens) {
		this.modelItens = modelItens;
	}

	private void adicionaModeloTable() {
		pedidos = new  DAO<Cpedidos>(Cpedidos.class).buscaPorId(id);
		pedidos.setOrcamento(new DAOqueryOrcamento().orcamentoPedidos(id));
	
		listAux = pedidos.getOrcamento();
		listaTodosItens = new DAO<Citem>(Citem.class).listaTodos();
		for (Corcamento orcamento : listAux) {
			listaTodosItens.remove(orcamento.getItem());
		}
		modelItensSelect = new DataModelItem(listaTodosItens);

		listAuxItem = new ArrayList<Citem>();
		for (Corcamento orcamento : listAux) {
			orcamento.getItem().setQuantidade(orcamento.getQuantidade());
			listAuxItem.add(orcamento.getItem());
		}
		modelItens = new DataModelItem(listAuxItem);
	}
	
	

	public void adicionarItem() {

		new DAOinsereItemPedido().addItem(selectedItens, pedidos);
		new DAOinsereItemPedido().removeItem(selectedItensList, pedidos);
		adicionaModeloTable();
	}

	public String criaOrcamento() {
		int cont = 0;
		for (Corcamento orcamento : listAux) {
			if (orcamento.getItem().getQuantidade() == 0) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								"A quantidade do item "
										+ orcamento.getItem()
										+ " não pode ser 0",
								"Watch out for PrimeFaces!"));
				cont++;

			} else {
				orcamento.setQuantidade(orcamento.getItem().getQuantidade());
				new DAO<Corcamento>(Corcamento.class).atualiza(orcamento);
			}
		}

		if (cont == 0) {

			return "/pedidos/cria-orcamento?faces-redirect=true";
		} else {
			return "";
		}
	}

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
